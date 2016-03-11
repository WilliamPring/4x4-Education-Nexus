package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FillBlank extends Fragment {

    private String questionText;
    private int questionNumber;

    private TextView txtQuestion;
    private TextView txtQNum;
    private TextView txtTimer;

    private Button btnSubmit;

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

        return view;
    }

}
