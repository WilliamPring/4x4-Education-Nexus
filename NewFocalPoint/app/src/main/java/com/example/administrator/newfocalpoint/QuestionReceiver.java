package com.example.administrator.newfocalpoint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Matt on 4/14/2016.
 */
public class QuestionReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        String questionType = intent.getStringExtra("questionType");

    }


}
