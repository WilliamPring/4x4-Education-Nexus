package com.example.administrator.newfocalpoint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CourseListFragment extends Fragment {



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
        return inflater.inflate(R.layout.fragment_course_list, container, false);
    }

}
