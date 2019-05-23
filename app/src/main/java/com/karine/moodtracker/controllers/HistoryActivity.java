package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karine.moodtracker.R;
import com.karine.moodtracker.models.AlertDialog;
import com.karine.moodtracker.models.Mood;

import java.util.Objects;

import static android.view.View.*;


public class HistoryActivity extends AppCompatActivity {


    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;
    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private View mYesterday;
    private MainActivity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mTvYesterday = findViewById(R.id.tvYesterday);
        mHistorybtn1 = findViewById(R.id.history_btn_1);
        mYesterday  = findViewById(R.id.yesterday);

        retrieveComment();
        retrieveBackground();
        retrieveDate();
    }

    private void retrieveComment(){
        //if (mContext.getComment.isEmpty()) {
            //mHistorybtn1.setVisibility(INVISIBLE);
        //}else {
           // mHistorybtn1.setVisibility(GONE);

        mHistorybtn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
                Log.d("Testing", mPreferences.getString("saved", ""));

                Toast.makeText(HistoryActivity.this, mPreferences.getString("saved", ""), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void retrieveBackground() {
        SharedPreferences sharedPreferences = getSharedPreferences("save_bg", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("save_bg", "");
        Gson gson = new Gson();
        Mood mood = gson.fromJson(json,  Mood.class);

            Log.d("Test_bg", "color" +  mood.getSelectedMood());

           mYesterday.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[mood.getSelectedMood()]);

    }


        public void retrieveDate() {
        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        Log.d("Test_Date", "onCreate() called with" + date);

        mTvYesterday.setText(date);
    }
}



































