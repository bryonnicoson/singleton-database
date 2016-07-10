package com.bryonnicoson.singletondatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by bryon on 7/10/16.
 *
 * SOURCES (Thanks! :)
 * Alex Lockwood:
 * www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html
 *
 * Adam McNeilly:
 * androidessence.com/sqliteopenhelper-and-the-singleton-pattern/
 *
 * INITIALIZE HELPER OBJECT: DatabaseHelper.getInstance(context);
 * (iso "new DatabaseHelper(context)")
 *
 * TODO: Implement ContentProvider to wrap SQLiteDatabase
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "game.db";
    private static final String GAME_TABLE = "game";
    private static final int DATABASE_VERSION = 3;

    private static final String GAME_ID = "_id";
    private static final String GAME_NAME = "name";
    private static final String[] GAME_KEYS = {GAME_ID, GAME_NAME};

    public static synchronized DatabaseHelper getInstance(Context context) {

        // use application context to prevent leaking of activity context: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    // constructor is private to prevent direct instantiation
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_GAMES_TABLE = "CREATE TABLE " + GAME_TABLE + "(" +
                GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GAME_NAME + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(CREATE_GAMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading application's database from version " + oldVersion + " to " + newVersion +
                " to " + newVersion + ", which will destroy all existing data!");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GAME_TABLE+";");
        onCreate(sqLiteDatabase);
    }

    // TODO: I wonder if what follows should be in another class (like the model?)

    public long add(String name){
        ContentValues values = new ContentValues();
        values.put(GAME_NAME, name);
        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(GAME_TABLE, null, values);
        db.close();
        return returnId;
    }

    public Cursor list(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(GAME_TABLE,    // table
                GAME_KEYS,                      // columns
                null,                           // selections
                null,                           // selection args
                null,                           // group by
                null,                           // having
                null,                           // order by
                null);                          // limit
        return cursor;
    }

}
