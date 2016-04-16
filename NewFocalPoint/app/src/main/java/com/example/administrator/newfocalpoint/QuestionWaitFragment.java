/*
* FILE : QuestionWaitFragment.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* This file contains the supporting functions and behaviour for the Waiting for new question fragment.
*/


package com.example.administrator.newfocalpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuestionWaitFragment extends Fragment {


    Bundle toSend;

    public QuestionWaitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("WAITING", "getArguments: " + getArguments());
        if (getArguments() != null) {
            toSend = getArguments();
        }
        else{
            toSend = new Bundle();
        }

        Log.d("WAITING", "toSend: " + toSend);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_wait, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null && bundle.containsKey("title")){
            getActivity().setTitle(bundle.getString("title"));
            if(bundle.containsKey("questionType")){
                toSend.putString("question", bundle.getString("question"));
                toSend.putInt("questionNumber", bundle.getInt("questionNumber"));
            }
            else{
                //set up first question
                toSend.putString("questionType", "multiplechoice");
                toSend.putString("question", "How many apple pies does it take to loosen a rusty bolt on an orange squishing machine?");
                toSend.putString("answerA", "1");
                toSend.putString("answerB", "2");
                toSend.putString("answerC", "3.14");
                toSend.putString("answerD", "What kind of stupid question is this?");
                toSend.putInt("questionNumber", 1);
            }
        }

        //start the service
        Intent serviceIntent = new Intent(getActivity(), NewQuestionService.class);
        serviceIntent.putExtras(toSend);
        getActivity().startService(serviceIntent);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }



}
