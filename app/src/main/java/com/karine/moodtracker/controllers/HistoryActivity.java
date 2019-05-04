package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.karine.moodtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class HistoryActivity extends AppCompatActivity {

    private Context mContext;
    private JSONObject dayDate;
    private Calendar mCalendar;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private JSONObject mSaved;
    private ImageView mHistorybtn1;
    private EditText et;
    private LinearLayout mYesterday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setDayDate(dayDate);


        mYesterday = (LinearLayout) findViewById(R.id.yesterday);

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



                Toast.makeText(HistoryActivity.this, (CharSequence) getSaved(), Toast.LENGTH_SHORT).show();
            }

        });

    }






    public JSONObject getSaved() {
        return mSaved;
    }

    public void setSaved(JSONObject saved) {
        mSaved = saved;
    }



    public JSONObject getDayDate() {
        return dayDate;
    }

    public void setDayDate(JSONObject dayDate) {

        this.dayDate = dayDate;

    }

}









