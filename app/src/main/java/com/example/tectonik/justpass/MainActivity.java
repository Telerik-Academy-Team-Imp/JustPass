package com.example.tectonik.justpass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.example.tectonik.justpass.fragments.AboutFragment;
import com.example.tectonik.justpass.fragments.AddCourseFragment;
import com.example.tectonik.justpass.fragments.LoginFragment;
import com.example.tectonik.justpass.fragments.MainPageFragment;
import com.example.tectonik.justpass.fragments.ProfileFragment;
import com.example.tectonik.justpass.fragments.RegistrationFragment;
import com.example.tectonik.justpass.helpers.Constants;

import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ViewPager viewPager;
    Stack<Integer> pageHistory;
    int currentPage;
    boolean saveToHistory;
    AppBarLayout appBar;
    Toolbar toolbar;
    String key;
    boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = getString(R.string.autoLog);
        isLogged = this
                .getPreferences(Context.MODE_PRIVATE)
                .getBoolean(key, false);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.noSwipeViewPager);
        JustPassPagerAdapter adapter = new JustPassPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        appBar = (AppBarLayout) findViewById(R.id.toolbar_layout);

        pageHistory = new Stack<>();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if (saveToHistory)
                    pageHistory.push(Integer.valueOf(currentPage));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        saveToHistory = true;


        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            int page = extras.getInt(Constants.PAGE);

            viewPager.setCurrentItem(page);
        }
    }

    public void navigateToFragment(View view) {
        switch (view.getId()) {
            case R.id.btn_reg_login_page:
                appBar.setVisibility(View.GONE);
                currentPage = viewPager.getCurrentItem();
                viewPager.setCurrentItem(5);
                break;
            case R.id.btn_login:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                appBar.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(1);
                //saveToHistory = false;
                pageHistory.pop();
                break;
            case R.id.btn_registration:
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                appBar.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(1);
                //saveToHistory = false;
                pageHistory.clear();
                break;
        }
    }

    public class JustPassPagerAdapter extends FragmentPagerAdapter {
        public JustPassPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (isLogged) {
                        return new MainPageFragment();
                    } else {
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        appBar.setVisibility(View.GONE);
                        return new LoginFragment();
                    }
                case 1:
                    return new MainPageFragment();
                case 2:
                    return new ProfileFragment();
                case 3:
                    return new AboutFragment();
                case 4:
                    return new LoginFragment();
                case 5:
                    return new RegistrationFragment();
                default:
                    return null;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!pageHistory.empty()) {
            saveToHistory = false;
            viewPager.setCurrentItem(pageHistory.pop().intValue());
            saveToHistory = true;
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            currentPage = viewPager.getCurrentItem();
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_courses) {
            currentPage = viewPager.getCurrentItem();
            Intent intent = new Intent(this, CoursesActivity.class);
            intent.putExtra(Constants.PAGE, 0);
            startActivity(intent);
        } else if (id == R.id.nav_course) {
            currentPage = viewPager.getCurrentItem();
            Intent intent = new Intent(this, CoursesActivity.class);
            intent.putExtra(Constants.PAGE, 1);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            currentPage = viewPager.getCurrentItem();
            viewPager.setCurrentItem(2);
        } else if(id == R.id.nav_add_course) {
            currentPage = viewPager.getCurrentItem();
            Intent intent = new Intent(this, CoursesActivity.class);
            intent.putExtra(Constants.PAGE, 2);
            startActivity(intent);
        } else if(id == R.id.nav_about) {
            currentPage = viewPager.getCurrentItem();
            viewPager.setCurrentItem(3);
        } else if (id == R.id.nav_logout) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            appBar.setVisibility(View.GONE);
            saveToHistory = false;
            //pageHistory.pop();
            viewPager.setCurrentItem(4);
            this
                    .getPreferences(Context.MODE_PRIVATE)
                    .edit().putBoolean(getString(R.string.autoLog), false)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
