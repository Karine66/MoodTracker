package com.karine.moodtracker.models;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.karine.moodtracker.R;
import com.karine.moodtracker.controllers.HistoryActivity;
import com.karine.moodtracker.controllers.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

import static com.karine.moodtracker.models.Mood.ARRAY_BACKGROUND_COLOR;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private  HistoryActivity mHistoryActivity;
    private JSONObject saved;


    @NonNull
    @Override
    public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.row_item, viewGroup, false);
        Holder holder = new Holder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mRecyclerView.getChildPosition((v));
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("position", position);
                mHistoryActivity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int i) {
        try {

            holder.mHistoryDays.setBackgroundResource(saved.getInt("Arrays Background"+i));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        return ARRAY_BACKGROUND_COLOR.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        LinearLayout mHistoryDays;

        private Holder(@NonNull View itemView) {
            super(itemView);
            mHistoryDays = itemView.findViewById(R.id.history_days);

        }
    }
}