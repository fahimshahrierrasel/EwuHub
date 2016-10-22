package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.utility.AppInstalled;
import com.treebricks.ewuhub.view.ProgressDialogQuotes;

import java.security.SecureRandom;

public class EwuSpirit extends AppCompatActivity{

    ChromeCustomTab chromeCustomTab;
    WebView spiritWebView;

    ProgressDialog progressDialog;
    ProgressDialogQuotes progressDialogQuotes;
    SecureRandom secureRandom;
    private boolean doubleBackToExitPressedOnce;
    CardView instructionCard , facultyCard, routineCard, seatCard, creditCard,
            advisiorCard, timeCard, disclamierCard;

    AccountHeader ewspiritAccountHeadeer = null;
    Drawer ewspiritDrawer = null;
    private AppInstalled chromeBrowser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewu_spirit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chromeBrowser = new AppInstalled(this);

        instructionCard = (CardView) findViewById(R.id.instruction_card);
        facultyCard = (CardView) findViewById(R.id.faculty_card);
        routineCard = (CardView) findViewById(R.id.routine_card);
        seatCard = (CardView) findViewById(R.id.seat_card);
        creditCard = (CardView) findViewById(R.id.credit_card);
        advisiorCard = (CardView) findViewById(R.id.advisior_card);
        timeCard = (CardView) findViewById(R.id.advising_time_card);
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


        // Navigation Drawer Header
        ewspiritAccountHeadeer = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.ewu_spirit)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .build();
        // Navigation Drawer
        ewspiritDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(ewspiritAccountHeadeer)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIcon(R.drawable.evaluate).withName(R.string.faculy_evaluation).withIdentifier(1),
                        new PrimaryDrawerItem().withIcon(R.drawable.schedule).withName(R.string.class_schedule_routine).withIdentifier(2),
                        new PrimaryDrawerItem().withIcon(R.drawable.presentation).withName(R.string.check_available_seat).withIdentifier(3),
                        new PrimaryDrawerItem().withIcon(R.drawable.graph).withName(R.string.credit_info).withIdentifier(4),
                        new PrimaryDrawerItem().withIcon(R.drawable.man).withName(R.string.check_advisior).withIdentifier(5),
                        new PrimaryDrawerItem().withIcon(R.drawable.clock).withName(R.string.advising_time_schedule).withIdentifier(6)
                )
                .addStickyDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.ewu_spirit_homepage).withIcon(R.drawable.torch).withIdentifier(7)
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
                                    if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
                                    {
                                        progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
                                        progressDialog.show();
                                        if(chromeBrowser.isChromeEnabled("com.android.chrome"))
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
                                    break;
                                }
                                case 2:
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
                                    break;
                                }
                                case 3:
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
                                    break;

                                }
                                case 4:
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
                                    break;
                                }
                                case 5:
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
                                    break;
                                }
                                case 6:
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
                                    break;
                                }
                                case 7:
                                {
                                    if("ewuwifi".equals(getCurrentSsid(getApplicationContext())))
                                    {
                                        cardHide();
                                        spiritWebView.setVisibility(View.VISIBLE);
                                        progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
                                        progressDialog.show();
                                        if(chromeBrowser.isChromeEnabled("com.android.chrome"))
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
                                    break;
                                }
                            }
                        }
                        return true;
                    }
                })
                .build();



        spiritWebView.setWebViewClient(new WebViewClient());

        spiritWebView.setVisibility(View.INVISIBLE);

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
        disclamierCard.setVisibility(View.INVISIBLE);;
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(this,"Press again to go EwuHub Home.", Toast.LENGTH_SHORT);

        if (ewspiritDrawer.isDrawerOpen()) {
            ewspiritDrawer.closeDrawer();
        } else {
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

}
