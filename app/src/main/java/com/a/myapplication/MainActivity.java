package com.a.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView list = findViewById(R.id.list);
        ArrayList<Word> arrayList = new ArrayList<>();
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
