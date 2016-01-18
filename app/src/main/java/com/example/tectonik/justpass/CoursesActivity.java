package com.example.tectonik.justpass;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tectonik.justpass.fragments.AddCourseFragment;
import com.example.tectonik.justpass.fragments.CourseFragment;
import com.example.tectonik.justpass.fragments.CoursesFragment;
import com.example.tectonik.justpass.helpers.Constants;


public class CoursesActivity extends AppCompatActivity {

    ViewPager coursesViewPager;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        mContext = this;
        //courseInfoNot = (RelativeLayout) findViewById(R.id.not_selected);
        // courseInfo = (LinearLayout) findViewById(R.id.selected);


        coursesViewPager = (ViewPager) findViewById(R.id.swipeViewPager);
        JustPassCoursesPagerAdapter adapter = new JustPassCoursesPagerAdapter(getSupportFragmentManager());
        coursesViewPager.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        int navPage = extras.getInt(Constants.PAGE);

        coursesViewPager.setCurrentItem(navPage);
    }

    public void navigateToCourseFragment(View view) {
        switch (view.getId()) {
            case R.id.list_course_name:
                this
                        .getPreferences(Context.MODE_PRIVATE)
                        .edit().putBoolean(getString(R.string.selected), true)
                        .commit();
                coursesViewPager.setCurrentItem(1);
                break;
        }
    }

    public class JustPassCoursesPagerAdapter extends FragmentPagerAdapter {
        public JustPassCoursesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CoursesFragment();
                case 1:
                    return new CourseFragment();
                case 2:
                    return new AddCourseFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Courses";
                case 1: return "Course Information";
                case 2: return "Add Course";
                default: return "non";
            }
        }
    }
}
