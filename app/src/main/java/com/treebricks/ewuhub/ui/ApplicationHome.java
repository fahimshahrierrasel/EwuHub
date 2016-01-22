package com.treebricks.ewuhub.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.treebricks.ewuhub.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApplicationHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public boolean hasInternetConnection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sort_course)
        {
            //Toast.makeText(this, "Sort Course Pressed", Toast.LENGTH_SHORT).show();
            Intent sortCourse = new Intent(this, SortCourseHome.class);
            startActivity(sortCourse);
        }
        else if (id == R.id.nav_result)
        {
            startNewTask();
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            if(hasInternetConnection)
            {

                Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                startActivity(i);

            }
            else
            {
                Toast.makeText(ApplicationHome.this,"You are not connected to internet",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_academic_calender)
        {
            Toast.makeText(this, "Academic Calender Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_notice_board)
        {
            Toast.makeText(this, "Notice Board Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_settings)
        {
            Toast.makeText(this, "Settings Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_about)
        {
            Toast.makeText(this, "About Pressed", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startNewTask()
    {
        NetworkUtility networkUtility = new NetworkUtility();
        networkUtility.execute();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
        {
            return true;
        }
        return false;
    }


    public class NetworkUtility extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            if (isNetworkAvailable()) {
                try {
                    HttpURLConnection urlc = (HttpURLConnection)
                            (new URL("http://clients3.google.com/generate_204")
                                    .openConnection());
                    urlc.setRequestProperty("User-Agent", "Android");
                    urlc.setRequestProperty("Connection", "close");
                    urlc.setConnectTimeout(1500);
                    urlc.setReadTimeout(2000);
                    urlc.connect();

                    if (urlc.getResponseCode() == 204 &&
                            urlc.getContentLength() == 0)
                    {
                        Log.i("Internet Connection", "network available!");
                        hasInternetConnection = true;
                    }
                } catch (IOException e) {
                    Log.e("Internet Connection", "Error checking internet connection", e);
                    hasInternetConnection = false;
                }
            }
            else {
                Log.d("Internet Connection", "No network available!");
                hasInternetConnection = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("On Threads", "execution complete");
        }
    }
}
