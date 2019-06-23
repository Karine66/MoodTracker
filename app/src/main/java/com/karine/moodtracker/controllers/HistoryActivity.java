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

import com.karine.moodtracker.R;
import com.karine.moodtracker.models.MoodStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.view.View.INVISIBLE;
import static android.view.View.OnClickListener;
import static android.view.View.VISIBLE;
import static com.karine.moodtracker.models.Mood.ARRAY_BACKGROUND_COLOR;

public class HistoryActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private ImageView mHistorybtn1;
    private TextView mPastDays;
    private TextView mTvpastDays1;
    private TextView mTvpastDays2;
    private TextView mTvpastDays3;
    private TextView mTvpastDays4;
    private TextView mTvpastDays5;
    private TextView mTvpastDays6;
    private TextView mTvpastDays7;
    private long dayAgoResult;
    private SharedPreferences myPrefs;
    private String date;
    private String dayDate;
    private long diff;
    private long daysBetween;
    private SharedPreferences.Editor editorStore;
    private Context context;
    private MoodStorage moodStorage;
    private Date mDate;
    private View mDaysAgo;
    private View view0;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private MoodStorage dateStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //TextView
        mTvpastDays1 = findViewById(R.id.tvYesterday);
        mTvpastDays2 = findViewById(R.id.tvDays2Ago);
        mTvpastDays3 = findViewById(R.id.tvDays3Ago);
        mTvpastDays4 = findViewById(R.id.tvDays4Ago);
        mTvpastDays5 = findViewById(R.id.tvDays5Ago);
        mTvpastDays6 = findViewById(R.id.tvDays6Ago);
        mTvpastDays7 = findViewById(R.id.tvDays7Ago);
        //History button
        mHistorybtn1 = findViewById(R.id.history_btn_1);
        //View
        view0 = findViewById(R.id.yesterday);
        view1 = findViewById(R.id.days2_ago);
        view2 = findViewById(R.id.days3_ago);
        view3 = findViewById(R.id.days4_ago);
        view4 = findViewById(R.id.days5_ago);
        view5 = findViewById(R.id.days6_ago);
        view6 = findViewById(R.id.days7_ago);

        //déclaration
        moodStorage = new MoodStorage(this, mDate);
        dateStorage = new MoodStorage(this, mDate);

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

    public void retrieveBackground() {

        moodStorage.getMoodStorage();
        colorBackground(moodStorage, mDaysAgo);
        Log.d("Test_bg", "color" + moodStorage.getMoodStorage().get(0));

        //mYesterday.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);

    }

    public void retrieveDate() {

//        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
//        String date = myPrefs.getString("save_date", "");
//        Log.d("Test_Date", "onCreate() called with" + date);
//        Calendar mCalendar = Calendar.getInstance();
//        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String dayDate = jsonDateFormat.format(mCalendar.getTime());
        dateStorage.getDateStorage();
        olderDays(date, dayDate, mPastDays);

        //Log.d("Test_Compare", "Yesterday was" + dayAgoResult);

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

    public long olderDays(String date, String dayDate, TextView textView) {

        dayAgoResult = daysBetween(date, dayDate);
        dayAgoResult = 1;

        switch ((int) dayAgoResult) {
            case 1:

                mTvpastDays1.setText("Hier");

                break;

            case 2:

                mTvpastDays2.setText("Avant-hier");
                break;

            case 3:

                mTvpastDays3.setText("Il y a " + dayAgoResult + "jours");
                break;

            case 4:

                mTvpastDays4.setText("Il y a " + dayAgoResult + "jours");
                break;

            case 5:

                mTvpastDays5.setText("Il y a " + dayAgoResult + "jours");
                break;

            case 6:

                mTvpastDays6.setText("Il y a " + dayAgoResult + "jours");

                break;

            case 7:

                mTvpastDays7.setText("Il ya " + dayAgoResult + "jours");
                break;
        }

        return dayAgoResult;

    }

    public MoodStorage colorBackground(MoodStorage moodStorage, View view) {

        if (moodStorage != null) {

            view6.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);
            view5.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(1)]);
            view4.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(2)]);
            view3.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(3)]);
            view2.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(4)]);
            view1.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(5)]);
            view0.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(6)]);

        }
        return moodStorage;
    }
}


































































