package com.karine.moodtracker.controllers;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.karine.moodtracker.R;
import com.karine.moodtracker.models.AlertDialog;
import com.karine.moodtracker.models.Mood;
import com.karine.moodtracker.models.SwipeGestureDetector;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {


    private SwipeGestureDetector mGestureDetector;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private Mood mMood;
    private View mView;
    private int counter = 0;
    private ImageSwitcher imagePic;
    private ImageView mNoteAdd;
    private ImageView mHistory;
    private EditText et;
    private JSONObject mSaved = new JSONObject();
    private SwipeGestureDetector.SwipeDirection mTopToBottom;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void decreaseCounter(){
        counter--;
    }

    public void increaseCOunter(){
        counter++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setBackgroundResource(R.color.light_sage);


        //Declaration

        mMood = new Mood(counter);
        mView = this.getWindow().getDecorView();
        mNoteAdd = (ImageView) findViewById(R.id.note_add_btn);
        mHistory = (ImageView) findViewById(R.id.history_black_button);

        //Instatiation SwipeGestureDetectorm
        mGestureDetector = new SwipeGestureDetector(this, mMood, mView);

        init();
        Intent intent = getIntent();
        if (intent.getIntExtra("position", -1) != -1) {
            try {
                String s = et.getText().toString();
                if (!mPreferences.getString("mSaved", "").equals(""))
                    mSaved = new JSONObject(mPreferences.getString("mSaved", ""));
                et.setText(mSaved.getString("mSaved" + intent.getIntExtra("position", 0)));
                s = mSaved.getString("mSaved" + intent.getIntExtra("position", 0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mNoteAdd.setOnClickListener(new AlertDialog(MainActivity.this, mPreferences, mEditor, mSaved));

        //Connect History Button
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(myIntent);

            }
        });

    }

    private void init() {
        mPreferences = getSharedPreferences("text", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.clear();
        mEditor.apply();
        et = findViewById(R.id.mood_dialog);
        mHistory = findViewById(R.id.history_black_button);

    }


    //interceps all event relative to touch and give to GestureDetector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



}



