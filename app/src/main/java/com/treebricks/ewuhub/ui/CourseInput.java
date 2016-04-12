package com.treebricks.ewuhub.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.AllCoursesDataSource;
import java.util.ArrayList;

public class CourseInput extends AppCompatActivity {

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
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    TextInputLayout textField1;
    TextInputLayout textField2;
    TextInputLayout textField3;
    TextInputLayout textField4;
    ActionBar actionBar;

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

        allCoursesDataSource = new AllCoursesDataSource();
        ArrayList<String> allCrs = allCoursesDataSource.findAll(CourseInput.this);
        allCourses = allCrs.toArray();


        editText1 = (EditText) findViewById(R.id.course_one_edit_text);
        editText2 = (EditText) findViewById(R.id.course_two_edit_text);
        editText3 = (EditText) findViewById(R.id.course_three_edit_text);
        editText4 = (EditText) findViewById(R.id.course_four_edit_text);
        textField1 = (TextInputLayout) findViewById(R.id.text_field_one);
        textField2 = (TextInputLayout) findViewById(R.id.text_field_two);
        textField3 = (TextInputLayout) findViewById(R.id.text_field_three);
        textField4 = (TextInputLayout) findViewById(R.id.text_field_four);


        if(totalSubjects != 4)
        {
            if (textField4 != null) {
                textField4.setVisibility(View.INVISIBLE);
            }
            editText4.setVisibility(View.INVISIBLE);
        }





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
                        textField1.setError("Subject-1 is required!");
                    }
                    else
                    {
                        textField1.setError(null);
                    }
                    if("".equals(secondCourse))
                    {
                        textField2.setError("Subject-2 is required!");
                    }
                    else
                    {
                        textField2.setError(null);
                    }
                    if("".equals(thirdCourse))
                    {
                        textField3.setError("Subject-3 is required!");
                    }
                    else
                    {
                        textField3.setError(null);
                    }
                    if(totalSubjects == 4)
                    {
                        fourthCourse = editText4.getText().toString().toUpperCase();
                        if("".equals(fourthCourse))
                        {
                            textField4.setError("Subject-4 is required!");
                        }
                        else
                        {
                            textField4.setError(null);
                        }
                    }
                    else
                        fourthCourse = "NULL";

                    if(!"".equals(firstCourse) && !"".equals(secondCourse) && !"".equals(thirdCourse)
                            && !"".equals(fourthCourse))
                    {
                        // send Data to next activity

                        textField1.setError(null);
                        textField2.setError(null);
                        textField3.setError(null);
                        textField4.setError(null);

                        if(!isSubjectValid(firstCourse))
                        {
                            allright = false;
                            Toast.makeText(CourseInput.this, firstCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(secondCourse))
                        {
                            allright = false;
                            Toast.makeText(CourseInput.this, secondCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(thirdCourse))
                        {
                            allright = false;
                            Toast.makeText(CourseInput.this, thirdCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }
                        if(!isSubjectValid(fourthCourse) && totalSubjects == 4)
                        {
                            allright = false;
                            Toast.makeText(CourseInput.this, fourthCourse + " is not a correct subject! Please give a correct Subject.",Toast.LENGTH_SHORT).show();
                        }

                        if(totalSubjects == 4)
                        {
                            if(firstCourse.equals(secondCourse) || firstCourse.equals(thirdCourse) || firstCourse.equals(fourthCourse) ||
                                    secondCourse.equals(thirdCourse) || secondCourse.equals(fourthCourse) || thirdCourse.equals(fourthCourse))
                            {
                                allright = false;
                                Toast.makeText(CourseInput.this, "You may have inputed the same course twice!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(totalSubjects == 3)
                        {
                            if(firstCourse.equals(secondCourse) || firstCourse.equals(thirdCourse) || secondCourse.equals(thirdCourse))
                            {
                                allright = false;
                                Toast.makeText(CourseInput.this, "You may have inputed the same course twice!",Toast.LENGTH_SHORT).show();
                            }
                        }

                        if(allright)
                        {
                            Bundle sentBundle = new Bundle();
                            sentBundle.putInt(NUMBEROFCOURSES, totalSubjects);
                            sentBundle.putString(FIRSTCOURSE, firstCourse);
                            sentBundle.putString(SECONDCOURSE, secondCourse);
                            sentBundle.putString(THIRDCOURSE, thirdCourse);
                            sentBundle.putString(FOURTHCOURSE, fourthCourse);
                            Intent showSortCourses = new Intent(CourseInput.this, ShowSortCourses.class);
                            showSortCourses.putExtras(sentBundle);
                            startActivity(showSortCourses);
                        }

                    }
                }
            });
        }

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {

        outState.putInt(NUMBEROFCOURSES, totalSubjects);
        outState.putString(FIRSTCOURSE, String.valueOf(editText1));
        outState.putString(SECONDCOURSE, String.valueOf(editText2));
        outState.putString(THIRDCOURSE, String.valueOf(editText3));
        if(totalSubjects == 4)
            outState.putString(FOURTHCOURSE, String.valueOf(editText4));
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        totalSubjects = savedInstanceState.getInt(NUMBEROFCOURSES);
        editText1.setText(savedInstanceState.getString(FIRSTCOURSE));
        editText2.setText(savedInstanceState.getString(SECONDCOURSE));
        editText3.setText(savedInstanceState.getString(THIRDCOURSE));
        if(totalSubjects == 4)
            editText4.setText(savedInstanceState.getString(FOURTHCOURSE));
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
