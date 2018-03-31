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

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView lv;
    ArrayList<String> names = new ArrayList<>();
    private DBHelper dbh;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataB();
        listV();
    }

    public void dataB(){
        dbh = new DBHelper(this);
        try {dbh.updateDataBase();
        } catch (IOException mIOException) {throw new Error("UnableToUpdateDatabase");}

        try { db = dbh.getWritableDatabase();
        } catch (SQLException mSQLException) {throw mSQLException;}

        Cursor cursor = db.rawQuery("SELECT * FROM songs", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            names.add(cursor.getString(1) + " - " + cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public void listV(){
        lv = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names);

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
