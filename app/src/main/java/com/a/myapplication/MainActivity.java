package com.a.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

import jxl.Sheet;
import jxl.Workbook;

public class MainActivity extends AppCompatActivity {
    ArrayList<Word> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        order();


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

    public void order (){
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
