package com.karine.moodtracker.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.karine.moodtracker.R;

public class TextActivity extends AppCompatActivity {

    CalendarView mcalendarView;
    TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        mcalendarView = (CalendarView) findViewById(R.id.activity_calendar);
        mtextView = (TextView) findViewById(R.id.date_view);

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {


               mtextView.setText("Le  : " + dayOfMonth + " / " + (month+1) + " / " + year);

            }
        });
    }
}
