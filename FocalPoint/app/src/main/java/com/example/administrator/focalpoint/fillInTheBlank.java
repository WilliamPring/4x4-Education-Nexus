package com.example.administrator.focalpoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class fillInTheBlank extends Activity {
    private Timer questionTimer;
    private Button b_Submit;
    private List<UserChoice> answers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blank);

        if(getIntent().hasExtra("answers")){
            answers = getIntent().getParcelableArrayListExtra("answers");
        }

        answers.add(new UserChoice(5, 0));

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

                        b_Submit = (Button) findViewById(R.id.SubmitQuestion);
                        b_Submit.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                EditText inputOfUser = (EditText) findViewById(R.id.editText);
                                Intent intent = new Intent(getApplicationContext(), trueOrFalse.class);

                                if (inputOfUser.getText().toString().equals("1812")) {
                                    Toast.makeText(getApplicationContext(), "It Worked", Toast.LENGTH_SHORT).show();
                                    answers.get(1).setAnswerChoice(1);
                                } else {

                                }
                                intent.putParcelableArrayListExtra("answers", (ArrayList)answers);
                                startActivity(intent);

                            }
                        });


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
