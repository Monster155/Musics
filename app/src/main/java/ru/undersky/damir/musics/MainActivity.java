package ru.undersky.damir.musics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView lv;
    Button btn;
    public static int length = 20;
    MyArrayAdapter maa;
    Model[] modelItems = new Model[length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] songs = getResources().getStringArray(R.array.namesOfSongs);
        String[] autors = getResources().getStringArray(R.array.autorsOfSongs);
        for(int i = 0; i < length; i++)
            modelItems[i] = new Model(songs[i]+" - "+autors[i], false);

        lv = findViewById(R.id.listView);
        btn = findViewById(R.id.button);

        maa = new MyArrayAdapter(this, modelItems);

        lv.setAdapter(maa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("k", position);
                startActivity(i);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Saved! Не сохранено!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
