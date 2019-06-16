package com.karine.moodtracker.models;


import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.karine.moodtracker.R;
import com.karine.moodtracker.controllers.MainActivity;
/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class SwipeGestureDetector extends GestureDetector {

    private final static int DELTA_MIN = 50;

    //Constructor
    public SwipeGestureDetector(final MainActivity context, final Mood mood, final View view) {
        super(context, new GestureDetector.SimpleOnGestureListener() {
            //Swipe intercepting : e1 start movement and e2 end on movement
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("Debug", e1 + "-" + e2);

                //calculation end point and start point
                float deltaY = e2.getY() - e1.getY();
                if (Math.abs(deltaY) >= DELTA_MIN) {
                    if (deltaY < 0) {
                        onSwipe(SwipeDirection.BOTTOM_TO_TOP);
                        return true;

                    } else {
                        onSwipe(SwipeDirection.TOP_TO_BOTTOM);
                        return true;
                    }
                }
                return false;
            }

            public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {
                ImageView imagePic = (ImageView) context.findViewById(R.id.view);

                context.saveDate();

                switch (direction) {
                    case BOTTOM_TO_TOP:

                        if (context.getCounter() > 0) {
                            context.decreaseCounter();
                            imagePic.setImageResource(Mood.ARRAY_MOODS[context.getCounter()]);
                            view.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[context.getCounter()]);

                            break;
                        } else {
                           context.setCounter(4);
                           imagePic.setImageResource(Mood.ARRAY_MOODS[context.getCounter()]);
                           view.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[context.getCounter()]);
                        }
                        break;

                    case TOP_TO_BOTTOM:

                        if (context.getCounter() < 4) {
                            context.increaseCOunter();
                            imagePic.setImageResource(Mood.ARRAY_MOODS[context.getCounter()]);
                            view.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[context.getCounter()]);

                            break;
                        }
                      else {
                            context.setCounter(0);
                            imagePic.setImageResource(Mood.ARRAY_MOODS[context.getCounter()]);
                            view.setBackgroundResource(Mood.ARRAY_BACKGROUND_COLOR[context.getCounter()]);

                       }
                        break;

                }
                mood.setSelectedMood(context.getCounter());
                context.saveBackground();
            }

        });
    }

    //position enum
    public static enum SwipeDirection {
        TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }
}
