package ivy.com.popularmoviesstage1.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ivy.com.popularmoviesstage1.model.Favorite;

/**
 * Created by darshan on 14/6/16.
 */
public class MovieDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Favorite.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MOVIE_TABLE = "CREATE TABLE " + FavoriteContract.Tables.MOVIE + " (" +
                FavoriteContract.MovieColumns.MOVIE_ID + " INTEGER PRIMARY KEY, " +
                FavoriteContract.MovieColumns.MOVIE_TITLE + " TEXT, " +
                FavoriteContract.MovieColumns.MOVIE_RELEASE_DATE + " TEXT, " +
                FavoriteContract.MovieColumns.MOVIE_DURATION + " INTEGER, " +
                FavoriteContract.MovieColumns.MOVIE_RATING + " REAL, " +
                FavoriteContract.MovieColumns.MOVIE_POSTER_PATH + " TEXT, " +
                FavoriteContract.MovieColumns.MOVIE_BACKDROP_PATH + " TEXT" + ")";
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
