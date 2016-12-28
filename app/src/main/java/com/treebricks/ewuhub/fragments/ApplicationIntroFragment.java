package com.treebricks.ewuhub.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.treebricks.ewuhub.R;

public class ApplicationIntroFragment extends Fragment
{
    public static final String SLIDE_TITLE = "slide_title";
    public static final String SLIDE_DESCRIPTION = "slide_description";
    public static final String SLIDE_IMAGE = "slide_image";
    public static final String SLIDE_COLOR = "slide_color";
    String slideTitle;
    String slideDescription;
    int slideImage;
    String slideColor;
    LinearLayout slide;

    public static ApplicationIntroFragment newInstance(String slideTitle, String slideDescription, int slideImage, String slideColor) {
        ApplicationIntroFragment appIntroFragment = new ApplicationIntroFragment();

        Bundle bundle = new Bundle();
        bundle.putString(SLIDE_TITLE, slideTitle);
        bundle.putString(SLIDE_DESCRIPTION, slideDescription);
        bundle.putInt(SLIDE_IMAGE, slideImage);
        bundle.putString(SLIDE_COLOR, slideColor);

        appIntroFragment.setArguments(bundle);

        return appIntroFragment;
    }

    public ApplicationIntroFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_intro, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        slide = (LinearLayout) view;
        TextView slideTitleTV = (TextView) view.findViewById(R.id.slide_title);
        TextView slideDescriptionTV = (TextView) view.findViewById(R.id.slide_description);
        ImageView slideImageIV = (ImageView) view.findViewById(R.id.slide_image);

        slideTitle =  getArguments().getString(SLIDE_TITLE);
        slideDescription = getArguments().getString(SLIDE_DESCRIPTION);
        slideImage = getArguments().getInt(SLIDE_IMAGE);
        slideColor = getArguments().getString(SLIDE_COLOR);

        slide.setBackgroundColor(Color.parseColor(slideColor));
        slideTitleTV.setText(slideTitle);
        slideDescriptionTV.setText(slideDescription);
        slideImageIV.setImageDrawable(ContextCompat.getDrawable(getActivity(), slideImage));
    }
}
