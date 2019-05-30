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
import com.karine.moodtracker.models.Mood;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.view.View.*;



public class HistoryActivity extends AppCompatActivity {


    private static Object Date;
    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;
    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private View mYesterday;
    private Date dateYesterday;
    private java.util.Date yesterday;
    private long yesterdayResult;
    private long days2agoResult;
    private long daysBetween;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mTvYesterday = findViewById(R.id.tvYesterday);
        mHistorybtn1 = findViewById(R.id.history_btn_1);
        mYesterday = findViewById(R.id.yesterday);

        retrieveComment();
        retrieveBackground();
        retrieveDate();


    }

    private void retrieveComment() {

        mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
        if (mPreferences.getString("saved", "").isEmpty()) {
            mHistorybtn1.setVisibility(INVISIBLE);
        } else {
            mHistorybtn1.setVisibility(VISIBLE);

            mHistorybtn1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("Testing", mPreferences.getString("saved", ""));

                    Toast.makeText(HistoryActivity.this, mPreferences.getString("saved", ""), Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    private void retrieveBackground() {
        SharedPreferences sharedPreferences = getSharedPreferences("save_bg", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("save_bg", "");
        Gson gson = new Gson();
        Mood mood = gson.fromJson(json, Mood.class);

        Log.d("Test_bg", "color" + mood.getSelectedMood());

        mYesterday.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[mood.getSelectedMood()]);

    }

    public void retrieveDate() {

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        Log.d("Test_Date", "onCreate() called with" + date);

        Calendar mCalendar = Calendar.getInstance();
        //mCalendar.add(Calendar.DAY_OF_MONTH, -1);//supp
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());


        daysBetween(date, dayDate);
        //yesterdayResult = daysBetween(date, dayDate);
        //Log.d("Test_Compare", "Yesterday was" + yesterdayResult);
//
        //mTvYesterday.setText(date);}

        switch ((int) daysBetween) {
            case 0:
                if (daysBetween == 0) {

                    mCalendar.add(Calendar.DAY_OF_MONTH, -1);
                    yesterdayResult = daysBetween(date, dayDate);
                    Log.d("Test_compare", " Yesterday was" + yesterdayResult);

                    break;
                }
            case 1:
                if (daysBetween == -1) {

                    daysBetween = days2agoResult;
                    days2agoResult = daysBetween(date, dayDate);
                    mCalendar.add(Calendar.DAY_OF_MONTH, -2);

                    Log.d("Test_compare2", " days2Ago" + days2agoResult);

                    break;
                }
        }
    }

    public long daysBetween(String day1, String day2) {

        long daysBetween = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date1 = myFormat.parse(day1);
            Date date2 = myFormat.parse(day2);
            long diff = date2.getTime() - date1.getTime();
            daysBetween = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            Log.d("Test_Between", "diff" + diff);
        } catch (ParseException e) {
            e.printStackTrace();


        }
        return daysBetween;
    }

}

































































