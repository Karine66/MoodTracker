package com.karine.moodtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karine.moodtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SharedPreferences mPreferences;
    JSONObject saved;


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

    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        @NonNull
        @Override
        public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(HistoryActivity.this)
                    .inflate(R.layout.row_item, viewGroup, false);
            Holder holder = new Holder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mRecyclerView.getChildPosition((v));
                    Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter.Holder holder, int i) {
            try {
                holder.mTextView.setText(saved.getString("saved" + i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return saved.length();
        }

        private class Holder extends RecyclerView.ViewHolder {
            TextView mTextView;

            private Holder(@NonNull View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.text_view);
            }
        }
    }

}


