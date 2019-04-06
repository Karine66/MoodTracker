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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        CalendarView calendarView = findViewById(R.id.activity_calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {


                String date = dayOfMonth + "/" + month + "/" + year +":";
                Intent intent = new Intent(TextActivity.this, MainActivity.class);
                intent.putExtra("date", date);

                startActivity(intent);

            }
        });
    }
}
