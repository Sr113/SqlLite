package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "college";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student";
    //private static final String ID_COL = "id";
    private static final String REG_COL = "RegNo";
    private static final String NAME_COL = "name";
    private static final String EMAIL_COL = "email";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME_COL + " TEXT,"
                + REG_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";

        db.execSQL(query);
    }
    public void addNewCourse(String regNo, String name, String email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(REG_COL, regNo);
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<StudentModal> readData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorStudent = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<StudentModal> studentModalArrayList = new ArrayList<>();
        if (cursorStudent.moveToFirst()) {
            do {
                studentModalArrayList.add(new StudentModal(
                        cursorStudent.getString(2),
                        cursorStudent.getString(1),
                        cursorStudent.getString(3)));
            } while (cursorStudent.moveToNext());
        }
        cursorStudent.close();
        return studentModalArrayList;
    }

    public ArrayList<StudentModal> getData(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM student WHERE name = ?";
        String[] selectionArgs = {name};
        Cursor cursorStudent = db.rawQuery(query,selectionArgs);
        ArrayList<StudentModal> studentModalArrayList = new ArrayList<>();
        if (cursorStudent.moveToFirst()) {
            do {
                studentModalArrayList.add(new StudentModal(
                        cursorStudent.getString(2),
                        cursorStudent.getString(1),
                        cursorStudent.getString(3)));
            } while (cursorStudent.moveToNext());
        }
        cursorStudent.close();
        return studentModalArrayList;
    }
    public void updateData(String regNo, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_COL, regNo);
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        int rowsAffected = db.update(TABLE_NAME, values, "name=?", new String[]{name});
        db.close();
        Log.d("UpdateData", "Rows affected: " + rowsAffected);
    }

    public void deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{name});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}