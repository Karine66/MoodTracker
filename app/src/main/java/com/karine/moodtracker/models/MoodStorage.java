package com.karine.moodtracker.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class MoodStorage {

    private Context context;
    private SharedPreferences.Editor editorStore;
    public ArrayList<Integer> moodStorage;

    //constructor
    public MoodStorage(ArrayList<Integer> moodStorage) {

        this.moodStorage = moodStorage;
        retrieveMoodStore();
    }


    public void moodStoreAdd(Mood mood) {

        moodStorage.add(mood.getSelectedMood());

    }

    public List<Integer> getMoodStorage() {

        return moodStorage;

    }

    public void saveMoodStore() {
        SharedPreferences sharedPref = context.getSharedPreferences("Storage", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        //List<Integer> moodStorage = new ArrayList<>();
        SharedPreferences.Editor editStore = sharedPref.edit();
        editStore.putString("Storage", gson.toJson(moodStorage));
        editStore.apply();

        System.out.println(moodStorage);


    }

    public void retrieveMoodStore() {

        SharedPreferences sharedPref = context.getSharedPreferences("Storage", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPref.getString("Storage", null);
        //List<Integer> moodStorage = null;
        if(json!= null)
            moodStorage = gson.fromJson(json, new TypeToken<List<Integer>>() {}.getType());

        Log.d("Test_MoodStore", "Essai Store" + moodStorage);
    }
}










