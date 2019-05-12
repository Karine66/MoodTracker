package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karine.moodtracker.R;
import com.karine.moodtracker.models.Mood;




public class HistoryActivity extends AppCompatActivity {


    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private ImageView mHistorybtn1;
    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private Gson gson;
    private String json;
    private SharedPreferences sharedPreferences;
    private FrameLayout mDay1ago;
    private int[] mMood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mTvYesterday = (TextView) findViewById(R.id.tvYesterday);

        mHistorybtn1 = (ImageView) findViewById(R.id.history_btn_1);

        mDay1ago = (FrameLayout) findViewById(R.id.day1_ago);



        mHistorybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
                Log.d("Testing", mPreferences.getString("saved", ""));

                Toast.makeText(HistoryActivity.this, mPreferences.getString("saved", ""), Toast.LENGTH_SHORT).show();
            }

        });

        retrieveBackground();

        retrieveDate();
    }

    private void retrieveBackground() {
        sharedPreferences = getSharedPreferences("save_bg", Context.MODE_PRIVATE);
        String bg = sharedPreferences.getString("save_bg", "nothing");
        Gson gson = new Gson();
        Mood mood = gson.fromJson(json, Mood.class);
        mood.getSelectedMood(Mood.ARRAY_BACKGROUND_COLOR);
        Log.d("Test_bg", "color" + bg);

    }

    public void retrieveDate() {
        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        Log.d("Test_Date", "onCreate() called with" + date);

        mTvYesterday.setText(date);
    }
}



































