package com.example.administrator.focalpoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class fillInTheBlank extends Activity {
    Timer questionTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        questionTimer = new Timer();
        questionTimer.scheduleAtFixedRate(new TimerTask() {
        int TimeCounter = 30;
            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        TextView count = (TextView) findViewById (R.id.QuestionTimeViewCount);
                        count.setText(String.valueOf(TimeCounter));
                        TimeCounter--;
                        if (TimeCounter == -1)
                        {
                            questionTimer.cancel();
                        }
                    }
                });

            }
        }, 1000, 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blank);
    }
}
