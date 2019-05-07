package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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


public class HistoryActivity extends AppCompatActivity {


    private Context mContext;
    private JSONObject dayDate;
    private Calendar mCalendar;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private JSONObject mSaved;
    private ImageView mHistorybtn1;

    private TextView mTvYesterday;
    private SharedPreferences myPrefs;
    private JSONObject mDate;


    private String retrieveDate;
    private SharedPreferences.Editor mEdit;
    private String retrieveComment;
    private EditText et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        retrieveDate = "save_date";
        retrieveComment = "text";

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
                

                mEdit.apply();



                Toast.makeText(HistoryActivity.this, mPreferences.getString("saved", ""), Toast.LENGTH_SHORT).show();

            }

        });
        //Retrieve Date

        retrieveDate = "save_date";
        myPrefs = getSharedPreferences("date", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        if (intent.getIntExtra("date", -1) != -1) {
            try {
                String s =mTvYesterday.getText().toString();
                if (!mPreferences.getString("save_date", "").equals(""))
                    dayDate = new JSONObject(myPrefs.getString("save_date", ""));
                mTvYesterday.setText(dayDate.getString("date" + intent.getIntExtra("date", 0)));
                s = dayDate.getString("date" + intent.getIntExtra("date", 0));
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d("TestDate", myPrefs.getString("save_date", ""));


            mEdit = myPrefs.edit();
            mEdit.putString("save_date", dayDate.toString());

            mEdit.apply();
            retrieveDate = mTvYesterday.getText().toString();


            mTvYesterday.setText(retrieveDate);

        }
    }
}































