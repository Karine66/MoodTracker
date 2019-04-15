package com.karine.moodtracker.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.karine.moodtracker.R;
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
    private int mCurrentMood; //for stock mood
    private EditText et;
    private JSONObject saved = new JSONObject();


    //Array moods
    public int[] arrayMoods = new int[]{

            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy,

    };

    //Array background colors

    public int[] arrayBackgroundColor = new int[]{

            R.color.faded_red,
            R.color.warm_grey,
            R.color.cornflower_blue_65,
            R.color.light_sage,
            R.color.banana_yellow,


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Instatiation SwipeGestureDetector
        mGestureDetector = new SwipeGestureDetector(this);

        //Declaration

        mMood = new Mood(counter);
        mView = this.getWindow().getDecorView();
        mNoteAdd = (ImageView) findViewById(R.id.note_add_btn);
        mHistory = (ImageView) findViewById(R.id.history_black_button);


        init();
        Intent intent = getIntent();
        if (intent.getIntExtra("position", -1) != -1) {
            try {
                String s = et.getText().toString();
                if (!mPreferences.getString("saved", "").equals(""))
                    saved = new JSONObject(mPreferences.getString("saved", ""));
                et.setText(saved.getString("saved" + intent.getIntExtra("position", 0)));
                s = saved.getString("saved" + intent.getIntExtra("position", 0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Box dialog open when click button
        mNoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instantiation layout in View
                LayoutInflater factory = (LayoutInflater) LayoutInflater.from(MainActivity.this);
                final View commentView = factory.inflate(R.layout.activity_comment, null);

                //create AlertDialog
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);

                //View to AlertDialog
                adb.setView(commentView);

                //AlertDialog title
                adb.setTitle("What is your Mood today ?");

                //Button "Save"
                adb.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Click on for validate Edittext
                        EditText et = (EditText) commentView.findViewById(R.id.mood_dialog);

                        String s = et.getText().toString();
                        if (!s.equals("")) {
                            try {
                                if (!mPreferences.getString("saved", "").equals(""))
                                    saved = new JSONObject(mPreferences.getString("saved", ""));
                                saved.put("saved" + saved.length(), s);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("testing", saved + "");
                            mEditor.putString("saved", saved.toString());
                            mEditor.apply();
                            et.setText("");
                            Intent intent1 = new Intent(MainActivity.this, HistoryActivity.class);
                            startActivity(intent1);
                        }
                    }
                });

                //Create "Cancel"Button
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                adb.show();

            }
        });

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
        et = findViewById(R.id.mood_dialog);
        mHistory = findViewById(R.id.history_black_button);
    }


    //interceps all event relative to touch and give to GestureDetector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
        ImageView imagePic = (ImageView) findViewById(R.id.view);


        switch (direction) {
            case TOP_TO_BOTTOM:

                mMood.setSelectedMood(counter);

                if (counter > 0) {
                    counter--;
                    imagePic.setImageResource(arrayMoods[counter]);
                    mView.setBackgroundResource(arrayBackgroundColor[counter]);
                    break;
                } else {
                    counter = 4;
                    imagePic.setImageResource(arrayMoods[counter]);
                    mView.setBackgroundResource(arrayBackgroundColor[counter]);
                }
                break;

            case BOTTOM_TO_TOP:

                mMood.setSelectedMood(counter);

                if (counter < 4) {
                    counter++;
                    imagePic.setImageResource(arrayMoods[counter]);
                    mView.setBackgroundResource(arrayBackgroundColor[counter]);
                    break;

                } else {
                    counter = 0;
                    imagePic.setImageResource(arrayMoods[counter]);
                    mView.setBackgroundResource(arrayBackgroundColor[counter]);
                }
                break;

            default:
                imagePic.setImageResource(arrayMoods[4]);
                mView.setBackgroundResource(arrayBackgroundColor[4]);
                break;


        }

    }

}



