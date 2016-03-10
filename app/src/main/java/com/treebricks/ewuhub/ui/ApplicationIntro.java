package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.treebricks.ewuhub.R;

public class ApplicationIntro extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {
        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(AppIntroFragment.newInstance("Where is the EwuHub features?", "Slide from left to right of your mobile screen to see the EwuHub's features.", R.drawable.first_intro, Color.parseColor("#009688")));
        addSlide(AppIntroFragment.newInstance("EwuHub Features", "Select from the drawer which feature do you want? Be patience and give the application sometimes to load the feature.",R.drawable.second_intro, Color.parseColor("#03A9F4")));
        addSlide(AppIntroFragment.newInstance("Advising Helper", "Enter for how many subject you want help? Advising Helper will help you to find the combination of section of the courses you input without any confliction between different sections. Cool",R.drawable.third_intro, Color.parseColor("#4CAF50")));
        addSlide(AppIntroFragment.newInstance("Input the Subjects for Advising Helper", "For finding conflict free combination of sections input the course names properly.",R.drawable.fourth_intro, Color.parseColor("#673AB7")));
        addSlide(AppIntroFragment.newInstance("EwuSpirit now on Pocket", "Use most useful EwuSpirit features from EwuHub. Get rid from memorizing EwuSpirit IP address.",R.drawable.fifth_intro, Color.parseColor("#607D8B")));

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        //addSlide(AppIntroFragment.newInstance("Project Zero", "This is a Application",R.drawable.ic_slide1, Color.parseColor("#3F61B5")));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        setFlowAnimation();

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        //setVibrate(false);
        //setVibrateIntensity(30);
    }

    private void loadMainActivity()
    {
        Intent intent = new Intent(this, ApplicationHome.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        //finish();
        loadMainActivity();

    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
        //loadMainActivity();
    }
}
