package ru.undersky.damir.musics;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class SecondActivity extends Activity {

    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        int r = getIntent().getIntExtra("k", 0);
        String[] songs = getResources().getStringArray(R.array.namesOfSongs);
        String[] autors = getResources().getStringArray(R.array.autorsOfSongs);
        String[] texts = getResources().getStringArray(R.array.textsOfSongs);
        tv1.setText("Название: "+songs[r]);
        tv2.setText("Исполнитель: "+autors[r]);
        tv3.setText(Html.fromHtml(texts[r]));
    }
}
