package com.example.myapplicatiom.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    
    private static final String SQL_CREATE_ENTRIES = 
            "CREATE TABLE " + database.FeedEntry.TABLE_NAME + " (" +
            database.FeedEntry._ID + " INTEGER PRIMARY KEY," +
            database.FeedEntry.COLUMN_NAME_DNI + " TEXT," +
            database.FeedEntry.COLUMN_NAME_NOMBRE + " TEXT," +
            database.FeedEntry.COLUMN_NAME_APELLIDO + " TEXT," +
            database.FeedEntry.COLUMN_NAME_EDAD + " TEXT,"+
            database.FeedEntry.COLUMN_NAME_DIRECCION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + database.FeedEntry.TABLE_NAME;;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}