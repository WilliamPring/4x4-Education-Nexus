/* FILE : NewQuestionService.java
 * PROJECT : Mobile Application Development
 * PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
 * FIRST VERSION : 2016-04-15
 * DESCRIPTION :
 * This file contains the service that will send the Change Question broadcast to the main activity
 */

package com.example.administrator.newfocalpoint;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class NewQuestionService extends Service {
    public NewQuestionService() {
    }

    private Bundle questionInfo;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent!=null) {
            String test = intent.getStringExtra("questionType");

            Log.d("SERVICE", "intent.getExtras: " + intent.getExtras().toString() + "   test:" + test);
            questionInfo = intent.getExtras();

            TimerThread tt = new TimerThread();
            tt.execute(String.valueOf(5), String.valueOf(1000)); //start a timer for 5 seconds
        }


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
            //create broadcast intent (CHANGE_QUESTION) to get caught by the activity or BroadcastReceiver
            Intent broadcastIntent = new Intent("com.example.administrator.newfocalpoint.CHANGE_QUESTION");
            broadcastIntent.putExtras(questionInfo);
            Log.d("POST EXECUTE", "extras: " + broadcastIntent.getExtras());
            sendOrderedBroadcast(broadcastIntent, null);

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
