package com.treebricks.ewuhub.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.treebricks.ewuhub.R;
import android.os.Handler;

public class SplashScreen extends Activity
{

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent applicaitonHome = new Intent(SplashScreen.this, ApplicationHome.class);
                startActivity(applicaitonHome);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
