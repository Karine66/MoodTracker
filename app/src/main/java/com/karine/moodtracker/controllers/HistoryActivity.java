package com.karine.moodtracker.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karine.moodtracker.R;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.Calendar;
import java.util.Set;


public class HistoryActivity extends AppCompatActivity {


    private Context mContext;
    private JSONObject dayDate;
    private Calendar mCalendar;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private JSONObject mSaved;
    private ImageView mHistorybtn1;
    private EditText et;
    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private int mDate;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mTvYesterday = (TextView) findViewById(R.id.tvYesterday);


        mHistorybtn1 = (ImageView) findViewById(R.id.history_btn_1);


        mHistorybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPreferences = getSharedPreferences("saved", Context.MODE_PRIVATE);
                Log.d("Testing", mPreferences.getString("saved", ""));
                try {
                    mSaved = new JSONObject(mPreferences.getString("saved", ""));

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Toast.makeText(HistoryActivity.this, mPreferences.getString("saved", ""), Toast.LENGTH_SHORT).show();

            }

        });

        myPrefs = getSharedPreferences("save_date", Context.MODE_PRIVATE);
        try{
            dayDate = new JSONObject(myPrefs.getString("save_date", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mEditor = myPrefs.edit();
        mEditor.apply();
        mTvYesterday.setText(dayDate);

    }
}






















