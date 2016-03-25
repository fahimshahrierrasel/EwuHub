package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.treebricks.ewuhub.R;

public class NoticeWebViewer extends AppCompatActivity {


    android.webkit.WebView noticeWebView;
    private String jsonData;
    ActionBar actionBar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_web_viewer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = getIntent().getExtras().getString("URL");
        jsonData = getIntent().getExtras().getString("JSON_DATA");

        noticeWebView = (android.webkit.WebView) findViewById(R.id.notice_web_view);

        WebSettings webSettings = null;
        if (noticeWebView != null) {
            webSettings = noticeWebView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);
            //webSettings.setJavaScriptEnabled(true);

            noticeWebView.setInitialScale(1);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            progressDialog = new ProgressDialog(NoticeWebViewer.this);
            progressDialog.setMessage("Please be patience!\nNotice is Loading......");
            progressDialog.show();
            noticeWebView.setWebViewClient(new MyWebViewClient());
            noticeWebView.loadUrl(url);
        }
        progressDialog.setCanceledOnTouchOutside(true);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode == KeyEvent.KEYCODE_BACK) && noticeWebView.canGoBack())
        {
            Intent notice = new Intent(getApplicationContext(), NoticeActivity.class);
            notice.putExtra("JSON_DATA", jsonData);
            startActivity(notice);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent notice = new Intent(getApplicationContext(), NoticeActivity.class);
        notice.putExtra("JSON_DATA", jsonData);
        startActivity(notice);
        finish();
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

}
