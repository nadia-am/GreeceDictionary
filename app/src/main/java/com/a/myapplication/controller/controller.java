package com.a.myapplication.controller;

import android.content.Context;

import com.a.myapplication.Word;
import com.a.myapplication.model.model;

import java.util.ArrayList;
import java.util.List;

public class controller {
    private Context _context;

    public controller(Context _context) {
        this._context = _context;
    }

    public void AddWord(Word word){
        model _model = new model(_context);
        List<Word> all = _model.readAll();
        Long count = Long.valueOf(all.size());
        word.setId(count+1);
        _model.Add(word);
    }
    public ArrayList ReadWords(){
        model _model = new model(_context);
        List<Word> all = _model.readAll();
        ArrayList arrayList = new ArrayList<>();
        for (int i=0 ; i<all.size() ; i++){
            Word _w = new Word();
            _w.setGreece(all.get(i).getGreece());
            _w.setPersion(all.get(i).getPersion());
            _w.setType(all.get(i).getType());
            arrayList.add(_w);
        }
        return arrayList;
    }
}
