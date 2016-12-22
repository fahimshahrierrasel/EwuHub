package com.treebricks.ewuhub.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.AllCoursesDataSource;

import java.util.ArrayList;

public class CoursesInput extends AppCompatActivity {
    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    AllCoursesDataSource allCoursesDataSource;
    private Object[] allCourses;

    private int totalSubjects = 0;
    private String firstCourse = null;
    private String secondCourse = null;
    private String thirdCourse = null;
    private String fourthCourse = null;
    AutoCompleteTextView editText1;
    AutoCompleteTextView editText2;
    AutoCompleteTextView editText3;
    AutoCompleteTextView editText4;
    ImageButton addCourseButton;
    RelativeLayout container;
    private int numberOfSubject = 3;
    ArrayAdapter<String> autoCompleteCourseAdapter;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editText1 = (AutoCompleteTextView) findViewById(R.id.course_one_edit_text);
        editText2 = (AutoCompleteTextView) findViewById(R.id.course_two_edit_text);
        editText3 = (AutoCompleteTextView) findViewById(R.id.course_three_edit_text);
        addCourseButton = (ImageButton) findViewById(R.id.add_course);
        container = (RelativeLayout) findViewById(R.id.secondary_frame_rl_sv_rl);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Advising Helper");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(CoursesInput.this, android.R.color.transparent));

        allCoursesDataSource = new AllCoursesDataSource();
        ArrayList<String> allCrs = allCoursesDataSource.findAll(CoursesInput.this);
        allCourses = allCrs.toArray();
        autoCompleteCourseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, allCrs);

        editText1.setHint(getString(R.string.course_input_hint) + String.valueOf("-1"));
        editText2.setHint(getString(R.string.course_input_hint) + String.valueOf("-2"));
        editText3.setHint(getString(R.string.course_input_hint) + String.valueOf("-3"));

        editText1.setAdapter(autoCompleteCourseAdapter);
        editText2.setAdapter(autoCompleteCourseAdapter);
        editText3.setAdapter(autoCompleteCourseAdapter);

        addCourseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println(numberOfSubject);
                if(numberOfSubject == 3)
                {
                    addCourseButton.setImageDrawable(ContextCompat.getDrawable(CoursesInput.this, R.drawable.cancel));
                    createFourthCourse();
                    ++numberOfSubject;
                }
                else if(numberOfSubject == 4)
                {
                    container.removeView(editText4);
                    editText4 = null;
                    addCourseButton.setImageDrawable(ContextCompat.getDrawable(CoursesInput.this, R.drawable.plus));
                    numberOfSubject--;
                }
            }

        });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    // get text from the edit text
                    boolean allright = true;

                    firstCourse = editText1.getText().toString().toUpperCase();
                    secondCourse = editText2.getText().toString().toUpperCase();
                    thirdCourse = editText3.getText().toString().toUpperCase();
                    if("".equals(firstCourse))
                    {
                        editText1.setError("Subject-1 is required!");
                    }
                    else
                    {
                        editText1.setError(null);
                    }
                    if("".equals(secondCourse))
                    {
                        editText2.setError("Subject-2 is required!");
                    }
                    else
                    {
                        editText2.setError(null);
                    }
                    if("".equals(thirdCourse))
                    {
                        editText3.setError("Subject-3 is required!");
                    }
                    else
                    {
                        editText3.setError(null);
                    }
                    if(numberOfSubject == 4)
                    {
                        fourthCourse = editText4.getText().toString().toUpperCase();
                        if("".equals(fourthCourse))
                        {
                            editText4.setError("Subject-4 is required!");
                        }
                        else
                        {
                            editText4.setError(null);
                        }
                    }
                    else
                        fourthCourse = "NULL";

                    if(!"".equals(firstCourse) && !"".equals(secondCourse) && !"".equals(thirdCourse)
                            && !"".equals(fourthCourse))
                    {
                        // send data to next activity
                        editText1.setError(null);
                        editText2.setError(null);
                        editText3.setError(null);
                        if(numberOfSubject == 4)
                        {
                            editText4.setError(null);
                        }

                        if(!isSubjectValid(firstCourse))
                        {
                            allright = false;
                            Toast.makeText(CoursesInput.this, firstCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(secondCourse))
                        {
                            allright = false;
                            Toast.makeText(CoursesInput.this, secondCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(thirdCourse))
                        {
                            allright = false;
                            Toast.makeText(CoursesInput.this, thirdCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(fourthCourse) && numberOfSubject == 4)
                        {
                            allright = false;
                            Toast.makeText(CoursesInput.this, fourthCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }

                        if(numberOfSubject == 4)
                        {
                            if(firstCourse.equals(secondCourse) || firstCourse.equals(thirdCourse) || firstCourse.equals(fourthCourse) ||
                                    secondCourse.equals(thirdCourse) || secondCourse.equals(fourthCourse) || thirdCourse.equals(fourthCourse))
                            {
                                allright = false;
                                Toast.makeText(CoursesInput.this, "You may have inputed the same course twice!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(numberOfSubject == 3)
                        {
                            if(firstCourse.equals(secondCourse) || firstCourse.equals(thirdCourse) || secondCourse.equals(thirdCourse))
                            {
                                allright = false;
                                Toast.makeText(CoursesInput.this, "You may have inputed the same course twice!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        if(allright)
                        {
                            Bundle sentBundle = new Bundle();
                            sentBundle.putInt(NUMBEROFCOURSES, numberOfSubject);
                            sentBundle.putString(FIRSTCOURSE, firstCourse);
                            sentBundle.putString(SECONDCOURSE, secondCourse);
                            sentBundle.putString(THIRDCOURSE, thirdCourse);
                            sentBundle.putString(FOURTHCOURSE, fourthCourse);
                            Intent showSortCourses = new Intent(CoursesInput.this, ShowSortCourses.class);
                            showSortCourses.putExtras(sentBundle);
                            startActivity(showSortCourses);
                        }

                    }
                }
            });
        }
    }

    private void createFourthCourse() {
        editText4 = new AutoCompleteTextView(CoursesInput.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, editText3.getId());
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editText4.setHint(getString(R.string.course_input_hint) + String.valueOf("-4"));
        editText4.setLayoutParams(params);
        editText4.setGravity(Gravity.CENTER);
        editText4.setAdapter(autoCompleteCourseAdapter);
        container.addView(editText4);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("onSaveInstanceState");
        outState.putInt(NUMBEROFCOURSES, numberOfSubject);
        outState.putString(FIRSTCOURSE, String.valueOf(editText1.getText()));
        outState.putString(SECONDCOURSE, String.valueOf(editText2));
        outState.putString(THIRDCOURSE, String.valueOf(editText3));
        if(numberOfSubject == 4) {
            outState.putString(FOURTHCOURSE, String.valueOf(editText4));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState");
        numberOfSubject = savedInstanceState.getInt(NUMBEROFCOURSES);
        editText1.setText(savedInstanceState.getString(FIRSTCOURSE));
        editText2.setText(savedInstanceState.getString(SECONDCOURSE));
        editText3.setText(savedInstanceState.getString(THIRDCOURSE));
        if(numberOfSubject == 4)
        {
            createFourthCourse();
            editText4.setText(savedInstanceState.getString(FOURTHCOURSE));
        }
    }

    boolean isSubjectValid(String subject)
    {
        boolean valid = false;
        for (Object allCourse : allCourses) {
            if (allCourse.toString().equals(subject)) {
                valid = true;
                break;
            }
        }
        return valid;
    }
}
