package ru.undersky.damir.musics;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

public class SlidingTabs extends AppCompatActivity{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingtabs_activity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(6);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            DBHelper dbh = new DBHelper(getContext());
            SQLiteDatabase db;
            TextView tv1 = (TextView) rootView.findViewById(R.id.textView1);
            TextView tv2 = (TextView) rootView.findViewById(R.id.textView2);
            TextView tv3 = (TextView) rootView.findViewById(R.id.textView3);
            int r = getArguments().getInt(ARG_SECTION_NUMBER);

            try {dbh.updateDataBase();
            } catch (IOException mIOException) {throw new Error("UnableToUpdateDatabase");}

            try { db = dbh.getWritableDatabase();
            } catch (SQLException mSQLException) {throw mSQLException;}

            Cursor cursor = db.rawQuery("SELECT * FROM songs", null);
            cursor.moveToPosition(r);

            tv1.setText("Название: " + cursor.getString(1));
            tv2.setText("Исполнитель: " + cursor.getString(0));
            tv3.setText(cursor.getString(2));

            cursor.close();

            //String song = cursor.getString(1);
            //String author = cursor.getString(0);
            //String text = cursor.getString(2);
            //tv1.setText("Название: "+song);
            //tv2.setText("Исполнитель: "+author);
            //tv3.setText(text);

            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            return rootView;
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            int r = getIntent().getIntExtra("cur", 0);
            return r;
        }
    }
}
