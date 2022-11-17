package com.example.myapplicatiom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.myapplicatiom.MainActivity2;

public class DbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";
    
    private static final String SQL_CREATE_ENTRIES = 
            "CREATE TABLE " + database.FeedEntry.TABLE_NAME + " (" +
            database.FeedEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    //insert data
    public boolean insertData(String dni, String name, String surname, String edad, String direc) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(BaseColumns._ID, id);
        contentValues.put(database.FeedEntry.COLUMN_NAME_DNI, dni);
        contentValues.put(database.FeedEntry.COLUMN_NAME_NOMBRE, name);
        contentValues.put(database.FeedEntry.COLUMN_NAME_APELLIDO, surname);
        contentValues.put(database.FeedEntry.COLUMN_NAME_EDAD, edad);
        contentValues.put(database.FeedEntry.COLUMN_NAME_DIRECCION, direc);

        long result = sqLiteDatabase.insert(database.FeedEntry.TABLE_NAME , null, contentValues); //insert data in table and returns long value. if positive then inserted data successfully
        if(result == 1) {
            return false;
        } else {
            return true;
        }
    }

    //get all data
    public Cursor getAllData() {

        String selectAll = "SELECT * FROM " +  database.FeedEntry.TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAll, null);

        return cursor;

    }
    //get all data
    public Cursor SortByAgeDESC() {

        String sortAge = "SELECT * FROM " +  database.FeedEntry.TABLE_NAME + " ORDER BY " + database.FeedEntry.COLUMN_NAME_EDAD + " DESC ";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sortAge, null);

        return cursor;

    }

    //get all data
    public Cursor SortByAgeASC() {

        String sortAge = "SELECT * FROM " +  database.FeedEntry.TABLE_NAME + " ORDER BY " + database.FeedEntry.COLUMN_NAME_EDAD + " ASC ";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sortAge, null);

        return cursor;

    }

    //Delete data by name
    /*public boolean deleteData(String name) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(DATABASE_NAME, database.FeedEntry.TABLE_NAME + " = ?", new String[]{name});
        sqLiteDatabase.close();
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }*/

    public Integer deleteAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(database.FeedEntry.TABLE_NAME,null,null);
    }
}
