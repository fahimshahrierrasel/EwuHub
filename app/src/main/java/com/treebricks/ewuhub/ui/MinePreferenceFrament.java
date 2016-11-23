package com.treebricks.ewuhub.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.utility.AppInstalled;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fahim on 7/9/16.
 */
public class MinePreferenceFrament extends PreferenceFragment
{
    public static final String TAG = MinePreferenceFrament.class.getSimpleName();
    ProgressDialog progressDialog;
    public boolean hasInternetConnection = false;
    FirebaseStorage storage;
    StorageReference storageReference;
    boolean[] calenderDownloaded;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://ewuhub.appspot.com");

        calenderDownloaded = new boolean[4];

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String key = preference.getKey();

        if(key.equals("updatedb"))
        {

            progressDialog.setMessage("Updating the Database\nPlease be patient..");
            progressDialog.show();

            new NetworkUtility().execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(hasInternetConnection)
                    {
                        if(playServiceUpdated())
                        {
                            StorageReference databaseReference = storageReference.child("databases/CoursesDatabase.db");

                            final long ONE_MEGABYTE = 1024 * 1024;

                            databaseReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {

                                    OutputStream output = null;

                                    try {

                                        File dir = new File(getActivity().getApplicationInfo().dataDir + "/databases");
                                        if(!dir.exists()){
                                            dir.mkdirs();
                                            Log.i("Directory Log :","Directory Created" + dir);
                                        }
                                        output = new FileOutputStream(dir+ "/CoursesDatabase.db");
                                        output.write(bytes);
                                    }
                                    catch (IOException e)
                                    {
                                        e.getMessage();
                                    } finally {
                                        try {
                                            if (output != null) {
                                                output.close();
                                                output = null;
                                            }
                                        } catch (IOException ignored) {
                                            ignored.getStackTrace();
                                        }
                                    }
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Course Database Updated Successfully :)", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    progressDialog.dismiss();
                                    progressDialog.cancel();
                                    Toast.makeText(getActivity(), "Course Database Updated Failed :(", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                        else
                        {
                            progressDialog.dismiss();
                            progressDialog.cancel();
                            Toast.makeText(getActivity(), "Google Play Service is Outdated :(\nMake sure Google Play Service is updated!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        progressDialog.dismiss();
                        progressDialog.cancel();
                        Toast.makeText(getActivity(),"You are not connected to internet",Toast.LENGTH_LONG).show();

                    }
                }
            }, 2000);

        }
        else if(key.equals("updateac"))
        {
            progressDialog.setMessage("Updating the Academic Calendar.\nPlease be patient..");
            progressDialog.show();

            new NetworkUtility().execute();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(hasInternetConnection)
                    {

                        if(playServiceUpdated())
                        {
                            undergraduateTask();
                            graduateTask();
                            pharmacyUndergraduateTask();
                            pharmacyGraduateTask();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    boolean sucess = true;
                                    for (boolean aCalenderDownloaded : calenderDownloaded) {
                                        if (!aCalenderDownloaded) {
                                            progressDialog.dismiss();
                                            progressDialog.cancel();
                                            Toast.makeText(getActivity(), "Academic Calendars Update Failed :( ", Toast.LENGTH_SHORT).show();
                                            sucess = false;
                                            break;
                                        }
                                    }
                                    if(sucess)
                                    {
                                        progressDialog.dismiss();
                                        progressDialog.cancel();
                                        Toast.makeText(getActivity(),"Academic Calendar Updated Successfully :) ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, 4000);
                        }
                        else
                        {
                            progressDialog.dismiss();
                            progressDialog.cancel();
                            Toast.makeText(getActivity(), "Google Play Service is Outdated :(\nMake sure Google Play Service is updated!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        progressDialog.cancel();
                        Toast.makeText(getActivity(),"You are not connected to internet :( ",Toast.LENGTH_LONG).show();

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
        else if(key.equals("developer"))
        {
            Intent about = new Intent(getActivity(), About.class);
            startActivity(about);
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public void graduateTask()
    {
        StorageReference databaseReference = storageReference.child("calendars/graduate.json");

        final long ONE_MEGABYTE = 1024 * 1024;

        databaseReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                OutputStream output = null;

                try {
                    File dir = new File(getActivity().getApplicationInfo().dataDir + "/json");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    output = new FileOutputStream(dir+ "/graduate.json");
                    output.write(bytes);
                }
                catch (IOException e)
                {
                    e.getMessage();
                } finally {
                    try {
                        if (output != null) {
                            output.close();
                            output = null;
                        }
                    } catch (IOException ignored) {
                        ignored.getStackTrace();
                    }
                }
                calenderDownloaded[0] = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                calenderDownloaded[0] = false;

            }
        });
    }

    public void pharmacyUndergraduateTask()
    {
        StorageReference databaseReference = storageReference.child("calendars/pharmacyundergraduate.json");

        final long ONE_MEGABYTE = 1024 * 1024;

        databaseReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                OutputStream output = null;

                try {
                    File dir = new File(getActivity().getApplicationInfo().dataDir + "/json");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    output = new FileOutputStream(dir+ "/pharmacyundergraduate.json");
                    output.write(bytes);
                }
                catch (IOException e)
                {
                    e.getMessage();
                } finally {
                    try {
                        if (output != null) {
                            output.close();
                            output = null;
                        }
                    } catch (IOException ignored) {
                        ignored.getStackTrace();
                    }
                }
                calenderDownloaded[1] = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                calenderDownloaded[1] = false;

            }
        });
    }

    public void pharmacyGraduateTask()
    {
        StorageReference databaseReference = storageReference.child("calendars/pharmacygraduate.json");

        final long ONE_MEGABYTE = 1024 * 1024;

        databaseReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                OutputStream output = null;

                try {
                    File dir = new File(getActivity().getApplicationInfo().dataDir + "/json");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    output = new FileOutputStream(dir+ "/pharmacygraduate.json");
                    output.write(bytes);
                }
                catch (IOException e)
                {
                    e.getMessage();
                } finally {
                    try {
                        if (output != null) {
                            output.close();
                            output = null;
                        }
                    } catch (IOException ignored) {
                        ignored.getStackTrace();
                    }
                }

                calenderDownloaded[2] = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                calenderDownloaded[2] = false;

            }
        });
    }

    public void undergraduateTask()
    {
        StorageReference databaseReference = storageReference.child("calendars/undergraduate.json");

        final long ONE_MEGABYTE = 1024 * 1024;

        databaseReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                OutputStream output = null;

                try {

                    File dir = new File(getActivity().getApplicationInfo().dataDir + "/json");
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    output = new FileOutputStream(dir+ "/undergraduate.json");
                    output.write(bytes);
                }
                catch (IOException e)
                {
                    e.getMessage();
                } finally {
                    try {
                        if (output != null) {
                            output.close();
                            output = null;
                        }
                    } catch (IOException ignored) {
                        ignored.getStackTrace();
                    }
                }

                calenderDownloaded[3] = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                calenderDownloaded[3] = false;

            }
        });
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

    private boolean playServiceUpdated()
    {
        AppInstalled appInstalled = new AppInstalled(getActivity());
        int versionCode = appInstalled.appVersionByPackageName("com.google.android.gms");
        return (versionCode >= 10084000);
    }


}
