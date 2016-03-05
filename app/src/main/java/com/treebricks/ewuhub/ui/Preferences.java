package com.treebricks.ewuhub.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.widget.Toast;
import com.treebricks.ewuhub.R;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Preferences extends PreferenceActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyPreferenceFragment prefFragment = new MyPreferenceFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, prefFragment);
        fragmentTransaction.commit();


    }
    public static class MyPreferenceFragment extends PreferenceFragment
    {

        ProgressDialog progressDialog;
        public boolean hasInternetConnection = false;
        public boolean downloaded = false;



        @Override
        public void onCreate(final Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        }

        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            String key = preference.getKey();

            if(key.equals("updatedb"))
            {
                final DownloadTask downloadTask = new DownloadTask(getActivity());
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Updating the Database.\nPlease be patience..");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.show();

                new NetworkUtility().execute();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(hasInternetConnection)
                        {

                            downloadTask.execute();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(downloaded)
                                    {

                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(getActivity(), "Database Update Successful!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        progressDialog.hide();
                                        progressDialog.cancel();
                                        Toast.makeText(getActivity(), "Database Update Failed!!!Please Try Again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, 6000);

                        }
                        else
                        {
                            progressDialog.hide();
                            progressDialog.cancel();
                            Toast.makeText(getActivity(),"You are not connected to internet",Toast.LENGTH_LONG).show();

                        }
                    }
                }, 2000);





            }
            else if(key.equals("getpref"))
            {
                Toast.makeText(getActivity(),"Soon you will see us.",Toast.LENGTH_LONG).show();
            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        private class DownloadTask extends AsyncTask<Void, Integer, String>
        {

            private Context context;

            public DownloadTask(Context context) {
                this.context = context;
            }

            @Override
            protected String doInBackground(Void... Void) {
                InputStream input = null;
                OutputStream output = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://treebricks.twomini.com/CoursesDatabase.db");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    // expect HTTP 200 OK, so we don't mistakenly save error report
                    // instead of the file
                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        String temp =  "Server returned HTTP " + connection.getResponseCode()
                                + " " + connection.getResponseMessage();
                        Log.i("Download Error Log :",temp);
                        return temp;
                    }

                    //File root = android.os.Environment.getDataDirectory();
                    File dir = new File(context.getApplicationInfo().dataDir + "/databases");
                    if(!dir.exists()){
                        dir.mkdirs();
                        Log.i("Directory Log :","Directory Created" + dir);
                    }

                    // this will be useful to display download percentage
                    // might be -1: server did not report the length
                    int fileLength = connection.getContentLength();

                    // download the file
                    input = new BufferedInputStream(url.openStream());
                    output = new FileOutputStream(dir+ "/CoursesDatabase.db");

                    byte data[] = new byte[1024];
                    long total = 0;
                    int count;
                    while ((count = input.read(data)) != -1) {
                        // allow canceling with back button
                        if (isCancelled()) {
                            input.close();
                            return null;
                        }
                        total += count;
                        output.write(data,0,count);
                    }
                } catch (Exception e) {
                    return e.toString();
                } finally {
                    try {
                        if (output != null)
                            output.close();
                        if (input != null)
                            input.close();
                    } catch (IOException ignored) {
                    }

                    if (connection != null)
                        connection.disconnect();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    downloaded = false;
                    Log.i("Update Status:","Database Download Failed!");
                }
                else {
                    downloaded = true;
                    Log.i("Update Status:","Database Downloaded");
                }
            }
        }

        public class NetworkUtility extends AsyncTask<Void, Void, Void>
        {
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
                                urlc.getContentLength() == 0)
                        {
                            Log.i("Internet Connection", "network available!");
                            connect = true;
                        }
                    } catch (IOException e) {
                        Log.e("Internet Connection", "Error checking internet connection", e);
                        connect = false;
                    }
                }
                else {
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
        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if(activeNetworkInfo != null && activeNetworkInfo.isConnected())
            {
                return true;
            }
            return false;
        }



    }

}
