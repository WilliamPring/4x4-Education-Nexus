package com.example.administrator.newfocalpoint;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.util.Random;

public class NewQuestionService extends Service {
    public NewQuestionService() {
    }

    private TimerThread tt;
    private Bundle questionInfo;





    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        questionInfo = intent.getExtras();
        tt = new TimerThread();
        tt.execute(String.valueOf(5), String.valueOf(1000));

        Intent notificationIntent = new Intent("com.example.administrator.newfocalpoint.CHANGE_QUESTION");
        notificationIntent.putExtras(questionInfo);
        //sendBroadcast(notificationIntent);


        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setAction("actionString" + System.currentTimeMillis());

        int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Question is ready!")
                .setContentText("Click here to answer the next question.")
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(123456, notification);


        return START_STICKY;
    }

    //thread for counting down time
    private class TimerThread extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute(){
        }



        @Override
        protected String doInBackground(String... params){
            try{
                int loopCount = Integer.parseInt(params[0]);
                long waitTime = Long.parseLong(params[1]);
                for(int i = 0; i < loopCount; i++){
                    Thread.sleep(waitTime);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return String.valueOf(System.currentTimeMillis());
        }

        @Override
        protected void onPostExecute(String result){
            //launch next question here
            //start next fragment somehow?
            //create notification and create next fragment


        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
