/*
* FILE : FillBlank.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the Fill in the Blank type of question.
*/

package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FillBlank extends Fragment implements Button.OnClickListener{

    private String questionText;
    private int questionNumber;

    private TextView txtQuestion;
    private TextView txtQNum;
    private TextView txtTimer;

    private EditText answer;

    private Button btnSubmit;

    private TimerThread tt;

    public FillBlank() {
        // Required empty public constructor
    }

    public static FillBlank newInstance(int newQuestionNumber, String newQuestion) {
        FillBlank fragment = new FillBlank();
        Bundle args = new Bundle();
        args.putInt("questionNumber", newQuestionNumber);
        args.putString("question", newQuestion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt("questionNumber");
            questionText = getArguments().getString("question");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fill_blank, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null && bundle.containsKey("title")){
            getActivity().setTitle(bundle.getString("title"));
        }

        txtQNum = (TextView) view.findViewById(R.id.questionNumber);
        txtQNum.setText("#" + Integer.toString(questionNumber));

        txtQuestion = (TextView) view.findViewById(R.id.questionText);
        txtQuestion.setText(questionText);

        txtTimer = (TextView) view.findViewById(R.id.timerCount);
        txtTimer.setText(String.valueOf(10));

        answer = (EditText) view.findViewById(R.id.editText);

        btnSubmit = (Button) view.findViewById(R.id.submitQuestion);
        btnSubmit.setOnClickListener(this);



        //start 60 second timer
        tt = new TimerThread();
        tt.execute(String.valueOf(10), String.valueOf(1000));

        return view;
    }

    @Override
    public void onClick(View v) {

        tt.cancel(true);
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(answer.getWindowToken(), 0);
        Fragment newFragment = new TrueFalse();
        Bundle args = new Bundle();
        args.putString("question", "There are 652 banana chunks in your average pineapple.");
        args.putInt("questionNumber", 3);
        newFragment.setArguments(args);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();
    }

    class TimerThread extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String... params){
            try{
                int loopCount = Integer.parseInt(params[0]);
                long waitTime = Long.parseLong(params[1]);
                for(int i = 0; i < loopCount; i++){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtTimer.setText(String.valueOf(Integer.parseInt(txtTimer.getText().toString()) - 1));
                        }
                    });

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
            Fragment newFragment = new TrueFalse();
            Bundle args = new Bundle();
            args.putString("question", "There are 652 banana chunks in your average pineapple.");
            args.putInt("questionNumber", 3);
            newFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();

        }
    }

}
