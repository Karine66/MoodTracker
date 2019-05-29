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
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.karine.moodtracker.R;
import com.karine.moodtracker.models.AlertDialog;
import com.karine.moodtracker.models.Mood;
import com.karine.moodtracker.models.SwipeGestureDetector;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;




public class MainActivity extends AppCompatActivity {


    public static String getComment;
    private SwipeGestureDetector mGestureDetector;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences.Editor mEdit;
    private Mood mMood;
    private View mView;
    private int counter = 3;
    private ImageSwitcher imagePic;
    private ImageView mNoteAdd;
    private ImageView mHistory;
    private EditText et;
    private JSONObject mSaved = new JSONObject();
    private String comment;



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

        mMood = new Mood(counter);
        mView = this.getWindow().getDecorView();
        mNoteAdd = findViewById(R.id.note_add_btn);
        mHistory = findViewById(R.id.history_black_button);


        //Instatiation SwipeGestureDetector
        mGestureDetector = new SwipeGestureDetector(this, mMood, mView);

        saveBackground();
        saveDate();


        saveComment();
        Intent intent = getIntent();
        if (intent.getIntExtra("position", -1) != -1) {

                String comment = et.getText().toString();
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

    public void saveDate() {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());
        SharedPreferences myPrefs = getSharedPreferences("save_date", MODE_PRIVATE);
        SharedPreferences.Editor mEdit = myPrefs.edit();
        mEdit.putString("save_date", dayDate);
        mEdit.apply();
    }

    public void saveBackground() {

        Gson gson = new Gson();
        String json = gson.toJson(mMood);
        SharedPreferences sharedPreferences = getSharedPreferences("save_bg", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("save_bg",json);
        //editor.remove("save_bg");
        editor.apply();

        Log.d("Text", "Mood" + mMood.getSelectedMood());

    }

    private void saveComment() {

        mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        mEditor.remove("saved");
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




