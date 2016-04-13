/*
* FILE : CourseListFragment.java
* PROJECT : Mobile Application Development
* PROGRAMMER : Matt Warren, William Pring, Steven Johnston, Denys Politiuk
* FIRST VERSION : 2016-03-11
* DESCRIPTION :
* Fragment used to hold multiple CourseFragments and an Add Courses button
*/
package com.example.administrator.newfocalpoint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CourseListFragment extends Fragment implements View.OnClickListener{

    Button addCourse;


    public CourseListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //access db here and create the course fragments needed to hold the courses


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Courses");

        //add a bunch of CourseFragments,
        //eventually this will be pulling from a DB online
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment newFrag1 = CourseFragment.newInstance("Math 2100", "10301", "Matthew Warren");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Science Rulz", "1!", "William Pringle");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("English", "1234", "Steven Johnston");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Alphabet", "1!", "Denys Polituik");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Science Rulz", "1!", "William Pringle");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("English", "1234", "Steven Johnston");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Alphabet", "1!", "Denys Polituik");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Science Rulz", "1!", "William Pringle");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("English", "1234", "Steven Johnston");
        ft.add(R.id.frag_container, newFrag1);
        newFrag1 = CourseFragment.newInstance("Alphabet", "1!", "Denys Polituik");
        ft.add(R.id.frag_container, newFrag1);
        ft.commit();
        View rootView = inflater.inflate(R.layout.fragment_course_list, container,false);

        addCourse = (Button) rootView.findViewById(R.id.addCourse);
        addCourse.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Adding Course")
                .setMessage("Enter the Course ID")
                        //Yes
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    //No
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.btn_star)
                .show();
    }
}
