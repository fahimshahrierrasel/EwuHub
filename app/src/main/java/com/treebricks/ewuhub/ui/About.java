package com.treebricks.ewuhub.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.fragments.AboutFragment;

public class About extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    if(getSupportActionBar() != null)
    {
      getSupportActionBar().setTitle("About");
    }

    AboutFragment aboutFragment = new AboutFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(android.R.id.content, aboutFragment);
    fragmentTransaction.commit();
  }
}
