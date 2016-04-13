package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        //then do this:
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment newFrag = ResultFragment.newInstance("1", "A", "B");
        ft.add(R.id.frag_container, newFrag);
        ft.commit();
        return view;
    }
}
