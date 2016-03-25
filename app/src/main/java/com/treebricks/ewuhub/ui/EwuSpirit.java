package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.treebricks.ewuhub.R;

public class EwuSpirit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView spiritWebView;
    String advising_list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewu_spirit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(EwuSpirit.this);
        progressDialog.setMessage("We are almost there :):)\n" +
                "Please be patience!");
        // spritWebView
        spiritWebView = (WebView) findViewById(R.id.ewu_spirit_webview);
        WebSettings webSettings = null;
        if (spiritWebView != null) {
            webSettings = spiritWebView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);
            webSettings.setJavaScriptEnabled(true);
        }


        final String ewuspirit = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/ewuspirit.html";
        advising_list = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/advising_list.html";

        spiritWebView.setWebViewClient(new WebViewClient());
        spiritWebView.loadUrl(ewuspirit);

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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_faculty_evaluation)
        {
            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http:172.16.100.31:8020/webnet/index.php?option=assess&op=student&act=evaluation");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_class_schedule)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/webnet/index.php?option=room&op=student&act=check");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_available_seat)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/registration/knowseat.php");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_credit_info)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/registration/crdinfo.php");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_advisior)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/registration/advisor.php");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if (id == R.id.nav_advising_schedule)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/registration/webschedule.php");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if(id == R.id.nav_ewu_sprit_homepage)
        {

            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.show();
                spiritWebView.setWebViewClient(new MyWebViewClient());
                spiritWebView.loadUrl("http://172.16.100.31:8020/webnet/");
            }
            else if("NotConnected".equals(getCurrentSsid(getApplicationContext())))
            {
                Toast.makeText(getApplicationContext(),"You are not connected to Internet.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You are not connected to \"ewuwifi\".",Toast.LENGTH_LONG).show();
            }
        }
        else if(id == R.id.advising_sheet)
        {
            progressDialog.show();
            spiritWebView.setWebViewClient(new MyWebViewClient());
            spiritWebView.loadUrl(advising_list);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    public static String getCurrentSsid(Context context) {
        String ssid = "\"ElseWifi\"";

        if (isDeviceOnline(context)) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        else
        {
            ssid = "\"NotConnected\"";
        }
        ssid = ssid.substring(1, ssid.length()-1);
        System.out.println("Current SSID is : " + ssid);
        return ssid;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

    public static boolean isDeviceOnline(Context context) {
        boolean isConnectionAvail = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null)
                return netInfo.isConnected();
            else
                return isConnectionAvail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnectionAvail;
    }
}
