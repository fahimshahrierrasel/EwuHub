package com.treebricks.ewuhub.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.treebricks.ewuhub.R;


public class Preferences extends AppCompatActivity
{
    //Toolbar toolbar;
   // ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        setContentView(R.layout.activity_settings);

        MinePreferenceFrament prefFragment = new MinePreferenceFrament();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, prefFragment);
        fragmentTransaction.commit();

        /*actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/
    }
}
