package ru.undersky.damir.musics;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends Activity {

    ListView lv;
    TextView textView;
    private DBHelper dbh;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        dataB();
        //listV();
    }

    public void dataB(){
        dbh = new DBHelper(this);
        try {dbh.updateDataBase();
        } catch (IOException mIOException) {throw new Error("UnableToUpdateDatabase");}

        try { db = dbh.getWritableDatabase();
        } catch (SQLException mSQLException) {throw mSQLException;}


        String product = "";

        Cursor cursor = db.rawQuery("SELECT * FROM songs", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product += cursor.getString(2) + " | ";
            cursor.moveToNext();
        }
        cursor.close();

        textView.setText(product);
    }

    public void listV(){
        lv = findViewById(R.id.listView);

        String[] songs = getResources().getStringArray(R.array.namesOfSongs);
        String[] autors = getResources().getStringArray(R.array.autorsOfSongs);
        String[] all = new String[songs.length];
        for(int i = 0; i < all.length; i++)
            all[i] = songs[i] + " - " + autors[i];

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, all);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra( "k", position);
                startActivity(i);
            }
        });
    }
}
