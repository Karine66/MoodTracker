package com.karine.moodtracker.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class MoodStorage {

    private Context mContext;
    private Date mDaydate;
    private SharedPreferences.Editor editorStore;
    public ArrayList<Integer> moodStorage;
    public ArrayList<String> dateStorage;
    private SharedPreferences myPrefs;
    private String dayDate;
    private SharedPreferences sharePrefsDate;

    //constructor
    public MoodStorage(Context context) {

        mContext = context;
        retrieveMoodStore();
        retrieveDateStore();
    }

    public void moodStoreAdd(Mood mood) {

        if (moodStorage.size() <= 6) {
            moodStorage.add(mood.getSelectedMood());


        } else if (moodStorage.size() >= 7) {
            moodStorage.remove(0);
            moodStorage.add(mood.getSelectedMood());

        }
    }

    public void dateStoreAdd(String dayDate) {
        if(dateStorage.size() <=6) {
            dateStorage.add(dayDate);

        }else if (dateStorage.size() >=7) {
            dateStorage.remove(0);
            dateStorage.add(dayDate);

        }
    }

    public List<Integer> getMoodStorage() {

        return moodStorage;
    }

    public ArrayList<String> getDateStorage() {

        return dateStorage;
    }

    public void saveMoodStore() {

        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editStore = sharedPref.edit();
        editStore.putString("Storage", gson.toJson(moodStorage));
        editStore.apply();

    }

    public void saveDateStore() {

        SharedPreferences sharePrefsDate = mContext.getSharedPreferences("save_dateStorage", MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor mEdit = sharePrefsDate.edit();
        mEdit.putString("save_dateStorage", gson.toJson(dateStorage));
        mEdit.apply();

    }

    public void retrieveMoodStore() {

        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("Storage", null);

        if (json != null) {
            moodStorage = gson.fromJson(json, new TypeToken<List<Integer>>() {
            }.getType());

        } else {
            moodStorage = new ArrayList<Integer>();
        }
    }

    public void retrieveDateStore() {

        sharePrefsDate = mContext.getSharedPreferences("save_dateStorage", MODE_PRIVATE);
        Gson gson = new Gson();
        String dateStore = sharePrefsDate.getString("save_dateStorage", null);

        if (dateStorage != null) {
            dateStorage = gson.fromJson(dateStore, new TypeToken<List<String>>() {
            }.getType());

        } else {
            dateStorage = new ArrayList<String>();
        }
    }
}











