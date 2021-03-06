package com.example.tectonik.justpass.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tectonik.justpass.R;

import java.util.ArrayList;

public class CustomUserCoursesAdapter extends ArrayAdapter<Courses> {

    public CustomUserCoursesAdapter(Context context, ArrayList<Courses> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Courses courses = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        // Lookup view for data population
        TextView tvCourseName = (TextView) convertView.findViewById(R.id.iv_course_name);
        TextView tvResults = (TextView) convertView.findViewById(R.id.iv_course_result);
        // Populate the data into the template view using the data object
        tvCourseName.setText(courses.courseName);
        tvResults.setText(courses.results);
        // Return the completed view to render on screen
        return convertView;
    }
}
