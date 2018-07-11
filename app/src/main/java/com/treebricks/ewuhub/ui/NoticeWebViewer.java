package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.model.ProgressDialogQuotes;
import java.security.SecureRandom;

public class NoticeWebViewer extends AppCompatActivity {


    android.webkit.WebView noticeWebView;
    ProgressDialog progressDialog;
    ProgressDialogQuotes progressDialogQuotes;
    SecureRandom secureRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_web_viewer_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialogQuotes = new ProgressDialogQuotes();
        secureRandom = new SecureRandom();
        String url = getIntent().getExtras().getString("URL");

        noticeWebView = (android.webkit.WebView) findViewById(R.id.notice_web_view);

        WebSettings webSettings = null;
        if (noticeWebView != null) {
            webSettings = noticeWebView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setSupportZoom(true);
            webSettings.setJavaScriptEnabled(true);

            noticeWebView.setInitialScale(1);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setUseWideViewPort(true);
            progressDialog = new ProgressDialog(NoticeWebViewer.this);
            progressDialog.setMessage(progressDialogQuotes.getQuote(secureRandom.nextInt(28)));
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
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
