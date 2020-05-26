package com.a.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_activity);

        Intent myIntent = getIntent();

        String greece = myIntent.getStringExtra("greece");
        String persian= myIntent.getStringExtra("persian");

        TextView tv_definition = findViewById(R.id.tv_definition);
        tv_definition.setText(greece);

        TextView tv_persian = findViewById(R.id.tv_persian);
        tv_persian.setText(persian);
    }
}
