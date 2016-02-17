package com.example.administrator.focalpoint;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class fillInTheBlank extends Activity {
    Timer questionTimer;
    Button b_Submit;

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
                        TextView count = (TextView) findViewById(R.id.QuestionTimeViewCount);
                        count.setText(String.valueOf(TimeCounter));
                        TimeCounter--;
                        if (TimeCounter == -1) {
                            questionTimer.cancel();
                        }

                        b_Submit = (Button) findViewById(R.id.SubmitQuestion);
                        b_Submit.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                EditText inputOfUser = (EditText) findViewById(R.id.editText);
                                if (inputOfUser.getText().toString().equals("1812")) {
                                    Toast.makeText(getApplicationContext(), "It Worked", Toast.LENGTH_SHORT).show();
                                } else {

                                }

                            }
                        });


                    }
                });

            }
        }, 1000, 1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_the_blank);


    }


}
