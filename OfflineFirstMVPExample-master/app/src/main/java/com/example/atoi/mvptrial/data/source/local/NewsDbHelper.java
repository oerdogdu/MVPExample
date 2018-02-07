package com.example.atoi.mvptrial.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Atoi on 4.01.2018.
 */

public class NewsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Tasks.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsPersistenceContract.NewsEntry.TABLE_NAME + " (" +
                    NewsPersistenceContract.NewsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY," +
                    NewsPersistenceContract.NewsEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    NewsPersistenceContract.NewsEntry.COLUMN_NAME_TEXT + TEXT_TYPE +
                    " )";


    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {

    }

    @Override
    public void close() {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
