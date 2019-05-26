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

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.view.View.*;


public class HistoryActivity extends AppCompatActivity {


    private static Object Date;
    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;
    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private View mYesterday;
    private Date dateYesterday;
    private java.util.Date date2DayAgo;


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


//        compare();
//        Log.d("Test_compareDate", "Yesterday was" + compare() );
        compareDate();
        Log.d("Test_compareDate", "Yesterday was" + compareDate());
        mTvYesterday.setText(date);
    }

    public java.util.Date compareDate() {

        Calendar toDayCalendar = Calendar.getInstance();
        Date dateToday = toDayCalendar.getTime();


        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date dateYesterday = yesterdayCalendar.getTime();

            if (dateToday.compareTo(dateYesterday) < -1) {
                
            }
          return dateYesterday;
    }
}

//    private String compare (String stringData, String dateYesterday) {
//        String result = "";
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateMonth = null;
//
//        try {
//            Date = simpleDateFormat.parse(stringData);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        Long millisSecond = convertToMillisSecond(dateMonth);
//        Long currencyMillisSecond = System.currentTimeMillis();
//
//        if (currencyMillisSecond > millisSecond) {
//            Long diff = currencyMillisSecond - millisSecond;
//            Long day = 86400000L;
//
//            if (diff < day && getCurrentDayOfMonth() == getDateDayOfMonth(dateMonth)) {
//                result = convertMillisSecondsToDateString(millisSecond);
//            } else if (diff < (day * 2) && getCurrentDayOfMonth() - 1 == getDateDayOfMonth(dateMonth)) {
//                result = dateYesterday;
//
//            } else {
//                result = convertMillisSecondsToDateString(millisSecond);
//
//            }
//        }
//
//        return result;
//
//
//    }
//
//
//    private static int getDateDayOfMonth(Date dateMonth) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(dateMonth);
//        return calendar.get(Calendar.DAY_OF_MONTH);
//    }
//
//    public static int getCurrentDayOfMonth() {
//        Calendar calendar = Calendar.getInstance();
//        return calendar.get(Calendar.DAY_OF_MONTH);
//    }
//
//    public static String convertMillisSecondsToDateString(long millisSecond) {
//        Date dateMonth = new Date(millisSecond);
//        Format formatter = new SimpleDateFormat("dd / MM / yyyy");
//        return formatter.format(dateMonth);
//    }
//
//    public static long convertToMillisSecond(Date dateMonth) {
//        return dateMonth.getTime();
//    }













































