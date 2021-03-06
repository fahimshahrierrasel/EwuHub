package com.treebricks.ewuhub.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.fragments.PreferenceFragment;


public class Preferences extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Preferences");
        }

        PreferenceFragment prefFragment = new PreferenceFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, prefFragment);
        fragmentTransaction.commit();

    }
}
