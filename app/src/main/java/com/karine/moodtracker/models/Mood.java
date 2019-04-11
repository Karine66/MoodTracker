package com.karine.moodtracker.models;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class Mood {

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

