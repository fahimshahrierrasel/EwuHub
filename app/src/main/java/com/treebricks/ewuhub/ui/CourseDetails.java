package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.treebricks.ewuhub.R;

import model.CourseL;

public class CourseDetails extends AppCompatActivity
{


    public static final String HASLAB = "HASLAB";
    public static final String COURSENAME = "COURSENAME";
    public static final String SECTION = "SECTION";
    public static final String WEEKDAY = "WEEKDAY";
    public static final String TIMEFROM = "TIMEFROM";
    public static final String TIMETO = "TIMETO";
    public static final String LABWEEKDAY = "LABWEEKDAY";
    public static final String LABTIMEFROM = "LABTIMEFROM";
    public static final String LABTIMETO = "LABTIMETO";
    public static final String SHOWSORTCOURSEBUNDLE = "SHOWSORTCOURSEBUNDLE";

    TextView courseNameTextView;
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

    private String courseName = "Subject Name";
    private int section = 0;
    private String weekDay = "SMTWT";
    private int timeFrom = 0;
    private int timeTo = 0;
    private String labWeekDay = "SMTWT";
    private int labTimeFrom = 0;
    private int labTimeTo = 0;
    private boolean hasLab = false;
    Bundle showSortCourseBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle recivedBundle = getIntent().getExtras();
        showSortCourseBundle = getIntent().getBundleExtra(SHOWSORTCOURSEBUNDLE);
        if(recivedBundle != null)
        {
            hasLab = recivedBundle.getBoolean(HASLAB);
            courseName = recivedBundle.getString(COURSENAME);
            section = recivedBundle.getInt(SECTION);
            weekDay = recivedBundle.getString(WEEKDAY);
            timeFrom = recivedBundle.getInt(TIMEFROM);
            timeTo = recivedBundle.getInt(TIMETO);
            if(hasLab)
            {
                labWeekDay = recivedBundle.getString(LABWEEKDAY);
                labTimeFrom = recivedBundle.getInt(LABTIMEFROM);
                labTimeTo = recivedBundle.getInt(LABTIMETO);
            }
        }


        courseNameTextView = (TextView) findViewById(R.id.course_name);
        sectionNumberTextView = (TextView) findViewById(R.id.section_number);
        weekDayTextView = (TextView) findViewById(R.id.week_day);
        timeFromTextView = (TextView) findViewById(R.id.time_from);
        timeToTextView = (TextView) findViewById(R.id.time_to);
        labWeekTextView = (TextView) findViewById(R.id.labw);
        labWeekDayTextView = (TextView) findViewById(R.id.lab_week_day);
        labTimeFTextView = (TextView) findViewById(R.id.labtimef);
        labTimeFromTextView = (TextView) findViewById(R.id.lab_time_from);
        labTimeTTextView = (TextView) findViewById(R.id.labtimet);
        labTimeToTextView = (TextView) findViewById(R.id.lab_time_to);


        courseNameTextView.setText(courseName);
        sectionNumberTextView.setText(String.valueOf(section));
        weekDayTextView.setText(weekDay);
        timeFromTextView.setText(time12HourFormat(timeFrom));
        timeToTextView.setText(time12HourFormat(timeTo));
        if(hasLab)
        {
            labWeekDayTextView.setText(labWeekDay);
            labTimeFromTextView.setText(time12HourFormat(labTimeFrom));
            labTimeToTextView.setText(time12HourFormat(labTimeTo));
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public String time12HourFormat(int time)
    {
        int universalTime = time;
        int minute = universalTime % 100;
        int hour =  universalTime / 100;
        return String.format("%d:%02d %s", ((hour == 0 || hour == 12) ? 12 : hour % 12), minute,
                (hour < 12 ? "AM" : "PM"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(CourseDetails.this, ShowSortCourses.class);
        if(showSortCourseBundle != null)
        {
            i.putExtras(showSortCourseBundle);
        }
        startActivity(i);
        finish();
    }
}
