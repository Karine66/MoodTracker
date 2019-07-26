package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
    private View view;
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
    private ViewGroup.LayoutParams params;
    private ViewGroup.LayoutParams params1;
    private ViewGroup.LayoutParams params2;
    private ViewGroup.LayoutParams params3;
    private ViewGroup.LayoutParams params4;
    private ViewGroup.LayoutParams params5;
    private ViewGroup.LayoutParams params6;
    private LinearLayout linearLayout;
    private int newColorSize;



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
        linearLayout = findViewById(R.id.yesterday);
        linearLayout = findViewById(R.id.days2_ago);
        linearLayout = findViewById(R.id.days3_ago);
        linearLayout = findViewById(R.id.days4_ago);
        linearLayout = findViewById(R.id.days5_ago);
        linearLayout = findViewById(R.id.days6_ago);
        linearLayout = findViewById(R.id.days7_ago);



        //déclaration
        moodStorage = new MoodStorage(this);


        retrieveBackground();
        retrieveComment();
        retrieveDate();

    }

    private void retrieveComment() {

        olderComments(moodStorage);
    }

    public void retrieveBackground() {


        colorBackground(moodStorage, linearLayout);
       // colorsSize(moodStorage,linearLayout);
//
//        ViewGroup.LayoutParams params = view6.getLayoutParams();
//       // params.width =40;
//        params.width =convertDpToPixel(360, getApplicationContext());
//        view6.setLayoutParams(params);
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

        switch ((int) dayAgoResult) {
            case 0:
               //textView.setText("Aujourd'hui");
                break;
            case 1:
                textView.setText("Hier");
                break;
            case 2:
                textView.setText("Avant-hier");
                break;
            case 3: case 4: case 5: case 6:
                textView.setText("Il y a " + dayAgoResult +" "+"jours");
                break;
            case 7:
                textView.setText("Il y a une semaine");
        }
        return dayAgoResult;
    }

    public void colorBackground(MoodStorage moodStorage, LinearLayout linearLayout) {

        newColorSize=colorsSize(moodStorage);
        ViewGroup.LayoutParams params =  linearLayout.getLayoutParams();


        if (moodStorage.getMoodStorage().size() >= 1) {
            linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(0)]);
            params.width = newColorSize;
           linearLayout.setLayoutParams(params);
            if (moodStorage.getMoodStorage().size() >= 2)
                linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(1)]);
            params.width = newColorSize;
            linearLayout.setLayoutParams(params);

            if (moodStorage.getMoodStorage().size() >= 3)
                linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(2)]);
               params.width = newColorSize;
             //   linearLayout.setLayoutParams(params);
            if (moodStorage.getMoodStorage().size() >= 4)
               linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(3)]);
               params.width = newColorSize;
               linearLayout.setLayoutParams(params);
            if (moodStorage.getMoodStorage().size() >= 5)
                linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(4)]);
                params.width =newColorSize;
               linearLayout.setLayoutParams(params);
            if (moodStorage.getMoodStorage().size() >= 6)
                linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(5)]);
                params.width =newColorSize;
              linearLayout.setLayoutParams(params);
            if (moodStorage.getMoodStorage().size() >= 7)
                linearLayout.setBackgroundResource(ARRAY_BACKGROUND_COLOR[moodStorage.getMoodStorage().get(6)]);
                params.width = newColorSize;
               linearLayout.setLayoutParams(params);
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

    public void olderComments(final MoodStorage moodStorage) {

        if (moodStorage.getCommentStorage().size() < 7 || moodStorage.getCommentStorage().size() >= 7 && (moodStorage.getCommentStorage().get(6) == null ||
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
        if (moodStorage.getCommentStorage().size() < 6 || moodStorage.getCommentStorage().size() >= 6 && (moodStorage.getCommentStorage().get(5) == null ||
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
        if (moodStorage.getCommentStorage().size() < 5 || moodStorage.getCommentStorage().size() >= 5 && (moodStorage.getCommentStorage().get(4) == null ||
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

        if (moodStorage.getCommentStorage().size() < 4 || moodStorage.getCommentStorage().size() >= 4 && (moodStorage.getCommentStorage().get(3) == null ||
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
        if (moodStorage.getCommentStorage().size() < 3 || moodStorage.getCommentStorage().size() >= 3 && (moodStorage.getCommentStorage().get(2) == null ||
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
        if (moodStorage.getCommentStorage().size() < 2 || moodStorage.getCommentStorage().size() >= 2 && (moodStorage.getCommentStorage().get(1) == null ||
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
        if (moodStorage.getCommentStorage().size() < 1 || moodStorage.getCommentStorage().size() >= 1 && (moodStorage.getCommentStorage().get(0) == null ||
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
    }

    public int convertDpToPixel(float dp, Context context) {
        return (int) (dp * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }


    public int colorsSize(MoodStorage moodStorage) {


        if (moodStorage.getMoodStorage().contains(R.color.faded_red)) {

            params.width = convertDpToPixel(50, getApplicationContext());

            if (moodStorage.getMoodStorage().contains(R.color.warm_grey)) {
                params.width = convertDpToPixel(80, getApplicationContext());

                if (moodStorage.getMoodStorage().contains(R.color.cornflower_blue_65)) {

                    params.width = convertDpToPixel(160, getApplicationContext());

                    if (moodStorage.getMoodStorage().contains(R.color.light_sage)) {

                        params.width = convertDpToPixel(260, getApplicationContext());

                        if (moodStorage.getMoodStorage().contains(R.color.banana_yellow)) {

                            params.width = convertDpToPixel(360, getApplicationContext());

                        }
                    }
                }
            }
        }
    return newColorSize;

    }

}




































































