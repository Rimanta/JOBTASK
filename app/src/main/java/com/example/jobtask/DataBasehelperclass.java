package com.example.jobtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBasehelperclass  extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Items";
    private static final String COL_NAME = "name";

    private static final String CREATE_TABLE = "CREATE TABLE Items(name TEXT)";

    public DataBasehelperclass(@Nullable Context context) {
        super(context, "Itemdetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void insertDatatoDatabase(SQLiteDatabase database , Itemdetails itemdetails){
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,itemdetails.Itemname);
        database.insert(TABLE_NAME,null,cv);
    }

    public ArrayList<Itemdetails> getDataFromDatabase(SQLiteDatabase database) {
        ArrayList<Itemdetails> itemList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Itemdetails data = new Itemdetails();
                data.Itemname = cursor.getString(cursor.getColumnIndex(COL_NAME));
                itemList.add(data);
            }while (cursor.moveToNext());

            cursor.close();

        }
        return itemList;
}
public void  delteDatafromDatabase(SQLiteDatabase database, Itemdetails itemdetails){
        database.delete(TABLE_NAME,COL_NAME + "=" + itemdetails.Itemname,null);
}
}

