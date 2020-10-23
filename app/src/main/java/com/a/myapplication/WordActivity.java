package com.a.myapplication;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
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
        String type= myIntent.getStringExtra("type");
        Log.i("@greece=>",greece);
        Log.i("@persian=>",persian);

        TextView tv_persian = findViewById(R.id.tv_persian);
        tv_persian.setText(persian);

        TextView tv_greece = findViewById(R.id.tv_greece);
        tv_greece.setText(greece);
    }


}
