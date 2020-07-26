package com.a.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.a.myapplication.controller.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
    ArrayList<Word> arrayList = new ArrayList<>();
    String MY_PREFS_NAME = "dictionary";
    boolean readSuccessfully = false;
    ConstraintLayout loading_view;
    Context context;
    ListView list;
    boolean ifExcellReaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        loading_view = findViewById(R.id.loading_view);
        loading_view.setVisibility(View.GONE);

        list = findViewById(R.id.list);
        arrayList = new ArrayList<>();
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);

        new AsyncTaskRunner().execute();



    }

    public void ReadExcelFile (){
        try {
            InputStream myInput;
            AssetManager  assetManager = getAssets();
            myInput = assetManager.open("dic.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                Word _word = new Word();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    if (myCell.getColumnIndex() == 0){
                        _word.setGreece(myCell.toString());
                    }else if (myCell.getColumnIndex() == 1){
                        _word.setPersion(myCell.toString());
                    }else if (myCell.getColumnIndex() == 2){
                        _word.setType(myCell.toString());
                    }
//                    Log.e("FileUtils", "Cell Value: " + myCell.toString()+ " Index :" +myCell.getColumnIndex());
                }
            }
            readSuccessfully = true;
        } catch (Exception e) {
            e.printStackTrace();
            readSuccessfully = false;
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            ifExcellReaded = prefs.getBoolean("Readexcel", false);
            loading_view.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            if (!ifExcellReaded){
                ReadExcelFile();
            }
            controller controller = new controller(context);
            arrayList = controller.ReadWords();
            return "";
        }
        @Override
        protected void onProgressUpdate(String... text) {

        }
        @Override
        protected void onPostExecute(String result) {

            if (readSuccessfully){
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("Readexcel", true);
                editor.apply();
            }
            CustomAdapter customAdapter = new CustomAdapter(context, arrayList);
            list.setAdapter(customAdapter);
            loading_view.setVisibility(View.GONE);
        }
    }
}
