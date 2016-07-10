package com.bryonnicoson.singletondatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    int id;
    String name;
    List<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseHelper.getInstance(this);

//  give us some test data one time
//
//        db.add("Smash Up");
//        db.add("Carcasonne");
//        db.add("Settlers of Catan");
//        db.add("Sentinels of the Multiverse");
//        db.add("Bang");
//        db.add("Zombicide");
//        db.add("Dominion");

        Cursor cursor = db.list();
        cursor.moveToFirst();

        games = new ArrayList<>();

        while(!cursor.isLast()){
            id = cursor.getInt(0);
            name = cursor.getString(1);
            games.add(new Game(id, name));

            Bundle fa = new Bundle();
            fa.putString("NAME", name);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            GameFragment gf = new GameFragment();
            gf.setArguments(fa);

            ft.add(R.id.game_container, gf);
            ft.commit();
            cursor.moveToNext();
        }
        db.close();
    }
}
