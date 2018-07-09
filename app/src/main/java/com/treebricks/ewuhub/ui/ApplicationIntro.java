package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.github.paolorotolo.appintro.AppIntro2;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.fragments.ApplicationIntroFragment;

public class ApplicationIntro extends AppIntro2 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide1),
                getResources().getString(R.string.slide1des),
                R.drawable.intro_ewu, "#3F51B5"));
        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide2),
                getResources().getString(R.string.slide2des),
                R.drawable.intro_helper, "#009688"));
        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide3),
                getResources().getString(R.string.slide3des),
                R.drawable.intro_calendar, "#4CAF50"));
        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide4),
                getResources().getString(R.string.slide4des),
                R.drawable.intro_notice, "#673AB7"));
        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide5),
                getResources().getString(R.string.slide5des),
                R.drawable.intro_torch, "#607D8B"));
        addSlide(ApplicationIntroFragment.newInstance(getResources().getString(R.string.slide6),
                getResources().getString(R.string.slide6des),
                R.drawable.intro_goodluck, "#9C27B0"));

        setFadeAnimation();
        showStatusBar(false);
        showSkipButton(true);
        setProgressButtonEnabled(true);
    }

    private void loadMainActivity()
    {
        startActivity(new Intent(this, ApplicationHome.class));
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
      super.onSkipPressed(currentFragment);
        loadMainActivity();

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
      super.onDonePressed(currentFragment);
        loadMainActivity();
    }
}
