package com.a.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading_view = findViewById(R.id.loading_view);
        loading_view.setVisibility(View.GONE);

        new AsyncTaskRunner().execute();


//        final ListView list = findViewById(R.id.list);
//        arrayList = new ArrayList<>();
//        Word word = new Word();
//        word.setPersion("تست");
//        word.setGreece("test");
//        arrayList.add(word);
//        arrayList.add(word);
//        arrayList.add(word);
//        arrayList.add(word);
//        arrayList.add(word);
//        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
//        list.setAdapter(customAdapter);

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
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.e("FileUtils", "Cell Value: " + myCell.toString()+ " Index :" +myCell.getColumnIndex());
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
            boolean ifExcellReaded = prefs.getBoolean("Readexcel", false);
            loading_view.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            ReadExcelFile();
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
            loading_view.setVisibility(View.GONE);
        }
    }
}
