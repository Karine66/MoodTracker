package com.karine.moodtracker.models;

import com.karine.moodtracker.R;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class Mood {

    //Array moods
    public static final int[] ARRAY_MOODS = new int[]{

            R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy,

    };

    //Array background colors
    public static final int[] ARRAY_BACKGROUND_COLOR = new int[]{

            R.color.faded_red,
            R.color.warm_grey,
            R.color.cornflower_blue_65,
            R.color.light_sage,
            R.color.banana_yellow,


    };

    private int selectedMood;


    //constructor
    public Mood(int selectedMood) {

        this.selectedMood = selectedMood;
    }

    // create getter et setter

    public int getSelectedMood() {

        return selectedMood;
    }

    public void setSelectedMood(int selectedMood) {

        this.selectedMood = selectedMood;
    }
}

