package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.ProgressDialogQuotes;

import java.security.SecureRandom;

public class AllWebView extends AppCompatActivity {

    WebView myWebView;
    private String url;
    ProgressDialog progressDialog;
    SecureRandom secureRandom;
    ProgressDialogQuotes progressDialogQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_web_view);

        Bundle recievedBundle = getIntent().getExtras();
        if(recievedBundle != null) {
            url = recievedBundle.getString("URL");
        }


        secureRandom = new SecureRandom();
        myWebView = (WebView) findViewById(R.id.webview);
        progressDialogQuotes = new ProgressDialogQuotes();
        progressDialog = new ProgressDialog(AllWebView.this);
        progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(true);
        WebSettings webSettings = null;
        if (myWebView != null) {

            webSettings = myWebView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);
            webSettings.setJavaScriptEnabled(true);

            if(!("http://result.ewubd.edu/").equals(url))
            {
                myWebView.setInitialScale(90);
            }

            myWebView.setWebViewClient(new MyWebViewClient());
            myWebView.loadUrl(url);
        }


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


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

    public void fabclickhandler(View view)
    {
        if (myWebView.canGoBack())
        {
            myWebView.goBack();
        }
        else
        {
            finish();
        }
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
}
