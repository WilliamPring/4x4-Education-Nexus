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
import android.widget.TextView;


public class TrueFalse extends Fragment {


    private String questionText;
    private int questionNumber;

    private TextView txtQuestion;
    private TextView txtQNum;
    private TextView txtTimer;



    public TrueFalse() {
        // Required empty public constructor
    }

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
        if (getArguments() != null) {
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
        txtQNum.setText("#" + Integer.toString(questionNumber));

        txtQuestion = (TextView) view.findViewById(R.id.questionText);
        txtQuestion.setText(questionText);

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
        }
    }

}
