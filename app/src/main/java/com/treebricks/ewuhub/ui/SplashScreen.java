package com.treebricks.ewuhub.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.treebricks.ewuhub.R;
import com.victor.loading.book.BookLoading;

import android.os.Handler;

public class SplashScreen extends Activity
{
    private BookLoading bookLoading;

    private static int SPLASH_TIME_OUT = 4200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bookLoading = (BookLoading) findViewById(R.id.bookloading);
        if(!bookLoading.isStart())
            bookLoading.start();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent applicaitonHome = new Intent(SplashScreen.this, ApplicationHome.class);
                startActivity(applicaitonHome);
                bookLoading.stop();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
