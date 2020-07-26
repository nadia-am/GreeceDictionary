package com.a.myapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.a.myapplication.DaoMaster;
import com.a.myapplication.DaoSession;
import com.a.myapplication.Word;
import com.a.myapplication.WordDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class model {
    DaoSession daoSession;
    private Context _context;

    public model(Context _context) {
        this._context = _context;
    }

    public void Add(Word word){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(_context, "notes-db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        WordDao dao = daoSession.getWordDao();
        dao.insert(word);
    }

    public List<Word> readAll(){
        List<Word> find;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(_context, "notes-db");
        SQLiteDatabase dbRead = helper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(dbRead);
        daoSession = daoMaster.newSession();
        WordDao readFromCard=daoSession.getWordDao();
        QueryBuilder qb = readFromCard.queryBuilder();
        find=qb.list();
        return find;
    }
}
