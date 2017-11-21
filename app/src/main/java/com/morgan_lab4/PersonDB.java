package com.morgan_lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.R.attr.description;

public class PersonDB extends SQLiteOpenHelper {


//    class Row extends Object {
//        public long _id;
//        public String name;
//        public int wins;
//        public int losses;
//        public int ties;
//        public boolean isSelected;
//    }

    private static String CREATE_DATABASE;
    private static final String DATABASE_NAME = "PERSONDB";
    private static final String DATABASE_TABLE = "Person";
    private static final int DATABASE_VERSION = 2;
    private Context context = null;
    private SQLiteDatabase db;

    public PersonDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        CREATE_DATABASE = "create table Person(" +
                "_id integer primary key autoincrement," +
                context.getString(R.string.name_field_name) + " text not null, " +
                context.getString(R.string.wins_field_name) + " integer not null, " +
                context.getString(R.string.losses_field_name) + " integer not null, " +
                context.getString(R.string.ties_field_name) + " integer not null" +
                ");";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void insertPerson(String name) {
        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(context.getString(R.string.name_field_name), name);
            contentValues.put(context.getString(R.string.wins_field_name), 0);
            contentValues.put(context.getString(R.string.losses_field_name), 0);
            contentValues.put(context.getString(R.string.ties_field_name), 0);

            db.insertOrThrow(DATABASE_TABLE, null, contentValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson( int id, String fieldName, int value) {

        try {
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(fieldName, value);

            db.update(DATABASE_TABLE, contentValues, context.getString(R.string.person_id_field_name) + "=" + id, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public int getStat(String personName, String fieldName)
    {
        String query = "SELECT " + fieldName + " FROM " + DATABASE_TABLE + " WHERE " + context.getString(R.string.name_field_name) + " = " + personName;
        int value = -1;

        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    do {
                        value = cursor.getInt(cursor.getColumnIndex(fieldName));
                    }
                    while (cursor.moveToNext());
                }
            }

            cursor.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }

    public Player getPlayerFromName( String personName)
    {
        Player person = null;
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + context.getString(R.string.name_field_name) + " = " + personName;

        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.getCount() == 1) {
                if (cursor.moveToFirst()) {
                    do {
                        person = createPlayer(cursor);
                    }
                    while (cursor.moveToNext());
                }
            }

            cursor.close();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DATABASE_TABLE;

        try
        {
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst())
            {
                do
                {
                    players.add(createPlayer(cursor));
                } while(cursor.moveToNext());
            }

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }

        return players;
    }

    private Player createPlayer(Cursor cursor)
    {
        int _id = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.person_id_field_name)));
        String name = cursor.getString(cursor.getColumnIndex(context.getString(R.string.name_field_name)));
        int wins = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.wins_field_name)));
        int losses = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.losses_field_name)));
        int ties = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.ties_field_name)));

        return new Player(_id, name, wins, losses, ties);
    }
}
