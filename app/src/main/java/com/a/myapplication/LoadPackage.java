package com.a.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.a.myapplication.controller.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.Iterator;

public class LoadPackage  extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rel_pack_1;
    RelativeLayout rel_pack_2;
    RelativeLayout rel_pack_3;
    RelativeLayout rel_pack_4;
    RelativeLayout rel_pack_5;
    RelativeLayout rel_pack_6;
    RelativeLayout rel_pack_7;
    ConstraintLayout loading_view;
    Context context;
    int btnClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity);
        context = this;
        loading_view = findViewById(R.id.loading_view);
        loading_view.setVisibility(View.GONE);

        rel_pack_1 = findViewById(R.id.rel_pack_1);
        rel_pack_1.setOnClickListener(this);

        rel_pack_2 = findViewById(R.id.rel_pack_2);
        rel_pack_2.setOnClickListener(this);

        rel_pack_3 = findViewById(R.id.rel_pack_3);
        rel_pack_3.setOnClickListener(this);

        rel_pack_4 = findViewById(R.id.rel_pack_4);
        rel_pack_4.setOnClickListener(this);

        rel_pack_5 = findViewById(R.id.rel_pack_5);
        rel_pack_5.setOnClickListener(this);

        rel_pack_6 = findViewById(R.id.rel_pack_6);
        rel_pack_6.setOnClickListener(this);

        rel_pack_7 = findViewById(R.id.rel_pack_7);
        rel_pack_7.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rel_pack_1:
                btnClicked = 1;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_2:
                btnClicked = 2;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_3:
                btnClicked = 3;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_4:
                btnClicked = 4;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_5:
                btnClicked = 5;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_6:
                btnClicked = 6;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            case R.id.rel_pack_7:
                btnClicked = 7;
                loading_view.setVisibility(View.VISIBLE);
                new AsyncTaskRunner().execute();
                break;
            default:
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
//            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//            ifExcellReaded = prefs.getBoolean("Readexcel", false);
//            Log.i("@Read-state =>",String.valueOf(ifExcellReaded));
            loading_view.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            switch (btnClicked){
                case 1:
                    ReadExcelFile("dic1.xls");
                    break;
                case 2:
                    ReadExcelFile("dic2.xls");
                    break;
                case 3:
                    ReadExcelFile("dic3.xls");
                    break;
                case 4:
                    ReadExcelFile("dic4.xls");
                    break;
                case 5:
                    ReadExcelFile("dic5.xls");
                    break;
                case 6:
                    ReadExcelFile("dic6.xls");
                    break;
                case 7:
                    ReadExcelFile("dic7.xls");
                    break;

            }

//            if (!ifExcellReaded){
//            }
//            controller controller = new controller(context);
//            arrayList = controller.ReadWords();
            return "";
        }
        @Override
        protected void onProgressUpdate(String... text) {

        }
        @Override
        protected void onPostExecute(String result) {
//            if (readSuccessfully){
//                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                editor.putBoolean("Readexcel", true);
//                editor.apply();
//            }
//            wordsAdapter = new WordsAdapter(context, arrayList);
//            list.setAdapter(wordsAdapter);
            loading_view.setVisibility(View.GONE);

        }
    }

    public void ReadExcelFile (String name){
        try {
            InputStream myInput;
            AssetManager assetManager = getAssets();
            myInput = assetManager.open(name);
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
                        _word.setType(myCell.toString());
                    }else if (myCell.getColumnIndex() == 2){
                        _word.setPersion(myCell.toString());
                    }
                }
                controller controller = new controller(context);
                controller.AddWord(_word);
                //Log.i("@FileUtils", "greece: " + _word.getGreece()+ " persian :" +_word.getPersion());
            }
//            readSuccessfully = true;
        } catch (Exception e) {
            e.printStackTrace();
//            readSuccessfully = false;
        }
    }
}
