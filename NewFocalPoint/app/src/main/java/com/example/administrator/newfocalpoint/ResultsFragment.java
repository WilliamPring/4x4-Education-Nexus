package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ResultsFragment extends Fragment {

    public ResultsFragment() {
        // Required empty public constructor
    }

    public static ResultsFragment newInstance() {

        return new ResultsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false); // Inflate the layout for this fragment
        getActivity().setTitle("Results");

        //pull from database right here
        ResultDB res = new ResultDB(getContext());
        List<Results> results = res.GetResultsList();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment newFrag;

        if(results.size()!=0) {
            for (Results r : results) {
                newFrag = ResultFragment.newInstance(String.valueOf(r.getQuestionNumber()), r.getUserChoice(), r.getQuestionAnswer());
                ft.add(R.id.frag_container, newFrag);
            }
        }
        ft.commit();
        return view;
    }
}
