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
    private TextView mTvpastDays;
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
        mDaysAgo = findViewById(R.id.yesterday);
        mDaysAgo = findViewById(R.id.days2_ago);
        mDaysAgo = findViewById(R.id.days3_ago);
        mDaysAgo = findViewById(R.id.days4_ago);
        mDaysAgo = findViewById(R.id.days5_ago);
        mDaysAgo = findViewById(R.id.days6_ago);
        mDaysAgo = findViewById(R.id.days7_ago);

        //déclaration
        moodStorage = new MoodStorage(this);

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

        Log.d("Test_bg", "color" + moodStorage.getMoodStorage().get(0));

       //mYesterday.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);

    }

    public void retrieveDate() {

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");
        Log.d("Test_Date", "onCreate() called with" + date);
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        olderDays(date, dayDate, mTvpastDays, mDaysAgo);

        Log.d("Test_Compare", "Yesterday was" + dayAgoResult);

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

    public long olderDays(String date, String dayDate, TextView textView, View view) {


        dayAgoResult = daysBetween(date, dayDate);
       // dayAgoResult = 1;


        switch ((int) dayAgoResult) {
            case 1:

                textView.setText("Hier");
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(6)]);
                Log.d("Test_Days", "Hier");

                break;

            case 2:

                textView.setText("Avant-hier");
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(5)]);
                break;

            case 3: case 4: case 5: case 6:

                textView.setText("Il y a " + dayAgoResult + "jours");

                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(4)]);
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(3)]);
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(2)]);
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(1)]);

                break;

            case 7:

                textView.setText("Il ya " + dayAgoResult + "jours");
                view.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);
        }

        return dayAgoResult;

    }


}


































































