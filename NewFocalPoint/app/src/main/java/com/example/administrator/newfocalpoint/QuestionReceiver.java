/*
* FILE : QuestionReceiver.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-04-15
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the main activity, with a drawer, that will hold the fragments.
*/

package com.example.administrator.newfocalpoint;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class QuestionReceiver extends BroadcastReceiver{


    //creates notification when the broadcast is received
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, main_drawer_activity.class);
        notificationIntent.putExtras(intent.getExtras());
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setAction("actionString" + System.currentTimeMillis());
        Log.d("NOTIFICATION", "bundle: " + intent.getExtras());
        int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Question is ready!")
                .setContentText("Click here to answer the next question.")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .getNotification();
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(123456, notification);
        abortBroadcast();

    }


}
