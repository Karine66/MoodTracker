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
import static com.karine.moodtracker.models.Mood.ARRAY_BACKGROUND_COLOR;


public class HistoryActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;
    private TextView mTvpastDays;
      private long dayAgoResult;
    private SharedPreferences myPrefs;
    private String date;
    private String dayDate;
    private long diff;
    private long daysBetween;

    private View mDays2Ago;
    private View mYesterday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //TextView
        mTvpastDays = findViewById(R.id.tvYesterday);
        mTvpastDays = findViewById(R.id.tvDays2Ago);
        mTvpastDays = findViewById(R.id.tvDays3Ago);
        mTvpastDays = findViewById(R.id.tvDays4Ago);
        mTvpastDays = findViewById(R.id.tvDays5Ago);
        mTvpastDays = findViewById(R.id.tvDays6Ago);
        mTvpastDays = findViewById(R.id.tvDays7Ago);

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

        olderDays(date, dayDate, mTvpastDays);

        Log.d("Test_Compare", "Yesterday was" + dayAgoResult);

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

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return daysBetween;

    }

    private long olderDays (String date, String dayDate, TextView textView) {

        dayAgoResult= daysBetween(date, dayDate);
        dayAgoResult=1;

        switch ((int) dayAgoResult) {
            case 1 :

                textView.setText("Hier");

                Log.d("Test_Days", "Hier");

            break;

            case 2:

                textView.setText("Avant-hier");

            break;

            case 3: case 4: case 5: case 6:

               textView.setText("Il y a " + dayAgoResult + "jours");

            break;

            case 7:

                textView.setText("Il ya " + dayAgoResult + "jours");
        }

        return dayAgoResult;

    }

}

































































