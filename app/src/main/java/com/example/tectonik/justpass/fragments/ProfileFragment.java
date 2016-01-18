package com.example.tectonik.justpass.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.tectonik.justpass.R;
import com.example.tectonik.justpass.ScaleImageActivity;
import com.example.tectonik.justpass.adapters.Courses;
import com.example.tectonik.justpass.adapters.CustomCoursesAdapter;
import com.example.tectonik.justpass.adapters.CustomUserCoursesAdapter;
import com.example.tectonik.justpass.customViews.ScaleImageView;
import com.example.tectonik.justpass.helpers.Constants;
import com.example.tectonik.justpass.helpers.ImageManager;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    ImageView profilePicture;
    ScaleImageView pictureViewer;
    Bitmap takenPhoto;
    Bitmap currentPhoto;
    ListView listView;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //ListAdapter adapter = new ArrayAdapter<String>(getActivity(), )

        profilePicture = (ImageView) rootView.findViewById(R.id.profile_picture);
        pictureViewer =  (ScaleImageView) rootView.findViewById(R.id.picture_viewer);
        listView = (ListView) rootView.findViewById(R.id.profile_list_view);
        //listView.setAdapter();

        populateCoursesList();

        if(ImageManager.loadFromCacheFile() != null) {
            takenPhoto = ImageManager.loadFromCacheFile();
            profilePicture.setImageBitmap(takenPhoto);
        }

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePicture.buildDrawingCache();
                currentPhoto = profilePicture.getDrawingCache();

                Intent intent = new Intent(getActivity(), ScaleImageActivity.class);
                intent.putExtra(Constants.PAGE, 2);
                if(takenPhoto != null) {
                    intent.putExtra("bmp", takenPhoto);
                } else{
                    intent.putExtra("bmp", currentPhoto);
                }

                startActivity(intent);
            }
        });


        return rootView;
    }

    private void populateCoursesList() {
        // Construct the data source
        ArrayList<Courses> arrayOfUsers = Courses.getCourses();
        // Create the adapter to convert the array to views
        CustomUserCoursesAdapter adapter = new CustomUserCoursesAdapter(getActivity(), arrayOfUsers);
        // Attach the adapter to a ListView
        ListView listView = (ListView) rootView.findViewById(R.id.profile_list_view);
        listView.setAdapter(adapter);
    }
}