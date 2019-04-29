package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.karine.moodtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SharedPreferences mPreferences;
    private JSONObject saved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mPreferences = getSharedPreferences("text", Context.MODE_PRIVATE);
        Log.d("Testing", mPreferences.getString("saved", ""));
        try {
            saved = new JSONObject(mPreferences.getString("saved", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

       }



}



