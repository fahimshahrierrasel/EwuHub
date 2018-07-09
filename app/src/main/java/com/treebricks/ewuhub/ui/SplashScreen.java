package com.treebricks.ewuhub.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.treebricks.ewuhub.R;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class SplashScreen extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
    @Override
    public void onStart() {
        super.onStart();

        View decorView = getWindow().getDecorView();
        // Hide Status Bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        int SPLASH_TIME_OUT = 1200;
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent applicationHome = new Intent(SplashScreen.this, ApplicationHome.class);
                startActivity(applicationHome);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
