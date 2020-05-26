package com.a.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Word> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = findViewById(R.id.list);
        arrayList = new ArrayList<>();
        Word word = new Word();
        word.setPersion("تست");
        word.setGreece("test");
        arrayList.add(word);
        arrayList.add(word);
        arrayList.add(word);
        arrayList.add(word);
        arrayList.add(word);
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);

    }
}
