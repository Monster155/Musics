package ru.undersky.damir.musics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
