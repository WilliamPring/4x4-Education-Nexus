/*
* FILE : TrueFalse.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the True or False type of question.
*/

package com.example.administrator.newfocalpoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class TrueFalse extends Fragment{


    private String questionText;
    private int questionNumber;

    private TextView txtQuestion;
    private TextView txtQNum;
    private TextView txtTimer;

    TimerThread tt;


    public TrueFalse() {
        // Required empty public constructor
    }


    //used to instantiate a new instance with parameters
    public static TrueFalse newInstance(int newQuestionNumber, String newQuestion) {
        TrueFalse fragment = new TrueFalse();
        Bundle args = new Bundle();
        args.putInt("questionNumber", newQuestionNumber);
        args.putString("question", newQuestion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { //set up question variables
            questionNumber = getArguments().getInt("questionNumber");
            questionText = getArguments().getString("question");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_true_false, container, false);
        Bundle bundle = getArguments();
        if(bundle!=null && bundle.containsKey("title")){
            getActivity().setTitle(bundle.getString("title"));
        }

        txtQNum = (TextView) view.findViewById(R.id.questionNumber);
        txtQNum.setText("#" + Integer.toString(questionNumber)); //set question number

        txtQuestion = (TextView) view.findViewById(R.id.questionText);
        txtQuestion.setText(questionText); //set question text

        txtTimer = (TextView) view.findViewById(R.id.timerCount);
        txtTimer.setText(String.valueOf(30)); //set timer start

        //start 30 second timer
        tt = new TimerThread();
        tt.execute(String.valueOf(30), String.valueOf(1000));

        View swipe = view.findViewById(R.id.swipe_area);
        swipe.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            @Override
            public void onSwipeRight() {
                Toast.makeText(getActivity(), "Swiped Right!", Toast.LENGTH_SHORT).show();

                Fragment newFragment = new ResultsFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(getActivity(), "Swiped Left!", Toast.LENGTH_SHORT).show();

                Fragment newFragment = new ResultsFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();
            }

        });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        tt.cancel(true);
    }

    //class for a async task to count down the time remaining
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
                            //change the text
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
        }
    }

}
