package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.ProgressDialogQuotes;
import java.security.SecureRandom;

public class EwuSpirit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ChromeCustomTab chromeCustomTab;
    WebView spiritWebView;
    String advising_list;
    ProgressDialog progressDialog;
    ProgressDialogQuotes progressDialogQuotes;
    SecureRandom secureRandom;
    private boolean doubleBackToExitPressedOnce;
    CardView instructionCard , facultyCard, routineCard, seatCard, creditCard,
            advisiorCard, timeCard, advisingListCard, disclamierCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewu_spirit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instructionCard = (CardView) findViewById(R.id.instruction_card);
        facultyCard = (CardView) findViewById(R.id.faculty_card);
        routineCard = (CardView) findViewById(R.id.routine_card);
        seatCard = (CardView) findViewById(R.id.seat_card);
        creditCard = (CardView) findViewById(R.id.credit_card);
        advisiorCard = (CardView) findViewById(R.id.advisior_card);
        timeCard = (CardView) findViewById(R.id.advising_time_card);
        advisingListCard = (CardView) findViewById(R.id.advising_list_card);
        disclamierCard = (CardView) findViewById(R.id.disclamier_card);

        doubleBackToExitPressedOnce = false;
        chromeCustomTab = new ChromeCustomTab(getApplicationContext(), EwuSpirit.this);
        secureRandom = new SecureRandom();
        progressDialog = new ProgressDialog(EwuSpirit.this);
        progressDialogQuotes = new ProgressDialogQuotes();
        progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));


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

        //final String ewuspirit = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/ewuspirit.html";
        advising_list = "file://" + getBaseContext().getApplicationInfo().dataDir+"/html/advising_list.html";

        spiritWebView.setWebViewClient(new WebViewClient());
        //spiritWebView.loadUrl(ewuspirit);

        spiritWebView.setVisibility(View.INVISIBLE);

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

    void cardHide()
    {
        instructionCard.setVisibility(View.INVISIBLE);
        facultyCard.setVisibility(View.INVISIBLE);
        routineCard.setVisibility(View.INVISIBLE);
        seatCard.setVisibility(View.INVISIBLE);
        creditCard.setVisibility(View.INVISIBLE);
        advisiorCard.setVisibility(View.INVISIBLE);
        timeCard.setVisibility(View.INVISIBLE);
        advisingListCard.setVisibility(View.INVISIBLE);
        disclamierCard.setVisibility(View.INVISIBLE);;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toast toast = Toast.makeText(this,"Press again to go EwuHub Home.", Toast.LENGTH_SHORT);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else {
                if (doubleBackToExitPressedOnce) {
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_faculty_evaluation)
        {
            if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
            {
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
                progressDialog.show();
                if(chromeOk())
                {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chromeCustomTab.runOnCustomTab("http:172.16.100.31:8020/webnet/index.php?option=assess&op=student&act=evaluation");
                            progressDialog.hide();
                            progressDialog.cancel();
                        }
                    }, 2000);
                }
                else
                {
                    spiritWebView.setWebViewClient(new MyWebViewClient());
                    spiritWebView.loadUrl("http:172.16.100.31:8020/webnet/index.php?option=assess&op=student&act=evaluation");
                }
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
                cardHide();
                spiritWebView.setVisibility(View.VISIBLE);
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
                progressDialog.show();
                if(chromeOk())
                {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chromeCustomTab.runOnCustomTab("http://172.16.100.31:8020/webnet/");
                            progressDialog.hide();
                            progressDialog.cancel();
                        }
                    }, 2000);
                }
                else
                {
                    spiritWebView.setWebViewClient(new MyWebViewClient());
                    spiritWebView.loadUrl("http://172.16.100.31:8020/webnet/");
                }
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
            cardHide();
            spiritWebView.setVisibility(View.VISIBLE);
            progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
        return ssid;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!progressDialog.isShowing()) {
                progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
