package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.treebricks.ewuhub.R;

public class NoticeWebViewer extends AppCompatActivity {


    android.webkit.WebView noticeWebView;
    private String url;
    private String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_web_viewer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = getIntent().getExtras().getString("URL");
        jsonData = getIntent().getExtras().getString("JSON_DATA");

        noticeWebView = (android.webkit.WebView) findViewById(R.id.notice_web_view);

        WebSettings webSettings = noticeWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);

        noticeWebView.setInitialScale(1);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        noticeWebView.setWebViewClient(new WebViewClient());
        noticeWebView.loadUrl(url);
        System.out.println("Json Data in WebView is : "+jsonData);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode == KeyEvent.KEYCODE_BACK) && noticeWebView.canGoBack())
        {
            System.out.println("Json Data in WebView when click back button is : "+jsonData);
            Intent notice = new Intent(getApplicationContext(), NoticeActivity.class);
            notice.putExtra("JSON_DATA", jsonData);
            startActivity(notice);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
