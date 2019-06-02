package com.karine.moodtracker.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.view.View.*;
import static com.karine.moodtracker.models.Mood.*;
import static com.karine.moodtracker.models.Mood.ARRAY_BACKGROUND_COLOR;


public class HistoryActivity extends AppCompatActivity {


    private static Object Date;
    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;

    //Initialize TextView
    private TextView mTvYesterday;
    private TextView mTvDays2Ago;
    private TextView mTvDays3Ago;
    private TextView mTvDays4Ago;
    private TextView mTvDays5Ago;
    private TextView mTvDays6Ago;
    private TextView mTvDays7Ago;
    //View
    private View mYesterday;
    private View mDays2Ago;


    private long dayAgoResult;

    private SharedPreferences myPrefs;

    private Mood mood;
    private String date;
    private String dayDate;
    private String daydate;
    private long diff;
    private long daysBetween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //TextView
        mTvYesterday = findViewById(R.id.tvYesterday);
        mTvDays2Ago = findViewById(R.id.tvDays2Ago);
        mTvDays3Ago = findViewById(R.id.tvDays3Ago);
        mTvDays4Ago = findViewById(R.id.tvDays4Ago);
        mTvDays5Ago = findViewById(R.id.tvDays5Ago);
        mTvDays6Ago = findViewById(R.id.tvDays6Ago);
        mTvDays7Ago = findViewById(R.id.tvDays7Ago);

        mHistorybtn1 = findViewById(R.id.history_btn_1);
        //View
        mYesterday = findViewById(R.id.yesterday);
        mDays2Ago = findViewById(R.id.days2_ago);

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

       mYesterday.setBackgroundResource(ARRAY_BACKGROUND_COLOR[mood.getSelectedMood()]);


    }



    public void retrieveDate() {

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        Log.d("Test_Date", "onCreate() called with" + date);

        Calendar mCalendar = Calendar.getInstance();
        //mCalendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        olderDays(date, dayDate);
        daysBetween(date, dayDate);
        //Log.d("Test_Compare", "Yesterday was" + dayAgoResult);
//
        //mTvYesterday.setText(date);}


    }



    public long daysBetween (String day1, String day2) {



        long daysBetween = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date1 = myFormat.parse(day1);
            Date date2 = myFormat.parse(day2);

            long diff = date2.getTime() - date1.getTime();



            daysBetween = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

            Log.d("Test_Between", "diff" + diff);
            Log.d("Test_yesterday","Il ya a"+daysBetween+"jours");
        } catch (ParseException e) {
            e.printStackTrace();


        }

        return daysBetween;

    }



    private long olderDays (String date, String dayDate) {


       dayAgoResult= daysBetween(date, dayDate);
       diff = date.compareTo(dayDate);



        switch ((int) dayAgoResult) {
            case 0:

                if (diff == -1) {
                mTvYesterday.setText("Il y a" + dayAgoResult + "jours");

                Log.d("Test_Days", "Il y a" + dayAgoResult + "jours");
           } else {
               mTvYesterday.setText("Pas d'historique");
            }
            break;

            case 1:

                mTvDays2Ago.setText("Il y a 2 jours");

                Log.d("Test_2Days", "Il y a 2 jours" + dayAgoResult);
            break;

            case 2:

                mTvDays3Ago.setText("Il y a 3 jours");

                Log.d("Test_3Days", "Il y a 3 jours" + dayAgoResult);
            break;

            case 3:

                mTvDays4Ago.setText("Il y a 4 jours");

                Log.d("Test_4Days", "Il y a 4 jours" + dayAgoResult);
            break;

            case 4:

                mTvDays5Ago.setText("Il y a 5 jours");

                Log.d("Test_5Days", "Il y a 5 jours" + dayAgoResult);
            break;

            case 5:

                mTvDays6Ago.setText("Il y a 6 jours");

                Log.d("Test_6Days", "Il y a 6 jours" + dayAgoResult);
            break;

            case 6:

                mTvDays7Ago.setText("Il y a 7 jours");

                Log.d("Test_7Days", "Il y a 7 jours" + dayAgoResult);
            break;

        }

        return dayAgoResult;

    }

}

































































