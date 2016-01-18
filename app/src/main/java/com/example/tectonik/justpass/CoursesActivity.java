package com.example.tectonik.justpass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.tectonik.justpass.fragments.AddCourseFragment;
import com.example.tectonik.justpass.fragments.CourseFragment;
import com.example.tectonik.justpass.fragments.CoursesFragment;
import com.example.tectonik.justpass.helpers.Constants;


public class CoursesActivity extends AppCompatActivity {

    ViewPager coursesViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        coursesViewPager = (ViewPager) findViewById(R.id.swipeViewPager);
        JustPassCoursesPagerAdapter adapter = new JustPassCoursesPagerAdapter(getSupportFragmentManager());
        coursesViewPager.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        int navPage = extras.getInt(Constants.PAGE);

        coursesViewPager.setCurrentItem(navPage);
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
