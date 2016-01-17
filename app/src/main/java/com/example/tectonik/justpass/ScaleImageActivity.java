package com.example.tectonik.justpass;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.tectonik.justpass.customViews.ScaleImageView;

public class ScaleImageActivity extends AppCompatActivity {
    ScaleImageView pictureViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image);

        pictureViewer =  (ScaleImageView) findViewById(R.id.picture_viewer);
        Bundle extras = getIntent().getExtras();
        Bitmap bmp = extras.getParcelable("bmp");
        if (bmp == null) {
            pictureViewer.setImageResource(R.drawable.profile_pic);
        } else {
            pictureViewer.setImageBitmap(bmp);
        }
    }
}
