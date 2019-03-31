package com.karine.moodtracker.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.karine.moodtracker.R;


public class MainActivity extends AppCompatActivity {

    private SwipeGestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instatiation SwipeGestureDetector
        mGestureDetector = new SwipeGestureDetector(this);

    }

    //interceps all event relative to touch and give to GestureDetector
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
        String message = "";
        switch (direction) {
            case TOP_TO_BOTTOM:
                message = "To to bottom swipe";
                break;
            case BOTTOM_TO_TOP:
                message = "Bottom to Top swipe";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}



