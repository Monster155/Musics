package ru.undersky.damir.musics;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends Activity {

    TextView tv1, tv2, tv3;
    private DBHelper dbh;
    private SQLiteDatabase db;
    String song, author, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dataB();
        textV();
    }

    public void dataB(){
        dbh = new DBHelper(this);
        int r = getIntent().getIntExtra("k", 0);

        try {dbh.updateDataBase();
        } catch (IOException mIOException) {throw new Error("UnableToUpdateDatabase");}

        try { db = dbh.getWritableDatabase();
        } catch (SQLException mSQLException) {throw mSQLException;}

        Cursor cursor = db.rawQuery("SELECT * FROM songs", null);
        cursor.moveToPosition(r);

        song = cursor.getString(1);
        author = cursor.getString(0);
        text = cursor.getString(2);

        cursor.close();
    }

    public void textV(){
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);

        tv1.setText("Название: "+song);
        tv2.setText("Исполнитель: "+ author);
        tv3.setText(text);
    }
}
