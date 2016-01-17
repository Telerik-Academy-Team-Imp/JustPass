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
import android.widget.ImageView;

import com.example.tectonik.justpass.R;
import com.example.tectonik.justpass.ScaleImageActivity;
import com.example.tectonik.justpass.customViews.ScaleImageView;
import com.example.tectonik.justpass.helpers.Constants;
import com.example.tectonik.justpass.helpers.ImageManager;

public class ProfileFragment extends Fragment {
    ImageView profilePicture;
    ScaleImageView pictureViewer;
    Bitmap takenPhoto;
    Bitmap currentPhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        profilePicture = (ImageView) rootView.findViewById(R.id.profile_picture);
        pictureViewer =  (ScaleImageView) rootView.findViewById(R.id.picture_viewer);


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
}