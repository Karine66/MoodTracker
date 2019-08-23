package com.karine.moodtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.karine.moodtracker.R;
import com.karine.moodtracker.models.AlertDialog;
import com.karine.moodtracker.models.Mood;
import com.karine.moodtracker.models.MoodStorage;
import com.karine.moodtracker.models.SwipeGestureDetector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private SwipeGestureDetector mGestureDetector;
    private Mood mMood;
    private View mView;
    private int counter = 3;
    private ImageView mNoteAdd;
    private ImageView mHistory;
    private ImageView mShare;
    private MoodStorage mMoodStorage;
    private String mComment;
    private ImageView mImagePic;


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void decreaseCounter() {
        counter--;
    }

    public void increaseCounter() {
        counter++;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaration
        mMoodStorage = new MoodStorage(this);
        //Catch Last Mood
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        if (mMoodStorage.getMoodStorage().size() > 0 && daysBetween(dayDate, mMoodStorage.getDateStorage().get(mMoodStorage.getDateStorage().size() - 1)) == 0)
            counter = mMoodStorage.getMoodStorage().get(mMoodStorage.getMoodStorage().size() - 1);

            getWindow().getDecorView().setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[counter]);
            mImagePic = findViewById(R.id.view);
            mImagePic.setImageResource(Mood.ARRAY_MOODS[counter]);

        //Declaration
        mMood = new Mood(counter);
        mView = this.getWindow().getDecorView();
        mNoteAdd = findViewById(R.id.note_add_btn);
        mHistory = findViewById(R.id.history_black_button);
        mShare = findViewById(R.id.share_button);
        //Instatiation SwipeGestureDetector
        mGestureDetector = new SwipeGestureDetector(this, mMood, mView);

        saveBackground();
        saveComment();
        saveDate();

        //Connect buttons
        mNoteAdd.setOnClickListener(new AlertDialog(MainActivity.this, mMoodStorage));

        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(myIntent);
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("message/rfc822");

                if (mMoodStorage.getCommentStorage() == null || mMoodStorage.getCommentStorage().isEmpty() || mMoodStorage.getCommentStorage().get(mMoodStorage.getCommentStorage().size() - 1) == null) {
                    intent1.putExtra(Intent.EXTRA_TEXT, convertStringMood(mMoodStorage.getMoodStorage().get(mMoodStorage.getMoodStorage().size() - 1)));
                } else {
                    intent1.putExtra(Intent.EXTRA_TEXT, mMoodStorage.getCommentStorage().get(mMoodStorage.getCommentStorage().size() - 1));
                }
                intent1.putExtra(Intent.EXTRA_SUBJECT, "Mon Humeur du jour !");
                startActivity(Intent.createChooser(intent1, "Envoyer un mail"));
            }
        });
    }


    public void saveDate() {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());
        mMoodStorage.dateStoreAdd(dayDate);
        mMoodStorage.saveDateStore();

        Log.d("Test_DateStorage", "DateStorage" + mMoodStorage.getDateStorage());
    }

    public void saveBackground() {
        mMoodStorage.moodStoreAdd(mMood);
        mMoodStorage.saveMoodStore();

        Log.d("Test", "Mood" + mMoodStorage.getMoodStorage());
    }

    public void saveComment() {
        mMoodStorage.commentStoreAdd(mComment);
        mMoodStorage.saveCommentStore();
    }

    //interceps all event relative to touch and give to GestureDetector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public String convertStringMood(int index) {

        String convertMood = "";

        switch (index) {
            case 0:
                convertMood = "Je suis de très mauvaise humeur !!!";
                break;
            case 1:
                convertMood = "Je suis de mauvaise humeur !!";
                break;
            case 2:
                convertMood = "Je suis d'une humeur normale";
                break;
            case 3:
                convertMood = "Je suis de bonne humeur !";
                break;
            case 4:
                convertMood = "Je suis de très bonne humeur !!";
                break;
        }
        return convertMood;
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
}








