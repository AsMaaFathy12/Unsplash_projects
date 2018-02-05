package com.example.asmaa.unsplash_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AsMaa on 2/5/2018.
 */

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "NAME text , EMAIL text,PASSWORD text); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {

        return db;
    }

    public void insertEntry(String name , String email, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAME", name);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        db.insert("LOGIN", null, newValues);

    }

    public int deleteEntry(String email) {

        String where = "EMAIL=?";
        int dele = db.delete("LOGIN", where,
                new String[] { email });
        return dele;
    }

    public String getSinlgeEntry(String email) {
        Cursor cursor = db.query("LOGIN", null, " EMAIL=?",
                new String[] { email }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateEntry(String name,String email , String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("NAME", name);
        updatedValues.put("Email", email);
        updatedValues.put("PASSWORD", password);

        String where = "EMAIL= ?";
        db.update("LOGIN", updatedValues, where, new String[] { email });
    }

}
