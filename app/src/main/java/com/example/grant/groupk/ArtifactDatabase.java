package com.example.grant.groupk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Grant on 4/24/2018.
 */

public class ArtifactDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "artifactlist.db";
    private static final String TABLE_NAME = "artifacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMES = "name";
    private static final String COLUMN_USERS = "users";
    private static final String COLUMN_DATE = "dates";
    private static final String COLUMN_LOCATION = "locations";
    private static final String COLUMN_DESSHORT = "descShorts";
    private static final String COLUMN_DESLONG = "descLongs";
    private static final String COLUMN_IMAGES = "images";
    private static final String COLUMN_COMMENTS = "comments";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE artifacts(id INT PRIMARY KEY NOT NULL , name TEXT NOT NULL , users TEXT NOT NULL , dates TEXT NOT NULL , locations TEXT NOT NULL , descShorts TEXT NOT NULL , descLongs TEXT NOT NULL , images TEXT NOT NULL);";

    public ArtifactDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertArtifact(Artifact artifact)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from artifacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();



        values.put(COLUMN_ID, count);
        values.put(COLUMN_USERS, artifact.getUser());
        values.put(COLUMN_NAMES, artifact.getName());
        values.put(COLUMN_DATE, artifact.getDate());
        values.put(COLUMN_LOCATION, artifact.getLocation());
        values.put(COLUMN_DESSHORT, artifact.getDescriptionShort());
        values.put(COLUMN_DESLONG, artifact.getDescriptionLong());
        values.put(COLUMN_IMAGES, artifact.getImages());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchArtifact(String s)
    {
        db = this.getReadableDatabase();
        String query = "select name from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a = "User not found.";
        if(cursor.moveToFirst())
        {
            do
            {
                if (a.equals(s))
                    return a;
            }
            while(cursor.moveToNext());
        }

        return a;
    }

    public String getUser(String s)
    {
        db = this.getReadableDatabase();
        String query = "select users from " + TABLE_NAME + " WHERE names = " + s;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public boolean contains(String name)
    {
        db = this.getReadableDatabase();
        String query = "select name from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a;
        if(cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                if (name.equals(a))
                    return true;
            }
            while (cursor.moveToNext());
        }
        return false;
    }

    public void deleteRow(String s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAMES + "='" + s + "'");
        db.close();
    }
}
