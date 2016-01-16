package com.example.tectonik.justpass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class ScaleImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image);

//        Bundle extras = getIntent().getExtras();
//        int navPage = extras.getInt(Constants.PAGE);

        //coursesViewPager.setCurrentItem(navPage);
    }
}
