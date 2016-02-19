package com.example.administrator.focalpoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class trueOrFalse extends Activity {
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private Timer questionTimer;

    private List<UserChoice> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false);


        if(getIntent().hasExtra("answers")){
            answers = getIntent().getParcelableArrayListExtra("answers");
        }

        answers.add(new UserChoice(6, 0));

        View swipe = findViewById(R.id.swipe_area);
        swipe.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeRight() {
                Toast.makeText(getApplicationContext(), "Swiped Right!", Toast.LENGTH_SHORT).show();
                answers.get(2).setAnswerChoice(1);
                Intent intent = new Intent(getApplicationContext(), Results.class);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), "Swiped Left!", Toast.LENGTH_SHORT).show();
                answers.get(2).setAnswerChoice(0);
                Intent intent = new Intent(getApplicationContext(), Results.class);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
            }

        });

        questionTimer = new Timer();
        questionTimer.scheduleAtFixedRate(new TimerTask() {
            int TimeCounter = 30;

            @Override
            public void run() {
                // TODO Auto-generated method stub
                runOnUiThread(new Runnable() {
                    public void run() {
                        TextView count = (TextView) findViewById(R.id.QuestionTimeViewCount);
                        count.setText(String.valueOf(TimeCounter));
                        TimeCounter--;
                        if (TimeCounter == -1) {
                            questionTimer.cancel();
                            Intent intent = new Intent(getApplicationContext(), Results.class);
                            intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                            startActivity(intent);
                        }

                    }
                });

            }
        }, 1000, 1000);
    }



    @Override
    protected void onPause() {
        super.onPause();
        questionTimer.cancel();
    }
}
