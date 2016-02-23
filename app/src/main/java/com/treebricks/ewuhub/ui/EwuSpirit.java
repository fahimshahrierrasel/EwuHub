package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.treebricks.ewuhub.R;

public class EwuSpirit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView spiritWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ewu_spirit_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // spritWebView
        spiritWebView = (WebView) findViewById(R.id.ewu_spirit_webview);
        WebSettings webSettings = spiritWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);


        spiritWebView.setWebViewClient(new WebViewClient());
        spiritWebView.loadUrl("file:///android_asset/test.html");




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

        if (id == R.id.nav_faculty_evaluation)
        {
            spiritWebView.loadUrl("http:172.16.100.31:8020/webnet/index.php?option=assess&op=student&act=evaluation");
        }
        else if (id == R.id.nav_class_schedule)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/registration/routine.php");
        }
        else if (id == R.id.nav_available_seat)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/registration/knowseat.php");
        }
        else if (id == R.id.nav_credit_info)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/registration/crdinfo.php");
        }
        else if (id == R.id.nav_advisior)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/registration/advisor.php");
        }
        else if (id == R.id.nav_advising_schedule)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/registration/webschedule.php");
        }
        else if(id == R.id.nav_ewu_sprit_homepage)
        {
            spiritWebView.loadUrl("http://172.16.100.31:8020/webnet/");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
