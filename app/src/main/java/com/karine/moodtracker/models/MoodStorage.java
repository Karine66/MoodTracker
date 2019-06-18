package com.karine.moodtracker.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class MoodStorage {


    private Context mContext;
    private SharedPreferences.Editor editorStore;
    public ArrayList<Integer> moodStorage;

    //constructor
    public MoodStorage(Context context) {

        mContext = context;
        retrieveMoodStore();
    }

    public void moodStoreAdd(Mood mood) {

        moodStorage.add(mood.getSelectedMood());
    }



    public List<Integer> getMoodStorage() {

        return moodStorage;

    }

    public void saveMoodStore() {

        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editStore = sharedPref.edit();
        editStore.putString("Storage", gson.toJson(moodStorage));
        editStore.apply();

    }

    public void retrieveMoodStore() {

        SharedPreferences sharedPref = mContext.getSharedPreferences("Storage", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPref.getString("Storage", null);
        //List<Integer> moodStorage = null;
        if (json != null){
            moodStorage = gson.fromJson(json, new TypeToken<List<Integer>>() {
            }.getType());
         }else{
        moodStorage=new ArrayList<Integer>();
    }

        Log.d("Test_MoodStore", "Essai Store" + moodStorage);
    }


}










