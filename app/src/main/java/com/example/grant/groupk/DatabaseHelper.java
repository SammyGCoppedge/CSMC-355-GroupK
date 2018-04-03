package com.example.grant.groupk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Grant on 4/2/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userlist.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER = "user";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE users(id INT PRIMARY KEY NOT NULL , user TEXT NOT NULL , email TEXT NOT NULL , password TEXT NOT NULL);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertUser(User user)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_USER, user.getUsername());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String s)
    {
        db = this.getReadableDatabase();
        String query = "select user, password from " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "User not found.";
        if(cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                b = cursor.getString(1);


                if (a.equals(s))
                {
                    b = cursor.getString(1);
                    break;
                }

                b = "Word not found.";
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    public boolean contains(String name, String email)
    {
        db = this.getReadableDatabase();
        String query = "select user, email from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        if(cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                b = cursor.getString(1);

                if (name.equals(a))
                    return true;
                if (b.equals(email))
                    return true;
            }
            while (cursor.moveToNext());
        }
        return false;
    }
}
