package com.treebricks.ewuhub.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.treebricks.ewuhub.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApplicationHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public boolean hasInternetConnection = false;
    String jsonString = "NULL";
    String jsonStringNoti = "NULL";
    JSONObject jsonObject;
    JSONArray jsonArray;
    int notificationNumber = 1;
    String notificationTitle;
    String notificationDescription;
    private ProgressDialog progressDialog;
    int versionCode;
    NotificationCompat.Builder builder;
    ChromeCustomTab chromeCustomTab;
    private boolean doubleBackToExitPressedOnce;

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
                    copyFile("graduate.html");
                    copyFile("undergraduate.html");
                    copyFile("pharmacyundergraduate.html");
                    copyFile("pharmacygraduate.html");
                    copyFile("ewuspirit.html");
                    copyFile("advising_list.html");


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


        getNotification();
        doubleBackToExitPressedOnce = false;
        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), ApplicationHome.this);

        setContentView(R.layout.activity_application_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // Create a new Boolean and preference and set it true
        versionCode = getPrefs.getInt("version_code",1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

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


    public void getNotification()
    {
        builder = new NotificationCompat.Builder(getBaseContext());

        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        // Create a new Boolean and preference and set it true
        final int notificationNum = getPrefs.getInt("notification_num",1);
        startNewTask();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                if (hasInternetConnection)
                {
                    new NotificationBackgroundTask().execute();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            if (!"NULL".equals(jsonStringNoti))
                            {
                                if (jsonStringNoti != null && !jsonStringNoti.isEmpty())
                                {
                                    try {
                                        jsonObject = new JSONObject(jsonStringNoti);
                                        jsonArray = jsonObject.getJSONArray("Notification");
                                        int count = 0;
                                        while(count < jsonArray.length())
                                        {

                                            JSONObject jObject = jsonArray.getJSONObject(count);
                                            notificationNumber = Integer.parseInt(jObject.getString("NotificationNumber"));

                                            notificationTitle = jObject.getString("Title");

                                            notificationDescription = jObject.getString("Description");

                                            count++;
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if(notificationNumber > notificationNum)
                                {

                                    SharedPreferences.Editor e = getPrefs.edit();
                                    // edit preference to make it false because we don't want this run again
                                    e.putInt("notification_num", notificationNumber);
                                    e.apply();

                                    Notification notification = builder.
                                            setSmallIcon(R.drawable.ic_notification)
                                            .setColor(Color.parseColor("#1A237E"))
                                            .setDefaults(Notification.DEFAULT_ALL)
                                            .setStyle(new NotificationCompat.BigTextStyle()
                                                    .bigText(notificationDescription)
                                                    .setBigContentTitle(notificationTitle)
                                                    .setSummaryText("Thanks for using EwuHub"))
                                            .setAutoCancel(true)
                                            .setVisibility(View.VISIBLE)
                                            .build();


                                    NotificationManager notificationManager = (NotificationManager)
                                            getSystemService(Context.NOTIFICATION_SERVICE);

                                    notificationManager.notify(0, notification);

                                }
                            }
                        }
                    }, 1500);
                }
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toast toast = Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_SHORT);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else
            {
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

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sort_course)
        {
            Intent sortCourse = new Intent(this, SortCourseHome.class);
            startActivity(sortCourse);
        }
        else if (id == R.id.nav_result)
        {
            progressDialog = new ProgressDialog(this);
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
                        if(chromeOk())
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
        else if (id == R.id.nav_academic_calender)
        {
            Intent academicCalender = new Intent(this, AcademicCalendar.class);
            startActivity(academicCalender);
        }
        else if (id == R.id.nav_notice_board)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Getting the notice from the Database.\nPlease be patience..");
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
                        new NoticeBackgroundTask().execute();
                        final Intent notice = new Intent(ApplicationHome.this, NoticeActivity.class);
                        final Bundle sentData = new Bundle();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!"NULL".equals(jsonString))
                                {
                                    if (jsonString != null && !jsonString.isEmpty()) {
                                        sentData.putString("JSON_DATA", jsonString);
                                        notice.putExtras(sentData);
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        startActivity(notice);
                                    }
                                    else
                                    {
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(ApplicationHome.this, "Failed to Download the Notices!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    progressDialog.hide();
                                    progressDialog.cancel();
                                    Toast.makeText(ApplicationHome.this, "Please, Try Again! Failed to get the Notice.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 4000);
                    }
                    else
                    {
                        progressDialog.hide();
                        progressDialog.cancel();
                        Toast.makeText(ApplicationHome.this, "You are not connected to internet", Toast.LENGTH_LONG).show();
                    }
                }
            }, 2000);
        }
        else if (id == R.id.nav_preferences)
        {
            Intent i = new Intent(this, Preferences.class);
            startActivity(i);
        }
        else if (id == R.id.nav_about)
        {
            Intent about = new Intent(this, About.class);
            startActivity(about);
        }
        else if (id == R.id.ewusprit)
        {
            Intent ewuSpirt = new Intent(this, EwuSpirit.class);
            startActivity(ewuSpirt);
        }
        else if (id == R.id.nav_library)
        {
            progressDialog = new ProgressDialog(this);
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
                        if(chromeOk())
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
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
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

    public void aplicaiton_home_onclick_listener(View view) {
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

                    if(chromeOk())
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


    public class NoticeBackgroundTask extends AsyncTask<Void, Void, String> {
        String JSON_STRING;
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.treebricks.com/ewuhub/notice_json_query.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING).append("\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i("Notice Board", "Data Recieved from Database");
            jsonString = result;
        }
    }


    public class NotificationBackgroundTask extends AsyncTask<Void, Void, String> {
        String JSON_STRING;
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://www.treebricks.com/ewuhub/notification_json.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING).append("\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(),"Data Recieved from Database", Toast.LENGTH_LONG).show();
            Log.i("Notification", "Notification Recieved from Database");
            jsonStringNoti = result;
        }
    }

    private void copyDatabase(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("tag", "copyFile() "+filename);
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
            Log.e("tag", "Exception in copyFile() of "+filename);
            Log.e("tag", "Exception in copyFile() "+e.toString());
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
    private void copyFile(String filename) {
        AssetManager assetManager = this.getAssets();

        InputStream input = null;
        OutputStream output = null;
        try {
            Log.i("tag", "copyFile() "+filename);
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
            Log.e("tag", "Exception in copyFile() of "+filename);
            Log.e("tag", "Exception in copyFile() "+e.toString());
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

    private boolean chromeOk()
    {
        boolean result = false;
        if(isAppInstalled("com.android.chrome") && isAppEnabled("com.android.chrome") && (chromeVersion() >= 45))
        {
            result = true;
        }
        return result;
    }

    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
    private boolean isAppEnabled(String packageName)
    {
        boolean appStatus = false;
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(packageName, 0);
            appStatus = ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appStatus;
    }

    private int chromeVersion()
    {
        int versionCode = 38;
        String versionNumber;
        PackageManager pm = getPackageManager();
        PackageInfo pInfo = null;
        try {
            pInfo = pm.getPackageInfo("com.android.chrome", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            String version = pInfo.versionName;
            versionNumber = version.substring(0,2);
            if(isNumeric(versionNumber))
            {
                versionCode = Integer.parseInt(versionNumber);
            }
        }
        return versionCode;
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
