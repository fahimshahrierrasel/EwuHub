package com.treebricks.ewuhub.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.treebricks.ewuhub.R;

public class AllWebView extends AppCompatActivity {


    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_web_view);


        myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://result.ewubd.edu");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack())
        {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
