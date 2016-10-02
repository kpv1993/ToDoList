package com.example.android.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prash on 9/30/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todolistapp.db";
    public static final String TABLE = "todo_table";
    public static final String KEY_ID = "_id";
    public static final String KEY_TASK = "TASK";
    public static final String KEY_DATE = "DATE";

    public static final String[] ALL_KEYS = new String[]{KEY_ID,KEY_TASK,KEY_DATE};

    public static final int COL_1 = 0;
    public static final int COL_2 = 1;
    public static final int COL_3 = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String task,String date){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TASK,task);
        contentValues.put(KEY_DATE,date);

        long result = sqLiteDatabase.insert(TABLE,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where=null;
        Cursor c = sqLiteDatabase.query(true,TABLE,ALL_KEYS,where,null,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor row(long id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where = KEY_ID+"="+id;
        Cursor c = sqLiteDatabase.query(true,TABLE,ALL_KEYS,where,null,null,null,null,null);
        if(c!=null)
            c.moveToFirst();
        return  c;
    }

    public boolean update(long id,String task,String date){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where = KEY_ID+"="+id;
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_TASK,task);
        newValues.put(KEY_DATE,date);

        return sqLiteDatabase.update(TABLE,newValues,where,null)!=0;
    }

    public boolean delete(long id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where = KEY_ID+"="+id;
        return sqLiteDatabase.delete(TABLE,where,null)!=0;
    }
}
