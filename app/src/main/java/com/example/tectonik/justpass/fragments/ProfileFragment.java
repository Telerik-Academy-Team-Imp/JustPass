package com.example.tectonik.justpass.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tectonik.justpass.R;
import com.example.tectonik.justpass.ScaleImageActivity;
import com.example.tectonik.justpass.customViews.ScaleImageView;
import com.example.tectonik.justpass.helpers.Constants;

public class ProfileFragment extends Fragment {
    ImageView profilePicture;
    ScaleImageView pictureViewer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePicture = (ImageView) rootView.findViewById(R.id.profile_picture);
        pictureViewer =  (ScaleImageView)rootView.findViewById(R.id.picture_viewer);
        profilePicture.setTag(R.drawable.profile_pic);
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScaleImageActivity.class);
                intent.putExtra(Constants.PAGE, 2);
                startActivity(intent);
                
               // pictureViewer.setImageResource((int)profilePicture.getTag());
            }
        });
        return rootView;
    }
}