package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.treebricks.ewuhub.R;

import java.util.ArrayList;

public class CourseDetails extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static final String COURSENAME = "COURSENAME";
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    public static final String FACULTY = "FACULTY";
    public static final String SHOWSORTCOURSEBUNDLE = "SHOWSORTCOURSEBUNDLE";
    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    int numberSubject;

    public static Bundle firstCourseBundle = null;
    public static Bundle secondCourseBundle = null;
    public static Bundle thirdCourseBundle = null;
    public static Bundle fourthCourseBundle = null;
    public static Bundle showSortCourseBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_activity);
        showSortCourseBundle = getIntent().getBundleExtra(SHOWSORTCOURSEBUNDLE);
        numberSubject = showSortCourseBundle.getInt(NUMBEROFCOURSES);
        firstCourseBundle = getIntent().getBundleExtra(FIRSTCOURSE);
        secondCourseBundle = getIntent().getBundleExtra(SECONDCOURSE);
        thirdCourseBundle = getIntent().getBundleExtra(THIRDCOURSE);
        fourthCourseBundle = getIntent().getBundleExtra(FOURTHCOURSE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (mViewPager != null) {
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("Detail Class", "On Back pressed is sending");
        Intent i = new Intent(CourseDetails.this, ShowSortCourses.class);
        if(showSortCourseBundle != null)
        {
            i.putExtras(showSortCourseBundle);
        }
        startActivity(i);
        finish();
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
        public static final String HASLAB = "HASLAB";
        public static final String SECTION = "SECTION";
        public static final String WEEKDAY = "WEEKDAY";
        public static final String TIMEFROM = "TIMEFROM";
        public static final String TIMETO = "TIMETO";
        public static final String LABWEEKDAY = "LABWEEKDAY";
        public static final String LABTIMEFROM = "LABTIMEFROM";
        public static final String LABTIMETO = "LABTIMETO";
        public static final String FACULTY = "FACULTY";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            boolean hasLab;

            switch(sectionNumber)
            {
                case 1:

                    hasLab = firstCourseBundle.getBoolean(HASLAB);
                    args.putBoolean(HASLAB,hasLab);
                    args.putInt(SECTION,firstCourseBundle.getInt(SECTION));
                    args.putString(WEEKDAY, firstCourseBundle.getString(WEEKDAY));
                    args.putInt(TIMEFROM,firstCourseBundle.getInt(TIMEFROM));
                    args.putInt(TIMETO, firstCourseBundle.getInt(TIMETO));
                    args.putString(FACULTY, firstCourseBundle.getString(FACULTY));
                    if(hasLab)
                    {
                        args.putString(LABWEEKDAY, firstCourseBundle.getString(LABWEEKDAY));
                        args.putInt(LABTIMEFROM, firstCourseBundle.getInt(LABTIMEFROM));
                        args.putInt(LABTIMETO, firstCourseBundle.getInt(LABTIMETO));
                    }
                    break;
                case 2:
                    hasLab = secondCourseBundle.getBoolean(HASLAB);
                    args.putBoolean(HASLAB,hasLab);
                    args.putInt(SECTION,secondCourseBundle.getInt(SECTION));
                    args.putString(WEEKDAY, secondCourseBundle.getString(WEEKDAY));
                    args.putInt(TIMEFROM, secondCourseBundle.getInt(TIMEFROM));
                    args.putInt(TIMETO, secondCourseBundle.getInt(TIMETO));
                    args.putString(FACULTY, secondCourseBundle.getString(FACULTY));
                    if(hasLab)
                    {
                        args.putString(LABWEEKDAY, secondCourseBundle.getString(LABWEEKDAY));
                        args.putInt(LABTIMEFROM, secondCourseBundle.getInt(LABTIMEFROM));
                        args.putInt(LABTIMETO, secondCourseBundle.getInt(LABTIMETO));
                    }
                    break;
                case 3:
                    hasLab = thirdCourseBundle.getBoolean(HASLAB);
                    args.putBoolean(HASLAB,hasLab);
                    args.putInt(SECTION, thirdCourseBundle.getInt(SECTION));
                    args.putString(WEEKDAY, thirdCourseBundle.getString(WEEKDAY));
                    args.putInt(TIMEFROM, thirdCourseBundle.getInt(TIMEFROM));
                    args.putInt(TIMETO, thirdCourseBundle.getInt(TIMETO));
                    args.putString(FACULTY, thirdCourseBundle.getString(FACULTY));
                    if(hasLab)
                    {
                        args.putString(LABWEEKDAY, thirdCourseBundle.getString(LABWEEKDAY));
                        args.putInt(LABTIMEFROM, thirdCourseBundle.getInt(LABTIMEFROM));
                        args.putInt(LABTIMETO, thirdCourseBundle.getInt(LABTIMETO));
                    }
                    break;
                case 4:
                    hasLab = fourthCourseBundle.getBoolean(HASLAB);
                    args.putBoolean(HASLAB,hasLab);
                    args.putInt(SECTION, fourthCourseBundle.getInt(SECTION));
                    args.putString(WEEKDAY, fourthCourseBundle.getString(WEEKDAY));
                    args.putInt(TIMEFROM, fourthCourseBundle.getInt(TIMEFROM));
                    args.putInt(TIMETO, fourthCourseBundle.getInt(TIMETO));
                    args.putString(FACULTY, fourthCourseBundle.getString(FACULTY));
                    if(hasLab)
                    {
                        args.putString(LABWEEKDAY, fourthCourseBundle.getString(LABWEEKDAY));
                        args.putInt(LABTIMEFROM, fourthCourseBundle.getInt(LABTIMEFROM));
                        args.putInt(LABTIMETO, fourthCourseBundle.getInt(LABTIMETO));
                    }
                    break;
            }


            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_course_details, container, false);
            TextView sectionNumberTextView;
            TextView weekDayTextView;
            TextView timeFromTextView;
            TextView timeToTextView;
            TextView labWeekTextView;
            TextView labWeekDayTextView;
            TextView labTimeFTextView;
            TextView labTimeFromTextView;
            TextView labTimeTTextView;
            TextView labTimeToTextView;
            TextView facultyTextView;
            sectionNumberTextView = (TextView) rootView.findViewById(R.id.section_number);
            weekDayTextView = (TextView) rootView.findViewById(R.id.week_day);
            timeFromTextView = (TextView) rootView.findViewById(R.id.time_from);
            timeToTextView = (TextView) rootView.findViewById(R.id.time_to);
            facultyTextView = (TextView) rootView.findViewById(R.id.faculty);
            labWeekTextView = (TextView) rootView.findViewById(R.id.labw);
            labWeekDayTextView = (TextView) rootView.findViewById(R.id.lab_week_day);
            labTimeFTextView = (TextView) rootView.findViewById(R.id.labtimef);
            labTimeFromTextView = (TextView) rootView.findViewById(R.id.lab_time_from);
            labTimeTTextView = (TextView) rootView.findViewById(R.id.labtimet);
            labTimeToTextView = (TextView) rootView.findViewById(R.id.lab_time_to);

            sectionNumberTextView.setText(String.valueOf(getArguments().getInt(SECTION)));
            weekDayTextView.setText(getArguments().getString(WEEKDAY));
            timeFromTextView.setText(time12HourFormat(getArguments().getInt(TIMEFROM)));
            timeToTextView.setText(time12HourFormat(getArguments().getInt(TIMETO)));
            facultyTextView.setTextColor(Color.parseColor("#009688"));
            facultyTextView.setText(getArguments().getString(FACULTY));
            if(getArguments().getBoolean(HASLAB))
            {
                labWeekDayTextView.setText(getArguments().getString(LABWEEKDAY));
                labTimeFromTextView.setText(time12HourFormat(getArguments().getInt(LABTIMEFROM)));
                labTimeToTextView.setText(time12HourFormat(getArguments().getInt(LABTIMETO)));
            }
            else
            {
                labWeekTextView.setVisibility(View.INVISIBLE);
                labWeekDayTextView.setVisibility(View.INVISIBLE);
                labTimeFTextView.setVisibility(View.INVISIBLE);
                labTimeFromTextView.setVisibility(View.INVISIBLE);
                labTimeTTextView.setVisibility(View.INVISIBLE);
                labTimeToTextView.setVisibility(View.INVISIBLE);
            }

            return rootView;
        }
        public String time12HourFormat(int time)
        {
            int universalTime = time;
            int minute = universalTime % 100;
            int hour =  universalTime / 100;
            return String.format("%d:%02d %s", ((hour == 0 || hour == 12) ? 12 : hour % 12), minute,
                    (hour < 12 ? "AM" : "PM"));
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
            // Show 3 total pages.
            return numberSubject;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return firstCourseBundle.getString(COURSENAME);
                case 1:
                    return secondCourseBundle.getString(COURSENAME);
                case 2:
                    return thirdCourseBundle.getString(COURSENAME);
                case 3:
                    return fourthCourseBundle.getString(COURSENAME);
            }
            return null;
        }
    }
}
