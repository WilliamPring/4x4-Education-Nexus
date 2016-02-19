package com.example.administrator.focalpoint;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Results extends Activity {

    private List<UserChoice> answers = new ArrayList<>();

    private TextView q4;
    private TextView q5;
    private TextView q6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        q4 = (TextView) findViewById(R.id.q4correct);
        q5 = (TextView) findViewById(R.id.q5correct);
        q6 = (TextView) findViewById(R.id.q6correct);

        if(getIntent().hasExtra("answers")){
            answers = getIntent().getParcelableArrayListExtra("answers");
        }

        int count = 0;
/*
        String answerInfo;
        answerInfo = answers.get(0).getQuestionNumber() + ": " + answers.get(0).getAnswerChoice();
        q4.setText(answerInfo);

        answerInfo = answers.get(1).getQuestionNumber() + ": " + answers.get(1).getAnswerChoice();
        q5.setText(answerInfo);

        answerInfo = answers.get(2).getQuestionNumber() + ": " + answers.get(2).getAnswerChoice();
        q6.setText(answerInfo);
*/

        for(Iterator<UserChoice> i = answers.iterator(); i.hasNext();){

            if(i.next().getAnswerChoice() == 1){
                switch(count){
                    case 0:
                        q4.setText("Correct!");
                        break;
                    case 1:
                        q5.setText("Correct!");
                        break;
                    case 2:
                        q6.setText("Correct!");
                        break;
                }
            }
            else{
                switch(count){
                    case 0:
                        q4.setText("Incorrect!");
                        break;
                    case 1:
                        q5.setText("Incorrect!");
                        break;
                    case 2:
                        q6.setText("Incorrect!");
                        break;
                }
            }
            count++;
        }


    }
}
