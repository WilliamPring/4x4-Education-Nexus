package com.example.administrator.focalpoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HoldingMultipleChoice extends Activity {

    private List<UserChoice> answers = new ArrayList<>();
    private Timer questionTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holding_multiple_choice);

        answers.add(new UserChoice(4, 0));

        Button A = (Button) findViewById(R.id.questionAButton);

        A.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                answers.get(0).setAnswerChoice(1);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
                return false;
            }
        });
        Button B = (Button) findViewById(R.id.questionBButton);
        B.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "It Worked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                answers.get(0).setAnswerChoice(0);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
                return false;
            }
        });

        Button C = (Button) findViewById(R.id.questionCButton);
        B.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "It Worked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                answers.get(0).setAnswerChoice(0);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
                return false;
            }
        });
        Button D = (Button) findViewById(R.id.questionDButton);

        D.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "It Worked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                answers.get(0).setAnswerChoice(0);
                intent.putParcelableArrayListExtra("answers", (ArrayList) answers);
                startActivity(intent);
                return false;
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
                            Intent intent = new Intent(getApplicationContext(), fillInTheBlank.class);
                            intent.putParcelableArrayListExtra("answers", (ArrayList)answers);
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


