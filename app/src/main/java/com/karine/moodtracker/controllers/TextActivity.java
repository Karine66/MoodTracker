package com.karine.moodtracker.controllers;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;

import com.karine.moodtracker.R;

import java.util.Calendar;

public class TextActivity extends AppCompatActivity {

    CalendarView mcalendarView;
    TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Calendar c = Calendar.getInstance();
        mcalendarView = (CalendarView) findViewById(R.id.activity_calendar);
        mcalendarView.setMinDate(c.getTimeInMillis());
        mtextView = (TextView) findViewById(R.id.date_view);


        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {


                mtextView.setText("Le  : " + dayOfMonth + " / " + (month + 1) + " / " + year);

            }
        });
    }
}
