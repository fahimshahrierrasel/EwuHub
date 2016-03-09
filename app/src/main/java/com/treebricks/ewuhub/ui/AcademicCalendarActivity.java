package com.treebricks.ewuhub.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.treebricks.ewuhub.R;

import model.CalendarData;

public class AcademicCalendarActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    ActionBar actionBar;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_WEB_FILE = "web_file";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            CalendarData calendarData = new CalendarData();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            switch(sectionNumber)
            {
                case 1:
                    args.putString(ARG_WEB_FILE, calendarData.getUndergradute());
                    break;
                case 2:
                    args.putString(ARG_WEB_FILE, calendarData.getUndergradutePharmacy());
                    break;
                case 3:
                    args.putString(ARG_WEB_FILE, calendarData.getGraduate());
                    break;
                case 4:
                    args.putString(ARG_WEB_FILE, calendarData.getGraduatePharmacy());
                    break;
            }
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_academic_calendar, container, false);
            WebView webView = (WebView) rootView.findViewById(R.id.calendar_webview);

            //webView.loadUrl(getArguments().getString(ARG_WEB_FILE));
            webView.setWebViewClient(new WebViewClient());

            webView.loadData(getArguments().getString(ARG_WEB_FILE),"text/html","UTF-8");

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Undergraduate";
                case 1:
                    return "Undergraduate Pharmacy";
                case 2:
                    return "Graduate";
                case 3:
                    return "Graduate Pharmacy";
            }
            return null;
        }
    }
}
