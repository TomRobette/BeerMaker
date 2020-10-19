package com.example.beermaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String create = " create table recettes (" +
            "id INTEGER PRIMARY KEY, "+
            "volAlc INTEGER NOT NULL, "+
            "degAlc INTEGER NOT NULL, "+
            "ebc DOUBLE(15,15) NOT NULL, "+
            "malt DOUBLE(15,15) NOT NULL, "+
            "eauBra DOUBLE(15,15) NOT NULL, "+
            "eauRin DOUBLE(15,15) NOT NULL, "+
            "houblonAm DOUBLE(15,15) NOT NULL, "+
            "houblonAr DOUBLE(15,15) NOT NULL, "+
            "levure DOUBLE(15,15) NOT NULL, "+
            "color TEXT NOT NULL, "+
            "mcu DOUBLE(15,15) NOT NULL, "+
            "srm DOUBLE(15,15) NOT NULL);";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
