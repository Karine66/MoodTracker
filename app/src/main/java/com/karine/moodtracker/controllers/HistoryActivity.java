package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.karine.moodtracker.R;
import com.karine.moodtracker.models.Mood;
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
    private ImageView mHistorybtn2;
    private ImageView mHistorybtn3;
    private ImageView mHistorybtn4;
    private ImageView mHistorybtn5;
    private ImageView mHistorybtn6;
    private ImageView mHistorybtn7;
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
    private MoodStorage moodStorage;
    private View view0;
    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private View view5;
    private View view6;
    private View textView;
    private ImageView imageView;
    private ArrayList<String> mCommentStorage;
    private SharedPreferences mPrefsComment;
    private Context mContext;
    private Mood mood;
    private int newSizeColor;
    public LinearLayout.LayoutParams color0;
    public LinearLayout.LayoutParams color1;
    public LinearLayout.LayoutParams color2;
    public LinearLayout.LayoutParams color3;
    public LinearLayout.LayoutParams color4;
    private LinearLayout linearLayout;

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
        mHistorybtn2 = findViewById(R.id.history_btn_2);
        mHistorybtn3 = findViewById(R.id.history_btn_3);
        mHistorybtn4 = findViewById(R.id.history_btn_4);
        mHistorybtn5 = findViewById(R.id.history_btn_5);
        mHistorybtn6 = findViewById(R.id.history_btn_6);
        mHistorybtn7 = findViewById(R.id.history_btn_7);
        //View
        view0 = findViewById(R.id.yesterday);
        view1 = findViewById(R.id.days2_ago);
        view2 = findViewById(R.id.days3_ago);
        view3 = findViewById(R.id.days4_ago);
        view4 = findViewById(R.id.days5_ago);
        view5 = findViewById(R.id.days6_ago);
        view6 = findViewById(R.id.days7_ago);
        //backgrounds

        //déclaration
        moodStorage = new MoodStorage(this);

        retrieveBackground();
        retrieveComment();
        retrieveDate();

    }

    private void retrieveComment() {

        olderComments(moodStorage, imageView);
    }

    public void retrieveBackground() {

        colorBackground(moodStorage);
       // colors(mood, linearLayout);

    }

    public void retrieveDate() {

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        String date = myPrefs.getString("save_date", "");

        olderDates(moodStorage);
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

        dayAgoResult = daysBetween(dayDate, date);
        // dayAgoResult = 1;
        switch ((int) dayAgoResult) {
            case 0:
                //aujourd'hui
                break;
            case 1:
                textView.setText("Hier");
                break;
            case 2:
                textView.setText("Avant-hier");
                break;
            case 3: case 4: case 5: case 6: case 7:
                textView.setText("Il ya " + dayAgoResult + "jours");
                break;
        }
        return dayAgoResult;
    }

    public void colorBackground(MoodStorage moodStorage) {

        if (moodStorage.getMoodStorage().size() >= 1) {
            view6.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);
            if (moodStorage.getMoodStorage().size() >= 2)
                view5.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(1)]);
            if (moodStorage.getMoodStorage().size() >= 3)
                view4.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(2)]);
            if (moodStorage.getMoodStorage().size() >= 4)
                view3.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(3)]);
            if (moodStorage.getMoodStorage().size() >= 5)
                view2.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(4)]);
            if (moodStorage.getMoodStorage().size() >= 6)
                view1.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(5)]);
            if (moodStorage.getMoodStorage().size() >= 7)
                view0.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(6)]);
        }
    }

    public void olderDates(MoodStorage moodStorage) {

        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        if (moodStorage.getDateStorage().size() >= 1) {
            olderDays(dayDate, moodStorage.getDateStorage().get(0), mTvpastDays7);
            if (moodStorage.getDateStorage().size() >= 2)
                olderDays(dayDate, moodStorage.getDateStorage().get(1), mTvpastDays6);
            if (moodStorage.getDateStorage().size() >= 3)
                olderDays(dayDate, moodStorage.getDateStorage().get(2), mTvpastDays5);
            if (moodStorage.getDateStorage().size() >= 4)
                olderDays(dayDate, moodStorage.getDateStorage().get(3), mTvpastDays4);
            if (moodStorage.getDateStorage().size() >= 5)
                olderDays(dayDate, moodStorage.getDateStorage().get(4), mTvpastDays3);
            if (moodStorage.getDateStorage().size() >= 6)
                olderDays(dayDate, moodStorage.getDateStorage().get(4), mTvpastDays2);
            if (moodStorage.getDateStorage().size() >= 7)
                olderDays(dayDate, moodStorage.getDateStorage().get(6), mTvpastDays1);
        }
    }

    public void olderComments(final MoodStorage moodStorage, ImageView imageView) {

        if (moodStorage.getCommentStorage().size() >= 7 && (moodStorage.getCommentStorage().get(6) == null ||
                moodStorage.getCommentStorage().get(6).trim().isEmpty())) {
            mHistorybtn1.setVisibility(INVISIBLE);
        } else {
            mHistorybtn1.setVisibility(VISIBLE);
            mHistorybtn1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(6), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (moodStorage.getCommentStorage().size() >= 6 && (moodStorage.getCommentStorage().get(5) == null ||
                moodStorage.getCommentStorage().get(5).trim().isEmpty())) {
            mHistorybtn2.setVisibility(INVISIBLE);
        } else {
            mHistorybtn2.setVisibility(VISIBLE);
            mHistorybtn2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(5), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (moodStorage.getCommentStorage().size() >= 5 && (moodStorage.getCommentStorage().get(4) == null ||
                moodStorage.getCommentStorage().get(4).trim().isEmpty())) {
            mHistorybtn3.setVisibility(INVISIBLE);
        } else {
            mHistorybtn3.setVisibility(VISIBLE);
            mHistorybtn3.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(4), Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (moodStorage.getCommentStorage().size() >= 4 && (moodStorage.getCommentStorage().get(3) == null ||
                moodStorage.getCommentStorage().get(3).trim().isEmpty())) {
            mHistorybtn4.setVisibility(INVISIBLE);
        } else {
            mHistorybtn4.setVisibility(VISIBLE);
            mHistorybtn4.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(3), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (moodStorage.getCommentStorage().size() >= 3 && (moodStorage.getCommentStorage().get(2) == null ||
                moodStorage.getCommentStorage().get(2).trim().isEmpty())) {
            mHistorybtn5.setVisibility(INVISIBLE);
        } else {
            mHistorybtn5.setVisibility(VISIBLE);
            mHistorybtn5.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(2), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (moodStorage.getCommentStorage().size() >= 2 && (moodStorage.getCommentStorage().get(1) == null ||
                moodStorage.getCommentStorage().get(1).trim().isEmpty())) {
            mHistorybtn6.setVisibility(INVISIBLE);
        } else {
            mHistorybtn6.setVisibility(VISIBLE);
            mHistorybtn6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(1), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (moodStorage.getCommentStorage().size() >= 1 && (moodStorage.getCommentStorage().get(0) == null ||
                moodStorage.getCommentStorage().get(0).trim().isEmpty())) {
            mHistorybtn7.setVisibility(INVISIBLE);
        } else {
            mHistorybtn7.setVisibility(VISIBLE);
            mHistorybtn7.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(HistoryActivity.this, moodStorage.getCommentStorage().get(0), Toast.LENGTH_SHORT).show();
                }

            });
        }
//        if(moodStorage.getCommentStorage().) {
//            imageView.setVisibility(INVISIBLE);
//        }
    }


    public int colors (Mood mood, LinearLayout linearLayout) {

        newSizeColor = colorsSize();

        switch (newSizeColor) {

            case 0:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 1:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 2:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 3:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 4:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 5:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
            case 6:
                linearLayout.setBackgroundColor(newSizeColor);
                break;
        }
        return newSizeColor;
    }



    public int colorsSize() {

        if (mood.getSelectedMood() == R.color.faded_red) {
            LinearLayout.LayoutParams color0 = new LinearLayout.LayoutParams(20, 1);
            if (mood.getSelectedMood() == R.color.warm_grey) {
                LinearLayout.LayoutParams color1 = new LinearLayout.LayoutParams(30, 1);
                if (mood.getSelectedMood() == R.color.cornflower_blue_65) {
                    LinearLayout.LayoutParams color2 = new LinearLayout.LayoutParams(40, 1);
                    if (mood.getSelectedMood() == R.color.light_sage) {
                        LinearLayout.LayoutParams color3 = new LinearLayout.LayoutParams(60, 1);
                        if (mood.getSelectedMood() == R.color.banana_yellow) {
                            LinearLayout.LayoutParams color4 = new LinearLayout.LayoutParams(100, 1);
                        }

                    }
                }
            }
        }
        return newSizeColor;
    }
}




































































