package com.karine.moodtracker.models;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class MoodStorage extends  Mood {


    List<Integer> moodStorage = new ArrayList<>(getSelectedMood());

    

    public MoodStorage(int selectedMood) {
        super(selectedMood);

        System.out.println(moodStorage);
    }

}
