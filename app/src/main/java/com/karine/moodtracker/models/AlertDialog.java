package com.karine.moodtracker.models;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.karine.moodtracker.R;



public class AlertDialog implements View.OnClickListener {

    private final MoodStorage mMoodStorage;
    private Context mContext;

    public AlertDialog(Context context, MoodStorage moodStorage) {
        mContext = context;
        mMoodStorage = moodStorage;
    }

    @Override
    public void onClick(View v) {

        //Instantiation layout in View
        LayoutInflater factory = (LayoutInflater) LayoutInflater.from(mContext);
        final View commentView = factory.inflate(R.layout.activity_comment, null);

        //create AlertDialog
        android.app.AlertDialog.Builder adb = new android.app.AlertDialog.Builder(mContext);

        //View to AlertDialog
        adb.setView(commentView);

        //AlertDialog title
        adb.setTitle("Quelle est votre humeur aujourd'hui ?");

        //Button "Save"
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Click on for validate Edittext
                EditText et = (EditText) commentView.findViewById(R.id.mood_dialog);
                String comment = et.getText().toString();
                Log.d("test_comment", comment + "");

                mMoodStorage.commentStoreAdd(comment);
                mMoodStorage.saveCommentStore();
            }
        });

        //Create "Cancel"Button
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        adb.show();
    }
}



