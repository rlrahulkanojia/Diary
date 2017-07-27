package com.example.root.app;

/**
 * Created by root on 27/6/17.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.root.app.Record;

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RecordManager";

    // Records table name
    private static final String TABLE_RECORDS = "Record";

    // Records Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEXT = "text";
    private final ArrayList<Record> record_list = new ArrayList<Record>();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RecordS_TABLE = "CREATE TABLE " + TABLE_RECORDS + "("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_RecordS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Record
    public boolean Add_Record(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, record.getName()); // Record Name
        values.put(KEY_DATE, record.getDate()); // Record Phone
        values.put(KEY_TEXT, record.gettext()); // Record Email
        // Inserting Row
        long result =db.insert(TABLE_RECORDS, null, values);
        db.close(); // Closing database connection
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Getting single Record
    Record Get_Record(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECORDS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DATE, KEY_TEXT}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Record record = new Record(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return Record
        cursor.close();
        db.close();

        return record;
    }

    // Getting All Records
   public ArrayList<Record> Get_Records() {
        try {
            record_list.clear();

            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_RECORDS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Record Record = new Record();
                    Record.setID(Integer.parseInt(cursor.getString(0)));
                    Record.setName(cursor.getString(1));
                    Record.setDate(cursor.getString(2));
                    Record.settext(cursor.getString(3));
                    // Adding Record to list
                    record_list.add(Record);
                } while (cursor.moveToNext());
            }

            // return Record list
            cursor.close();
            db.close();
            return record_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_Record", "" + e);
        }

        return record_list;
    }

    // Updating single Record
    public int Update_Record(Record Record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Record.getName());
        values.put(KEY_DATE, Record.getDate());
        values.put(KEY_TEXT, Record.gettext());

        // updating row
        return db.update(TABLE_RECORDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(Record.getID()) });
    }

    // Deleting single Record
    public void Delete_Record(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECORDS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
/*
    // Getting Records Count
   /* public Cursor getListContents() {  //Get_Total_Records

        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_RECORDS;
        Cursor cursor = db.rawQuery(countQuery, null);


        // return count
        return cursor;
    }*/


    public ArrayList<HashMap<String, String>> selectAllIds() {
        try {
            ArrayList<HashMap<String, String>> idArrayList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            SQLiteDatabase database = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_RECORDS;

            Cursor cursor = database.rawQuery(query, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        map = new HashMap<String, String>();
                        map.put("id", cursor.getString(0));
                        idArrayList.add(map);

                    } while (cursor.moveToNext());
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            database.close();
            return idArrayList;


        } catch (Exception e) {
            return null;
        }

    }




    }







