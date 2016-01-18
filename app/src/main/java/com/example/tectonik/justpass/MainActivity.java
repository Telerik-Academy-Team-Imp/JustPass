package com.example.tectonik.justpass;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tectonik.justpass.fragments.AboutFragment;
import com.example.tectonik.justpass.fragments.LoginFragment;
import com.example.tectonik.justpass.fragments.MainPageFragment;
import com.example.tectonik.justpass.fragments.ProfileFragment;
import com.example.tectonik.justpass.fragments.RegistrationFragment;
import com.example.tectonik.justpass.helpers.Constants;
import com.example.tectonik.justpass.helpers.ImageManager;


import java.util.Stack;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ViewPager viewPager;
    private Stack<Integer> pageHistory;
    private int currentPage;
    private boolean saveToHistory;
    private AppBarLayout appBar;
    private Toolbar toolbar;
    private String key;
    private Context mContext;
    private boolean isLogged;
    private static final Integer NOTIFICATION_ID = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

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
        TextView fullName = (TextView) findViewById(R.id.full_name);
        TextView email = (TextView) findViewById(R.id.email);
//        fullName.setText();
//        email.setText();

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

    public void takePhoto(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePhotoIntent, Constants.MY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.MY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageManager.SaveImage(imageBitmap);
            sendNotification();

            ImageView imageView = (ImageView) this.findViewById(R.id.profile_picture);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void sendNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle("Picture saved.");
        mBuilder.setContentText("Your profile picture was successfully saved.");
        mBuilder.setSmallIcon(R.drawable.ok);

        Intent notificationIntent = new Intent(mContext, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        mBuilder.setContentIntent(notificationPendingIntent);
        Notification notificationObject = mBuilder.build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationObject);
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
                Toast.makeText(this, "Welcome. You are logged in", Toast.LENGTH_LONG).show();
                viewPager.setCurrentItem(1);
                saveToHistory = false;
                //pageHistory.pop();
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
        } else if (id == R.id.nav_add_course) {
            currentPage = viewPager.getCurrentItem();
            Intent intent = new Intent(this, CoursesActivity.class);
            intent.putExtra(Constants.PAGE, 2);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
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

            Toast.makeText(this, "Successfully logged out.", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
