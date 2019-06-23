package com.karine.moodtracker.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class MoodStorage {

    private Context mContext;
    private Date mDate;
    private SharedPreferences.Editor editorStore;
    public ArrayList<Integer> moodStorage;
    private SharedPreferences myPrefs;

    //constructor
    public MoodStorage(Context context,Date date) {

        mDate = date;
        mContext = context;
        retrieveMoodStore();
    }

    public void moodStoreAdd(Mood mood) {

        if (moodStorage.size() <= 6) {
            moodStorage.add(mood.getSelectedMood());

        } else if (moodStorage.size() >= 7) {
            moodStorage.remove(0);
            moodStorage.add(mood.getSelectedMood());
        }
    }

    public List<Integer> getMoodStorage() {

        return moodStorage;
    }

    public Date getDate() {

        return mDate;
    }

    public void saveMoodStore() {
        //Save Moods
        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editStore = sharedPref.edit();
        editStore.putString("Storage", gson.toJson(moodStorage));
        editStore.apply();
//        //Save Dates
//        Calendar mCalendar = Calendar.getInstance();
//        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String storageDate = jsonDateFormat.format(mCalendar.getTime());
//        SharedPreferences sharePrefsDate = getSharedPreferences("save_date", MODE_PRIVATE);
//        SharedPreferences.Editor mEdit = sharePrefsDate.edit();
//        mEdit.putString("save_date", storageDate);
//        mEdit.apply();

    }

    public void retrieveMoodStore() {

        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPref.getString("Storage", null);
        //List<Integer> moodStorage = null;
        if (json != null) {
            moodStorage = gson.fromJson(json, new TypeToken<List<Integer>>() {
            }.getType());
        } else {
            moodStorage = new ArrayList<Integer>();
        }

        Log.d("Test_MoodStore", "Essai Store" + moodStorage);
    }
}










