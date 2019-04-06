package com.karine.moodtracker.controllers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karine.moodtracker.R;
import com.karine.moodtracker.models.Mood;
import com.karine.moodtracker.models.SwipeGestureDetector;


public class MainActivity extends AppCompatActivity {

    private SwipeGestureDetector mGestureDetector;
    private Mood mMood;
    private int counter = 0;
    private ImageSwitcher imagePic;
    private View mView;
    private ImageView mNoteAdd;
    private ImageView mHistory;


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
        ImageView imagePic;
        imagePic = findViewById(R.id.activity_main_smiley);
        mNoteAdd = (ImageView) findViewById(R.id.note_add_btn);
        mHistory = (ImageView) findViewById(R.id.history_black_button);


        mNoteAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TextActivity.class);
                startActivity(myIntent);
            }
        });
    }

    //interceps all event relative to touch and give to GestureDetector
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
        ImageView imagePic = (ImageView) findViewById(R.id.activity_main_smiley);


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




        }

    }


}



