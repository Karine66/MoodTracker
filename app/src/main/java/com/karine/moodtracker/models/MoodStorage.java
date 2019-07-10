package com.karine.moodtracker.models;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

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

    public ArrayList<Integer> moodStorage;
    public ArrayList<String> dateStorage;
    public ArrayList<String> commentStorage;
    private Context mContext;
    private Date mDaydate;
    private SharedPreferences.Editor editorStore;
    private SharedPreferences myPrefs;
    private String dayDate;
    private SharedPreferences sharePrefsDate;
    private SharedPreferences mPrefsComment;
    private SharedPreferences.Editor mEditComment;
    private EditText et;

    //constructor
    public MoodStorage(Context context) {

        mContext = context;
        retrieveMoodStore();
        retrieveDateStore();
        retrieveCommentStore();
    }

    public void moodStoreAdd(Mood mood) {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        if(moodStorage.size()<=6)
            moodStorage.add(mood.getSelectedMood());

        if (moodStorage.indexOf(dayDate) <=0 && moodStorage.size() <= 6) {
            moodStorage.remove(mood.getSelectedMood());
            moodStorage.add(mood.getSelectedMood());
        } else if (moodStorage.size() >= 7) {
            moodStorage.remove(0);
            moodStorage.add(mood.getSelectedMood());
        }
        System.out.println(moodStorage);
    }


    public void dateStoreAdd(String dayDate) {

        if (!dateStorage.contains(dayDate)) {
            if (dateStorage.size() >= 7) {
                dateStorage.remove(0);
            }
            dateStorage.add(dayDate);
        }
    }

    public void commentStoreAdd(String comment) {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat jsonDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dayDate = jsonDateFormat.format(mCalendar.getTime());

        if(commentStorage.size()<= 6)
            commentStorage.add(comment);

        if (commentStorage.indexOf(dayDate) <=0 && commentStorage.size() <= 6) {
            commentStorage.remove(comment);
            commentStorage.add(comment);

        } else if (commentStorage.size() >= 7) {

                commentStorage.remove(0);
                commentStorage.add(comment);
            }
            System.out.println(commentStorage);
        }

    public List<Integer> getMoodStorage() {
        return moodStorage;
    }

    public ArrayList<String> getDateStorage() {
        return dateStorage;
    }

    public ArrayList<String> getCommentStorage() {
        return commentStorage;
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

    public void saveCommentStore() {

        SharedPreferences mPrefsComment = mContext.getSharedPreferences("save_commentStorage", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor mEditComment = mPrefsComment.edit();
        mEditComment.putString("save_commentStorage", gson.toJson(commentStorage));
        mEditComment.apply();
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

        if (dateStore != null) {
            dateStorage = gson.fromJson(dateStore, new TypeToken<List<String>>() {
            }.getType());
        } else {
            dateStorage = new ArrayList<String>();
        }
    }

    public void retrieveCommentStore() {

        mPrefsComment = mContext.getSharedPreferences("save_commentStorage", MODE_PRIVATE);
        Gson gson = new Gson();
        String commentStore = mPrefsComment.getString("save_commentStorage", null);

        if (commentStore != null) {
            commentStorage = gson.fromJson(commentStore, new TypeToken<List<String>>() {
            }.getType());
        } else {
            commentStorage = new ArrayList<String>();
        }
    }
}











