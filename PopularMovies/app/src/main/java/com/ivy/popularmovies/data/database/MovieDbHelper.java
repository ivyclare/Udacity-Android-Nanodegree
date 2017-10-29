package com.ivy.popularmovies.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Favorite.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieContract.Tables.MOVIE + " (" +
                MovieContract.MovieColumns.MOVIE_ID + " INTEGER PRIMARY KEY, " +
                MovieContract.MovieColumns.MOVIE_TITLE + " TEXT, " +
                MovieContract.MovieColumns.MOVIE_RELEASE_DATE + " TEXT, " +
                MovieContract.MovieColumns.MOVIE_VOTE_AVERAGE + " DOUBLE, " +
                MovieContract.MovieColumns.MOVIE_OVERVIEW + " TEXT, " +
                MovieContract.MovieColumns.MOVIE_POSTER_PATH + " TEXT, " +
                MovieContract.MovieColumns.MOVIE_BACKDROP_PATH + " TEXT" + ")";
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
