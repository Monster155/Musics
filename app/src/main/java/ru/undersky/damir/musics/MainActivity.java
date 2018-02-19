package ru.undersky.damir.musics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
    Context context = this;
    boolean flag = true;
    Model[] modelItems = new Model[length];
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        String[] songs = getResources().getStringArray(R.array.namesOfSongs);
        String[] autors = getResources().getStringArray(R.array.autorsOfSongs);
        for (int i = 0; i < length; i++)
            if (mSettings.contains(songs[i] + " - " + autors[i])) {
                modelItems[i] = new Model(songs[i] + " - " + autors[i],
                        mSettings.getBoolean(songs[i] + " - " + autors[i], true));
                Log.e("While", modelItems[i].getName() + " " + modelItems[i].getValue());
            } else modelItems[i] = new Model(songs[i] + " - " + autors[i], false);

        maa = new MyArrayAdapter(this, modelItems);

        btn = findViewById(R.id.button);
        lv = findViewById(R.id.listView);
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
                if (flag) {
                    btn.setText("Сортировка по избранным");
                    flag = false;
                } else {
                    btn.setText("Сортировка по алфавиту");
                    flag = true;
                }
                ArrayList<Model> selectedTeams = new ArrayList<>();
                final SparseBooleanArray checkedItems = lv.getCheckedItemPositions();
                int checkedItemsCount = checkedItems.size();
                for (int i = 0; i < checkedItemsCount; i++) {
                    // Item position in adapter
                    int position = checkedItems.keyAt(i);
                    // Add team if item is checked == TRUE!
                    if (checkedItems.valueAt(i))
                        selectedTeams.add(maa.getItem(position));
                }
                if (selectedTeams.size() < 2)
                    Toast.makeText(context, "Need to select two or more teams.", Toast.LENGTH_SHORT);
                else {
                    // Just logging the output.
                    for (Model m : selectedTeams)
                        Log.e("Selected model: ", m.getName());
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*Model[] models = maa.getNames();
        SharedPreferences.Editor editor = mSettings.edit();

        for(int i = 0; i < length; i++) {
            Log.e("onStop", models[i].getName()+" "+models[i].getValue());
            editor.putBoolean(models[i].getName(), models[i].getValue());
            editor.apply();
        }
        Log.e("onStop", "Saving end");*/
    }
}
