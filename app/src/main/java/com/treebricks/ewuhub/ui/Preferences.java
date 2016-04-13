package com.treebricks.ewuhub.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
        boolean successfull = true;

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
                final DownloadDBTask downloadDBTask = new DownloadDBTask(getActivity());
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Updating the Database.\nPlease be patient..");
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
                            downloadDBTask.execute();
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
            else if(key.equals("updateac"))
            {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Updating the Academic Calendar.\nPlease be patient..");
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
                            undergraduateTask();
                            graduateTask();
                            pharmacyUndergraduateTask();
                            pharmacyGraduateTask();
                            if(successfull)
                            {
                                progressDialog.hide();
                                progressDialog.cancel();
                                Toast.makeText(getActivity(), "Update Successful!!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                progressDialog.hide();
                                progressDialog.cancel();
                                Toast.makeText(getActivity(), "Update Failed!!", Toast.LENGTH_LONG).show();
                            }
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

            else if(key.equals("updatelist"))
            {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Updating the Advising List.\nPlease be patient..");
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
                            advisingListTask();
                            if(successfull)
                            {
                                progressDialog.hide();
                                progressDialog.cancel();
                                Toast.makeText(getActivity(), "Update Successful!!", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                progressDialog.hide();
                                progressDialog.cancel();
                                Toast.makeText(getActivity(), "Update Failed!!", Toast.LENGTH_LONG).show();
                            }
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

            else if(key.equals("feedback"))
            {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "feedback@treebricks.com" });
                Email.putExtra(Intent.EXTRA_SUBJECT, "EwuHub Feedback");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
            /*else if(key.equals("opensrc"))
            {
                Toast.makeText(getActivity(),"Soon you will see us.",Toast.LENGTH_LONG).show();
            }*/
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        public void undergraduateTask()
        {
            downloadHtmlTask undergraduate = new downloadHtmlTask(getActivity(),"http://www.treebricks.com/ewuhub/undergraduate.html",0,"undergraduate.html");
            undergraduate.execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!downloaded)
                    {
                        successfull= false;
                    }
                }
            }, 3000);
        }
        public void graduateTask()
        {
            downloadHtmlTask graduate = new downloadHtmlTask(getActivity(),"http://www.treebricks.com/ewuhub/graduate.html",0,"graduate.html");
            graduate.execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!downloaded)
                    {
                        successfull= false;
                    }
                }
            }, 3000);
        }

        public void pharmacyUndergraduateTask()
        {
            downloadHtmlTask undergraduatePharmacy = new downloadHtmlTask(getActivity(),"http://www.treebricks.com/ewuhub/pharmacyundergraduate.html",0,"pharmacyundergraduate.html");
            undergraduatePharmacy.execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!downloaded)
                    {
                        successfull= false;
                    }
                }
            }, 3000);
        }

        public void pharmacyGraduateTask()
        {
            downloadHtmlTask graduatePharmacy = new downloadHtmlTask(getActivity(),"http://www.treebricks.com/ewuhub/pharmacygraduate.html",0,"pharmacygraduate.html");
            graduatePharmacy.execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!downloaded)
                    {
                        successfull= false;
                    }
                }
            }, 3000);
        }

        public void advisingListTask()
        {
            downloadHtmlTask advisingList = new downloadHtmlTask(getActivity(),"http://www.treebricks.com/ewuhub/advising_list.html",0,"advising_list.html");
            advisingList.execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!downloaded)
                    {
                        successfull= false;
                    }
                }
            }, 3000);
        }
        private class DownloadDBTask extends AsyncTask<Void, Integer, String>
        {
            private Context context;
            public DownloadDBTask(Context context) {
                this.context = context;
            }

            @Override
            protected String doInBackground(Void... Void) {
                InputStream input = null;
                OutputStream output = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.treebricks.com/ewuhub/CoursesDatabase.db");
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
                    // Creating buffer
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

        private class downloadHtmlTask extends AsyncTask<Void, Void, String>
        {
            private Context context;
            private String recievedUrl = "http://www.google.com/";
            private int choice;
            private String fileName;

            public downloadHtmlTask(Context context, String url, int choice, String fileName)
            {
                this.context = context;
                this.recievedUrl = url;
                this.choice = choice;
                this.fileName = fileName;
            }

            @Override
            protected String doInBackground(Void... Void) {
                HttpURLConnection urlConnection = null;
                FileOutputStream fileOutput = null;
                InputStream inputStream = null;

                try {
                    String USERAGENT;
                    if(choice==0)
                    {
                        USERAGENT ="Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-us) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Safari/530.17";
                    }
                    else
                    {
                        USERAGENT ="Mozilla/5.0 (Linux; U; Android 2.1-update1; en-us; ADR6300 Build/ERE27) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17";
                    }
                    URL url = new URL(recievedUrl);
                    //create the new connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    //set up some things on the connection
                    urlConnection.setRequestProperty("User-Agent", USERAGENT);  //if you are not sure of user agent just set choice=0
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        String temp =  "Server returned HTTP " + urlConnection.getResponseCode()
                                + " " + urlConnection.getResponseMessage();
                        Log.i("Download Error Log :",temp);
                        return temp;
                    }
                    //set the path where we want to save the file
                    File dir = new File (context.getApplicationInfo().dataDir + "/html");
                    if(!dir.exists())
                    {
                        dir.mkdirs();
                        Log.i("Directory Log :","Directory Created" + dir);
                    }
                    File file = new File(dir, fileName);  //any name abc.html
                    //this will be used to write the downloaded data into the file we created
                    fileOutput = new FileOutputStream(file);
                    //this will be used in reading the data from the internet
                    inputStream = urlConnection.getInputStream();
                    //this is the total size of the file
                    int totalSize = urlConnection.getContentLength();
                    //variable to store total downloaded bytes
                    int downloadedSize = 0;
                    //create a buffer...
                    byte[] buffer = new byte[1024];
                    int bufferLength = 0; //used to store a temporary size of the buffer
                    //write the contents to the file
                    while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                        fileOutput.write(buffer, 0, bufferLength);
                    }
                    //catch some possible errors...
                } catch (IOException e) {
                    return e.toString();
                }
                finally
                {
                    //close the output stream when done
                    try {
                        if (fileOutput != null)
                            fileOutput.close();
                        if (inputStream != null)
                            inputStream.close();
                    } catch (IOException ignored) {
                    }
                    if (urlConnection != null)
                        urlConnection.disconnect();
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
