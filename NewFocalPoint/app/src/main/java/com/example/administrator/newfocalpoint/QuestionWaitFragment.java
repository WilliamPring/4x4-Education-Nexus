package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


public class QuestionWaitFragment extends Fragment {

    private String title;

    private Timer waitTimer;

    public QuestionWaitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_wait, container, false);

        Bundle bundle = getArguments();

        if(bundle!=null && bundle.containsKey("title")){
            getActivity().setTitle(bundle.getString("title"));
        }

        new TimerThread().execute(String.valueOf(5), String.valueOf(1000));
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
            Fragment newFragment = new MultipleChoice();
            Bundle args = new Bundle();
            args.putString("title", getActivity().getTitle().toString());
            args.putString("question", "How many apple pies does it take to loosen a rusty bolt on an orange squishing machine?");
            args.putString("answerA", "1");
            args.putString("answerB", "2");
            args.putString("answerC", "3.14");
            args.putString("answerD", "What kind of stupid question is this?");
            args.putInt("questionNumber", 1);
            newFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();

        }
    }

}
