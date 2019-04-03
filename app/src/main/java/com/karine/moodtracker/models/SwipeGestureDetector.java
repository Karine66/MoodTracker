package com.karine.moodtracker.models;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.karine.moodtracker.controllers.MainActivity;



/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class SwipeGestureDetector extends GestureDetector {

    private final static int DELTA_MIN = 50;

    //Constructor
    public SwipeGestureDetector(final MainActivity context) {
        super(context, new GestureDetector.SimpleOnGestureListener() {

            //Swipe intercepting : e1 start movement and e2 end on movement
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("Debug", e1 + "-" + e2);

                //calculation end point and start point
                float deltaY = e2.getY() - e1.getY();
                if (Math.abs(deltaY) == Math.abs(deltaY))
                    if (Math.abs(deltaY) >= DELTA_MIN) {
                        if (deltaY < 0) {
                            context.onSwipe(SwipeDirection.BOTTOM_TO_TOP);
                            return true;

                        } else {
                            context.onSwipe(SwipeDirection.TOP_TO_BOTTOM);
                            return true;
                        }
                    }

                return false;
            }

        });

    }
    //position enum
    public static enum SwipeDirection {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }
}