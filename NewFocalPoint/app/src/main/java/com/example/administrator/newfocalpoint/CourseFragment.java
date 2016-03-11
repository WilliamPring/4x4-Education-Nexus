package com.example.administrator.newfocalpoint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CourseFragment extends Fragment implements View.OnClickListener{

    private static String ARG_NAME = "name";
    private static String ARG_ID = "id";
    private static String ARG_TEACHER_NAME = "teacher_name";

    public String name;
    public String id;
    public String teacher_name;

    private GridLayout grid;


    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance(String newName, String newID, String newTeacherName) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();

        args.putString(ARG_NAME, newName);
        args.putString(ARG_ID, newID);
        args.putString(ARG_TEACHER_NAME, newTeacherName);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            id = getArguments().getString(ARG_ID);
            teacher_name = getArguments().getString(ARG_TEACHER_NAME);
        }
        if(getView() != null) {
            TextView nameText = (TextView) getView().findViewById(R.id.course_name);
            TextView idText = (TextView) getView().findViewById(R.id.course_id);
            TextView teacherNameText = (TextView) getView().findViewById(R.id.course_teacher);
            nameText.setText(name);
            idText.setText(id);
            teacherNameText.setText(teacher_name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        grid = (GridLayout) view.findViewById(R.id.course_layout);
        grid.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Fragment newFragment = new QuestionWaitFragment();
        Bundle args = new Bundle();
        args.putString("title", name);
        newFragment.setArguments(args);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.container, newFragment).addToBackStack(String.valueOf(newFragment)).commit();
    }



}
