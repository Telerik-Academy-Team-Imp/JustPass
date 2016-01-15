package com.example.tectonik.justpass;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.tectonik.justpass.fragments.CourseFragment;
import com.example.tectonik.justpass.fragments.CoursesFragment;
import com.example.tectonik.justpass.helpers.Constants;

import java.util.Stack;

public class CoursesActivity extends AppCompatActivity {

    DrawerLayout drawer;
    ViewPager coursesViewPager;
    Stack<Integer> pageHistory;
    int currentPage;
    boolean saveToHistory;
    AppBarLayout appBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);


//        toolbar = (Toolbar) findViewById(R.id.courses_toolbar);
//        setSupportActionBar(toolbar);

//        drawer = (DrawerLayout) findViewById(R.id.courses_drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.courses_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);



        coursesViewPager = (ViewPager) findViewById(R.id.swipeViewPager);
        JustPassCoursesPagerAdapter adapter = new JustPassCoursesPagerAdapter(getSupportFragmentManager());
        coursesViewPager.setAdapter(adapter);

        //appBar = (AppBarLayout) findViewById(R.id.courses_toolbar_layout);

//        pageHistory = new Stack<>();
//        coursesViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int arg0) {
//                if (saveToHistory)
//                    pageHistory.push(Integer.valueOf(currentPage));
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });
//        saveToHistory = true;

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
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CoursesFragment();
                case 1:
                    return new CourseFragment();
                default:
                    return null;
            }
        }
    }

//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//        @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        else if (!pageHistory.empty()) {
//            saveToHistory = false;
//            coursesViewPager.setCurrentItem(pageHistory.pop().intValue());
//            saveToHistory = true;
//        }
//        else {
//            super.onBackPressed();
//        }
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if(id == R.id.nav_main) {
//            //currentPage = coursesViewPager.getCurrentItem();
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra(Constants.PAGE, 1);
//            startActivity(intent);
//        } else if (id == R.id.nav_courses) {
//            //currentPage = coursesViewPager.getCurrentItem();
//            coursesViewPager.setCurrentItem(0);
//        } else if (id == R.id.nav_course) {
//            //currentPage = coursesViewPager.getCurrentItem();
//            coursesViewPager.setCurrentItem(1);
//        } else if (id == R.id.nav_profile) {
//            //currentPage = coursesViewPager.getCurrentItem();
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra(Constants.PAGE, 2);
//            startActivity(intent);
//        } else if (id == R.id.nav_logout) {
//
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra(Constants.PAGE, 3);
//            startActivity(intent);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.courses_drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
