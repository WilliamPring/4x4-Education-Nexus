package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    private static String ARG_NUM = "qNum";
    private static String ARG_CHOICE = "uChoice";
    private static String ARG_ANSWER = "qAnswer";

    private String qNum;
    private String uChoice;
    private String qAnswer;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(String questionNumber, String choice, String answer) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();

        args.putString(ARG_NUM, questionNumber);
        args.putString(ARG_CHOICE, choice);
        args.putString(ARG_ANSWER, answer);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        //get all of the information
        if (getArguments() != null) {
            qNum = getArguments().getString(ARG_NUM);
            uChoice = getArguments().getString(ARG_CHOICE);
            qAnswer = getArguments().getString(ARG_ANSWER);
        }
        if(getView() != null) {
            TextView qNumText = (TextView) getView().findViewById(R.id.question_number);
            TextView uChoiceText = (TextView) getView().findViewById(R.id.choice);
            TextView qAnswerText = (TextView) getView().findViewById(R.id.answer);
            qNumText.setText(qNum);
            uChoiceText.setText(uChoice);
            qAnswerText.setText(qAnswer);
        }
    }

}
