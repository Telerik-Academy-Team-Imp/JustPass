package com.example.tectonik.justpass.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tectonik.justpass.R;
import com.example.tectonik.justpass.adapters.Courses;
import com.example.tectonik.justpass.adapters.CustomCoursesAdapter;
import com.example.tectonik.justpass.adapters.CustomUserCoursesAdapter;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        populateCoursesList();
        return rootView;
    }

    private void populateCoursesList() {
        // Construct the data source
        ArrayList<Courses> arrayOfUsers = Courses.getCourses();
        // Create the adapter to convert the array to views
        CustomCoursesAdapter adapter = new CustomCoursesAdapter(getActivity(), arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) rootView.findViewById(R.id.courses_list_view);
        listView.setAdapter(adapter);
    }
}