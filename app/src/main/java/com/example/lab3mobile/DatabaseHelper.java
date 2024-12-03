package com.example.lab3mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Одногруппники";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ФИО TEXT, " +
                "Время_добавления INTEGER)");

        initializeData(db);
    }

    private void initializeData(SQLiteDatabase db) {
        // Удаляем все записи и добавляем 5 новых
        db.execSQL("DELETE FROM " + TABLE_NAME);
        for (int i = 1; i <= 5; i++) {
            ContentValues values = new ContentValues();
            values.put("ФИО", "Студент " + i);
            values.put("Время_добавления", System.currentTimeMillis());
            db.insert(TABLE_NAME, null, values);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertStudent(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ФИО", name);
        values.put("Время_добавления", System.currentTimeMillis());
        db.insert(TABLE_NAME, null, values);
    }

    public void updateLastStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET ФИО = 'Иванов Иван Иванович' " +
                "WHERE ID = (SELECT MAX(ID) FROM " + TABLE_NAME + ")");
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public static String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}