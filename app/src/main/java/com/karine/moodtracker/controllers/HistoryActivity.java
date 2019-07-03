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
import java.util.ArrayList;
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
    private TextView mTvpastDays1;
    private TextView mTvpastDays2;
    private TextView mTvpastDays3;
    private TextView mTvpastDays4;
    private TextView mTvpastDays5;
    private TextView mTvpastDays6;
    private TextView mTvpastDays7;
    private ArrayList<String> dateStorage;
    private long dayAgoResult;
    private SharedPreferences myPrefs;
    private String date;
    private String dayDate;
    private long diff;
    private long daysBetween;
    private SharedPreferences.Editor editorStore;
    private Context context;
    private MoodStorage moodStorage;
    private View mDaysAgo;
    private View view0;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private View textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //TextView
        mTvpastDays1= findViewById(R.id.tvYesterday);
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
        moodStorage = new MoodStorage(this);

        retrieveComment();
        retrieveBackground();
        retrieveDate();
    }

    private void retrieveComment() {

        mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
        ArrayList<String> commentStorage = moodStorage.getCommentStorage();
       // olderComments(moodStorage);
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
        colorBackground(moodStorage);
        //Log.d("Test_bg", "color" + moodStorage.getMoodStorage().get(0));
    }

    public void retrieveDate() {

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        //Log.d("Test_Date", "onCreate() called with" + date);
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());
        ArrayList<String> dateStorage = moodStorage.getDateStorage();
       daysBetween(date, dayDate);
        olderDates(moodStorage);
        Log.d("Test_Compare", "Yesterday was" + dayAgoResult);

    }

    public long daysBetween(String day1, String day2) {

        long daysBetween = 1;
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
                textView.setText("Hier");
                break;

            case 2:
                textView.setText("Avant-hier");
                break;

            case 3 : case 4 :case 5: case 6 : case 7:
                textView.setText("Il ya " + dayAgoResult + "jours");
                break;
        }
        return dayAgoResult;
    }

    public void colorBackground(MoodStorage moodStorage) {

         if (moodStorage.getMoodStorage().size()>= 1) {
            view6.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);
         if(moodStorage.getMoodStorage().size()>=2)
            view5.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(1)]);
         if(moodStorage.getMoodStorage().size()>=3)
            view4.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(2)]);
         if(moodStorage.getMoodStorage().size()>=4)
            view3.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(3)]);
         if(moodStorage.getMoodStorage().size()>=5)
            view2.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(4)]);
         if(moodStorage.getMoodStorage().size()>=6)
            view1.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(5)]);
         if(moodStorage.getMoodStorage().size()>=7)
            view0.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(6)]);

        }
}

    public void olderDates (MoodStorage moodStorage) {

        if(moodStorage.getDateStorage().size()>=1) {
          olderDays(date, moodStorage.getDateStorage().get(0), mTvpastDays7);
        if(moodStorage.getDateStorage().size()>=2)
            olderDays(date, moodStorage.getDateStorage().get(1), mTvpastDays6);
        if(moodStorage.getDateStorage().size()>=3)
            olderDays(date, moodStorage.getDateStorage().get(2), mTvpastDays5);
        if(moodStorage.getDateStorage().size()>=4)
            olderDays(date, moodStorage.getDateStorage().get(3), mTvpastDays4);
        if(moodStorage.getDateStorage().size()>=5)
            olderDays(date, moodStorage.getDateStorage().get(4), mTvpastDays3);
        if(moodStorage.getDateStorage().size()>=6)
            olderDays(date, moodStorage.getDateStorage().get(4), mTvpastDays2);
        if(moodStorage.getDateStorage().size()>=7)
            olderDays(date, moodStorage.getDateStorage().get(6), mTvpastDays1);
        }
    }

//    public void olderComments (MoodStorage moodStorage) {
//        if(moodStorage.getCommentStorage().size()>=1) {
//            mHistorybtn1.s
//        }
//    }

}


































































