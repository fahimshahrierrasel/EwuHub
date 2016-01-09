package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.florent37.materialtextfield.MaterialTextField;
import com.treebricks.ewuhub.R;

public class CourseInput extends AppCompatActivity {

    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    public static final String LOGTAG = "EwuHuB";

    private int totalSubjects = 0;
    private String firstCourse = null;
    private String secondCourse = null;
    private String thirdCourse = null;
    private String fourthCourse = null;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    MaterialTextField textField4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recived data from previous activity
        Bundle recievedBundle = getIntent().getExtras();
        if(recievedBundle != null)
        {
            totalSubjects = recievedBundle.getInt(NUMBEROFCOURSES);
        }

        editText1 = (EditText) findViewById(R.id.course_one_edit_text);
        editText2 = (EditText) findViewById(R.id.course_two_edit_text);
        editText3 = (EditText) findViewById(R.id.course_three_edit_text);
        editText4 = (EditText) findViewById(R.id.course_four_edit_text);
        textField4 = (MaterialTextField) findViewById(R.id.course_four_edit_text_holder);

        if(totalSubjects != 4)
        {
            textField4.setVisibility(View.INVISIBLE);
            editText4.setVisibility(View.INVISIBLE);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // get text from the edit text
                firstCourse = editText1.getText().toString().toUpperCase();
                secondCourse = editText2.getText().toString().toUpperCase();
                thirdCourse = editText3.getText().toString().toUpperCase();
                fourthCourse = editText4.getText().toString().toUpperCase();

                // send Data to next activity
                Bundle sentBundle = new Bundle();
                sentBundle.putInt(NUMBEROFCOURSES, totalSubjects);
                sentBundle.putString(FIRSTCOURSE, firstCourse);
                sentBundle.putString(SECONDCOURSE, secondCourse);
                sentBundle.putString(THIRDCOURSE, thirdCourse);
                sentBundle.putString(FOURTHCOURSE, fourthCourse);
                Log.i(LOGTAG, firstCourse + "," + secondCourse + "," + thirdCourse + "," + fourthCourse + " Recived by ShowSortCourse!");
                Intent showSortCourses = new Intent(CourseInput.this, ShowSortCourses.class);
                showSortCourses.putExtras(sentBundle);
                startActivity(showSortCourses);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {

        outState.putInt(NUMBEROFCOURSES, totalSubjects);
        /*outState.putString(FIRSTCOURSEEDITTEXT, String.valueOf(editText1));
        outState.putString(SECONDCOURSEEDITTEXT, String.valueOf(editText2));
        outState.putString(THIRDCOURSEEDITTEXT, String.valueOf(editText3));
        outState.putString(FOURTHCOURSEEDITTEXT, String.valueOf(editText4));*/
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        totalSubjects = savedInstanceState.getInt(NUMBEROFCOURSES);
        /*editText1.setText(savedInstanceState.getString(FIRSTCOURSEEDITTEXT));
        editText2.setText(savedInstanceState.getString(SECONDCOURSEEDITTEXT));
        editText3.setText(savedInstanceState.getString(THIRDCOURSEEDITTEXT));
        editText4.setText(savedInstanceState.getString(FOURTHCOURSEEDITTEXT));*/
    }

}
