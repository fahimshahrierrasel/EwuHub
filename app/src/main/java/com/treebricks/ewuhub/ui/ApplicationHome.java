package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.utility.AppInstalled;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApplicationHome extends AppCompatActivity {
    public boolean hasInternetConnection = false;
    private ProgressDialog progressDialog;
    int versionCode;
    ChromeCustomTab chromeCustomTab;
    private boolean doubleBackToExitPressedOnce;
    private Drawer homePageDrawer = null;
    private AccountHeader homePageAccountHeader = null;
    private AppInstalled chromeBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Intro Activity Start
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                // Create a new Boolean and preference and set it true
                boolean isFirstRun = getPrefs.getBoolean("intro_first_start", true);
                // if the activity never start before
                if (isFirstRun)
                {
                    // Lunch app intro
                    // Copy from assets folder
                    copyJsonFile("graduate.json");
                    copyJsonFile("undergraduate.json");
                    copyJsonFile("pharmacyundergraduate.json");
                    copyJsonFile("pharmacygraduate.json");
                    copyHTMLFile("advising_list.html");

                    Intent i = new Intent(ApplicationHome.this, ApplicationIntro.class);
                    startActivity(i);
                    finish();
                    // make a new preference aditor
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putInt("notification_num", 1);
                    // edit preference to make it false because we don't want this run again
                    e.putBoolean("intro_first_start", false);
                    e.apply();
                }
            }
        });
        t.start();



        doubleBackToExitPressedOnce = false;
        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), ApplicationHome.this);

        setContentView(R.layout.activity_application_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chromeBrowser = new AppInstalled(this);


        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // Create a new Boolean and preference and set it true
        versionCode = getPrefs.getInt("version_code",1);

        // Navigation Drawer Header
        homePageAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.long_dark)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .build();
        // Navigation Drawer
        homePageDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(homePageAccountHeader)
                .addDrawerItems(
                        new ExpandableDrawerItem().withName("Advising").withIcon(R.drawable.sign).withArrowColor(Color.parseColor("#009688")).withSubItems(
                                new PrimaryDrawerItem().withIcon(R.drawable.salvation).withName(R.string.advising_helper).withIdentifier(1),
                                new PrimaryDrawerItem().withIcon(R.drawable.clipboard).withName(R.string.advising_list).withIdentifier(2)
                        ),
                        new PrimaryDrawerItem().withIcon(R.drawable.diploma).withName(R.string.result).withIdentifier(3),
                        new PrimaryDrawerItem().withIcon(R.drawable.calendar).withName(R.string.academic_calender).withIdentifier(4),
                        new PrimaryDrawerItem().withIcon(R.drawable.notes).withName(R.string.notice_board).withIdentifier(5),
                        new PrimaryDrawerItem().withIcon(R.drawable.bookshelf).withName(R.string.ewu_library).withIdentifier(6),
                        new PrimaryDrawerItem().withIcon(R.drawable.torch).withName(R.string.ewuspirit).withIdentifier(7),
                        new PrimaryDrawerItem().withIcon(R.drawable.rss).withName("Newsfeed").withIdentifier(8),
                        new PrimaryDrawerItem().withIcon(R.drawable.chat).withName("Friendly Chat (Experimental)").withIdentifier(9)
                )
                .addStickyDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.preferences).withIcon(R.drawable.settings).withIdentifier(10)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem != null)
                        {
                            switch ((int) drawerItem.getIdentifier())
                            {
                                case 1:
                                {
                                    Intent sortCourse = new Intent(ApplicationHome.this, SortCourseHome.class);
                                    startActivity(sortCourse);
                                    break;
                                }
                                case 2:
                                {
                                    //String advising_list = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/advising_list.html";

                                    Intent i = new Intent(ApplicationHome.this, ActivityAdvisingList.class);
                                    //Bundle sentData = new Bundle();
                                    //sentData.putString("URL", advising_list);
                                    //sentData.putString("AdvisingSheet", "Yes");
                                    //i.putExtras(sentData);
                                    startActivity(i);
                                    break;
                                }
                                case 3:
                                {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Connecting to the Result Server.");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();
                                    startNewTask();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (hasInternetConnection)
                                            {
                                                if(chromeBrowser.isChromeEnabled("com.android.chrome"))
                                                {
                                                    chromeCustomTab.runOnCustomTab("http://result.ewubd.edu/");
                                                    progressDialog.hide();
                                                    progressDialog.cancel();
                                                }
                                                else
                                                {
                                                    Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                                                    Bundle sentData = new Bundle();
                                                    sentData.putString("URL", "http://result.ewubd.edu/");
                                                    sentData.putString("AdvisingSheet", "No");
                                                    i.putExtras(sentData);
                                                    progressDialog.hide();
                                                    progressDialog.cancel();
                                                    startActivity(i);
                                                }


                                            } else {
                                                progressDialog.hide();
                                                progressDialog.cancel();
                                                Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }, 2000);
                                    break;
                                }
                                case 4:
                                {
                                    Intent academicCalender = new Intent(ApplicationHome.this, AcademicCalendar.class);
                                    startActivity(academicCalender);
                                    break;
                                }
                                case 5:
                                {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Checking Internet Connection..");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();

                                    startNewTask();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (hasInternetConnection)
                                            {
                                                final Intent notice = new Intent(ApplicationHome.this, NoticeActivity.class);
                                                progressDialog.hide();
                                                progressDialog.cancel();
                                                startActivity(notice);
                                            }
                                            else
                                            {
                                                progressDialog.hide();
                                                progressDialog.cancel();
                                                Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }, 2000);
                                    break;
                                }
                                case 6:
                                {
                                    progressDialog = new ProgressDialog(ApplicationHome.this);
                                    progressDialog.setMessage("Going to Ewu Library...");
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.show();
                                    startNewTask();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (hasInternetConnection)
                                            {
                                                if(chromeBrowser.isChromeEnabled("com.android.chrome"))
                                                {
                                                    chromeCustomTab.runOnCustomTab("http://lib.ewubd.edu/");
                                                    progressDialog.hide();
                                                    progressDialog.cancel();
                                                }
                                                else
                                                {
                                                    Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                                                    Bundle sentData = new Bundle();
                                                    sentData.putString("URL", "http://lib.ewubd.edu/");
                                                    sentData.putString("AdvisingSheet", "No");
                                                    i.putExtras(sentData);
                                                    progressDialog.hide();
                                                    progressDialog.cancel();
                                                    startActivity(i);
                                                }

                                            }
                                            else
                                            {
                                                progressDialog.hide();
                                                progressDialog.cancel();
                                                Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }, 2000);

                                    break;
                                }
                                case 7:
                                {
                                    Intent ewuSpirt = new Intent(ApplicationHome.this, EwuSpirit.class);
                                    startActivity(ewuSpirt);
                                    break;
                                }
                                case 8:
                                {
                                    Toast.makeText(ApplicationHome.this, "Newsfeed service is coming soon.", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case 9:
                                {
                                    Toast.makeText(ApplicationHome.this, "Friendly chat is coming soon.", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case 10:
                                {
                                    Intent i = new Intent(ApplicationHome.this, Preferences.class);
                                    startActivity(i);
                                    break;
                                }
                            }
                        }
                        return true;
                    }
                })
                .build();

        newVersion();

    }



    public void newVersion()
    {
        int version = 0;
        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // Huh? Really?
        }
        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // Create a new Boolean and preference and set it true
        if(version > versionCode)
        {
            SharedPreferences.Editor e = getPrefs.edit();
            // edit preference to make it false because we don't want this run again
            e.putInt("version_code", version);
            e.apply();
            copyDatabase("CoursesDatabase.db");
        }
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_SHORT);
        if (homePageDrawer.isDrawerOpen()) {
            homePageDrawer.closeDrawer();
        } else {
            if(doubleBackToExitPressedOnce)
            {
                toast.cancel();
                super.onBackPressed();
            }
            this.doubleBackToExitPressedOnce = true;
            toast.show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    public void startNewTask() {
        NetworkUtility networkUtility = new NetworkUtility();
        networkUtility.execute();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public void aplicaiton_home_onclick_listener(View view)
    {
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Going EWU Website");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        startNewTask();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hasInternetConnection) {
                    if(chromeBrowser.isChromeEnabled("com.android.chrome"))
                    {
                        chromeCustomTab.runOnCustomTab("http://www.ewubd.edu");
                        progressDialog.hide();
                        progressDialog.cancel();
                    }
                    else
                    {
                        Intent i = new Intent(ApplicationHome.this, AllWebView.class);
                        Bundle sentData = new Bundle();
                        sentData.putString("URL", "http://www.ewubd.edu");
                        sentData.putString("AdvisingSheet", "No");
                        i.putExtras(sentData);
                        progressDialog.hide();
                        progressDialog.cancel();
                        startActivity(i);
                    }


                } else {
                    progressDialog.hide();
                    progressDialog.cancel();
                    Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                }
            }
        }, 2000);
    }


    public class NetworkUtility extends AsyncTask<Void, Void, Void> {
        boolean connect = false;

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
                            urlc.getContentLength() == 0) {
                        Log.i("Internet Connection", "network available!");
                        connect = true;
                    }
                } catch (IOException e) {
                    Log.e("Internet Connection", "Error checking internet connection", e);
                    connect = false;
                }
            } else {
                Log.d("Internet Connection", "No network available!");
                connect = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            hasInternetConnection = connect;
            Log.i("On Threads", "execution complete");
        }
    }





    private void copyDatabase(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("tag", "copyHTMLFile() "+filename);
            input = assetManager.open(filename);
            File dir = new File(getBaseContext().getApplicationInfo().dataDir + "/databases");
            if(!dir.exists()){
                dir.mkdirs();
                Log.i("Directory Log :","Directory Created" + dir);
            }

            output = new FileOutputStream(dir+"/"+filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Log.e("tag", "Exception in copyHTMLFile() of "+filename);
            Log.e("tag", "Exception in copyHTMLFile() "+e.toString());
        }
        finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
        }
    }
    private void copyHTMLFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("tag", "copyHTMLFile() "+filename);
            input = assetManager.open(filename);
            File dir = new File(getBaseContext().getApplicationInfo().dataDir + "/html");
            if(!dir.exists()){
                dir.mkdirs();
                Log.i("Directory Log :","Directory Created" + dir);
            }

            output = new FileOutputStream(dir+"/"+filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Log.e("tag", "Exception in copyHTMLFile() of "+filename);
            Log.e("tag", "Exception in copyHTMLFile() "+e.toString());
        }
        finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
        }
    }

    private void copyJsonFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("JSONFileCopy", "copyHTMLFile() "+filename);
            input = assetManager.open(filename);
            File dir = new File(getBaseContext().getApplicationInfo().dataDir + "/json");
            if(!dir.exists()){
                dir.mkdirs();
                Log.i("Directory Log :","Directory Created" + dir);
            }

            output = new FileOutputStream(dir+"/"+filename);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
        } catch (Exception e) {
            Log.e("JSONFileCopy", "Exception in copyHTMLFile() of "+filename);
            Log.e("JSONFileCopy", "Exception in copyHTMLFile() "+e.toString());
        }
        finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
        }
    }

}
