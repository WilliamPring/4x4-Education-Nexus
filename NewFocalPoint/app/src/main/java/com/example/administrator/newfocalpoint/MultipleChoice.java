package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MultipleChoice extends Fragment {

    private String questionText;
    private int questionNumber;

    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;

    private TextView txtA;
    private TextView txtB;
    private TextView txtC;
    private TextView txtD;

    private TextView txtQuestion;
    private TextView txtQNum;
    private TextView txtTimer;

    public MultipleChoice() {
        // Required empty public constructor
    }


    public static MultipleChoice newInstance(int newQuestionNumber, String newQuestion,
                                             String newAnswerA, String newAnswerB,
                                             String newAnswerC, String newAnswerD) {
        MultipleChoice fragment = new MultipleChoice();
        Bundle args = new Bundle();
        args.putInt("questionNumber", newQuestionNumber);
        args.putString("question", newQuestion);
        args.putString("answerA", newAnswerA);
        args.putString("answerB", newAnswerB);
        args.putString("answerC", newAnswerC);
        args.putString("answerD", newAnswerD);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt("questionNumber");
            questionText = getArguments().getString("question");
            answerA = getArguments().getString("answerA");
            answerB = getArguments().getString("answerB");
            answerC = getArguments().getString("answerC");
            answerD = getArguments().getString("answerD");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null && bundle.containsKey("title")){
            getActivity().setTitle(bundle.getString("title"));
        }


        txtQNum = (TextView) view.findViewById(R.id.questionNumber);
        txtQNum.setText("#" + Integer.toString(questionNumber));

        txtQuestion = (TextView) view.findViewById(R.id.questionText);
        txtQuestion.setText(questionText);

        txtA = (TextView) view.findViewById(R.id.answerA);
        txtA.setText("A) " + answerA);
        txtB = (TextView) view.findViewById(R.id.answerB);
        txtB.setText("B) " + answerB);
        txtC = (TextView) view.findViewById(R.id.answerC);
        txtC.setText("C) " + answerC);
        txtD = (TextView) view.findViewById(R.id.answerD);
        txtD.setText("D) " + answerD);

        btnA = (Button) view.findViewById(R.id.questionAButton);
        btnB = (Button) view.findViewById(R.id.questionBButton);
        btnC = (Button) view.findViewById(R.id.questionCButton);
        btnD = (Button) view.findViewById(R.id.questionDButton);
        //set on clicks here

        txtTimer = (TextView) view.findViewById(R.id.timerCount);
        txtTimer.setText(String.valueOf(10));

        //start 60 second timer
        new TimerThread().execute(String.valueOf(10), String.valueOf(1000));

        return view;
    }

    class TimerThread extends AsyncTask<String, Void, String> {
        private int internalCounter = 0;

        @Override
        protected void onPreExecute(){
            //internalCounter = counter;
        }

        @Override
        protected String doInBackground(String... params){
            try{
                int loopCount = Integer.parseInt(params[0]);
                long waitTime = Long.parseLong(params[1]);
                for(int i = 0; i < loopCount; i++){
                    Thread.sleep(waitTime);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtTimer.setText(String.valueOf(Integer.parseInt(txtTimer.getText().toString()) - 1));
                        }
                    });
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

            Fragment newFragment = new FillBlank();
            Bundle args = new Bundle();
            args.putString("title", getActivity().getTitle().toString());
            args.putString("question", "In 1812, computers we often used to do _________.");
            args.putInt("questionNumber", 2);
            newFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();

        }
    }


}
