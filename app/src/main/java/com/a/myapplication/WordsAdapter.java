package com.a.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsAdapter extends ArrayAdapter<Word> {
    Context _context;
    public WordsAdapter(Context context, ArrayList<Word> users) {
        super(context, 0, users);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Word _word = getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(_context, WordActivity.class);
                    myIntent.putExtra("greece",_word.getGreece());
                    myIntent.putExtra("persian",_word.getPersion());
                    myIntent.putExtra("type",_word.getType());
                    _context.startActivity(myIntent);
                }
            });
            TextView tittle=convertView.findViewById(R.id.tv_word);
            tittle.setText(_word.getPersion());

        }
        return convertView;
    }
}
