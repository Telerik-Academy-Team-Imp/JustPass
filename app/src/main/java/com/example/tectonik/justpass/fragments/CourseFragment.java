package com.example.tectonik.justpass.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.tectonik.justpass.R;

public class CourseFragment extends Fragment {
    Boolean isSelected;
    String key;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course, container, false);
        RelativeLayout courseInfoNot = (RelativeLayout) rootView.findViewById(R.id.not_selected);
        LinearLayout courseInfo = (LinearLayout) rootView.findViewById(R.id.selected);

        key = getString(R.string.selected);
        isSelected = getActivity()
                .getPreferences(Context.MODE_PRIVATE)
                .getBoolean(key, false);

        if (isSelected) {
            courseInfoNot.setVisibility(View.GONE);
            courseInfo.setVisibility(View.VISIBLE);
        } else {
            courseInfoNot.setVisibility(View.VISIBLE);
            courseInfo.setVisibility(View.GONE);
        }

        Button clear = (Button) rootView.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                .getPreferences(Context.MODE_PRIVATE)
                .edit().putBoolean(getString(R.string.selected), false)
                .commit();
            }
        });

        return rootView;
    }


}