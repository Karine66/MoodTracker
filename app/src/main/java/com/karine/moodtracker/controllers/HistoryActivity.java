package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.karine.moodtracker.R;
import com.karine.moodtracker.models.Adapter;

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

        mRecyclerView = findViewById(R.id.history_view);


        mPreferences = getSharedPreferences("text", Context.MODE_PRIVATE);
        Log.d("Testing", mPreferences.getString("saved", ""));
        try {
            saved = new JSONObject(mPreferences.getString("saved", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);


        mRecyclerView.setAdapter(new Adapter());

    }


}


