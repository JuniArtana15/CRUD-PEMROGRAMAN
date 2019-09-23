package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelperStudent extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NIM = "nim";
    private static final String KEY_FIRSTNAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private static final String CREATE_TABLE_TEACHERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_NIM + " TEXT NOT NULL, "+
            KEY_FIRSTNAME + " TEXT NOT NULL, "+
            KEY_EMAIL + " TEXT NOT NULL, "+
            KEY_PHONE + " VARCHAR " +
            "); ";

    public DatabaseHelperStudent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_TEACHERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEACHERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long addStudentsDetail(String nim, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NIM, nim);
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        //insert row in table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    public ArrayList<StudentsModel> getAllStudents() {
        ArrayList<StudentsModel> studentsModelArrayList = new ArrayList<StudentsModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                StudentsModel studentsModel = new StudentsModel();
                studentsModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                studentsModel.setNim(c.getString(c.getColumnIndex(KEY_NIM)));
                studentsModel.setName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                studentsModel.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                studentsModel.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
                // adding to list
                studentsModelArrayList.add(studentsModel);
            } while (c.moveToNext());
        }
        return studentsModelArrayList;
    }

    public int updateStudents(int id, String nim, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NIM, nim);
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        // update row in table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


}
