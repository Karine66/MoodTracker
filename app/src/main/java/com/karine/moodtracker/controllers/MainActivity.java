package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.karine.moodtracker.R;
import com.karine.moodtracker.models.AlertDialog;
import com.karine.moodtracker.models.Mood;
import com.karine.moodtracker.models.MoodStorage;
import com.karine.moodtracker.models.SwipeGestureDetector;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private SwipeGestureDetector mGestureDetector;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Mood mMood;
    private View mView;
    private int counter = 3;
    private ImageView mNoteAdd;
    private ImageView mHistory;
    private EditText et;
    private JSONObject mSaved = new JSONObject();
    private MoodStorage mMoodStorage;
    private ArrayList <Integer> moodStorage;
    private String mdayDate;
    private String mComment;
    private ImageView mShare;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void decreaseCounter() {
        counter--;
    }

    public void increaseCOunter() {
        counter++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundResource(R.color.light_sage);
        //Declaration
        mMoodStorage = new MoodStorage(this);
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

        final Intent intent = getIntent();
        if (intent.getIntExtra("position", -1) != -1) {

                String comment = et.getText().toString();
        }
        mNoteAdd.setOnClickListener(new AlertDialog(MainActivity.this, mPreferences, mEditor, mSaved, mMoodStorage));
        //Connect History Button
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
                intent1.putExtra("storage", "");
               // intent1.putExtra(Intent.EXTRA_TEXT, mMoodStorage.getCommentStorage().get(5));
                intent1.putExtra(Intent.EXTRA_SUBJECT, "Mon Humeur du jour !");
                intent1.putExtra(Intent.EXTRA_EMAIL, new String[] {"kada1973@gmail.com"});
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

        Log.d("Test_DateStorage", "DateStorage"+ mMoodStorage.getDateStorage());
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
}




