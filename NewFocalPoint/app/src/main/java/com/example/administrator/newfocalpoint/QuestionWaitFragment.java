package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        return view;
    }


}
