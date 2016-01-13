package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.CourseDataSource;
import com.treebricks.ewuhub.database.CourseLDataSource;
import com.treebricks.ewuhub.database.LabDataSource;
import com.treebricks.ewuhub.view.MyAdapter;
import com.treebricks.ewuhub.view.Viewer;

import java.util.ArrayList;

import model.Course;
import model.CourseL;

public class ShowSortCourses extends AppCompatActivity
{


    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    public static final String LOGTAG = "EwuHuB";



    private ArrayList<ArrayList<CourseL>> wLabCourses = new ArrayList<ArrayList<CourseL>>();
    private ArrayList<ArrayList<Course>> wOLabCourses = new ArrayList<ArrayList<Course>>();
    private Object[] labCourse;
    private ArrayList<String> subjects = new ArrayList<String>();
    private int totalLabCourse = 0;
    private int numberOfCourse = 0;
    private String firstCourse = "NULL",
            secondCourse = "NULL",
            thirdCourse = "NULL",
            fourthCourse = "NULL";

    // Variable for database

    CourseDataSource courseDataSource;
    CourseLDataSource courseLDataSource;
    LabDataSource labDataSource;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Viewer> recycleView = new ArrayList<Viewer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sort_courses);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        courseDataSource = new CourseDataSource();
        courseLDataSource = new CourseLDataSource();
        labDataSource = new LabDataSource();
        ArrayList<String> lCrs = labDataSource.findAll(ShowSortCourses.this);
        labCourse = lCrs.toArray();


        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            numberOfCourse = bundle.getInt(NUMBEROFCOURSES);
            firstCourse = bundle.getString(FIRSTCOURSE);
            secondCourse = bundle.getString(SECONDCOURSE);
            thirdCourse = bundle.getString(THIRDCOURSE);
            fourthCourse = bundle.getString(FOURTHCOURSE);
            Log.i(LOGTAG, firstCourse + "," + secondCourse + "," + thirdCourse + "," + fourthCourse + " Recived by ShowSortCourse!");
        }

        // add course to subjects.
        setSubjects(numberOfCourse);
        totalLabCourse = findTotalLabCourse();
        getCourseFromDataBase();
        findConflictFreeCourse();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);



        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new MyAdapter(recycleView, numberOfCourse);
        mRecyclerView.setAdapter(mAdapter);


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putInt(NUMBEROFCOURSES, numberOfCourse);
        outState.putString(FIRSTCOURSE, firstCourse);
        outState.putString(SECONDCOURSE, secondCourse);
        outState.putString(THIRDCOURSE, thirdCourse);
        outState.putString(FOURTHCOURSE, fourthCourse);
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberOfCourse = savedInstanceState.getInt(NUMBEROFCOURSES);
        firstCourse = savedInstanceState.getString(FIRSTCOURSE);
        secondCourse = savedInstanceState.getString(SECONDCOURSE);
        thirdCourse = savedInstanceState.getString(THIRDCOURSE);
        fourthCourse = savedInstanceState.getString(FOURTHCOURSE);
    }


    // Method for sorting


    public void setSubjects(int number)
    {
        if(numberOfCourse == 3)
        {
            subjects.clear();
            subjects.add(firstCourse);
            subjects.add(secondCourse);
            subjects.add(thirdCourse);
        }
        else if(numberOfCourse == 4)
        {
            subjects.clear();
            subjects.add(firstCourse);
            subjects.add(secondCourse);
            subjects.add(thirdCourse);
            subjects.add(fourthCourse);
        }
    }


    void getCourseFromDataBase()
    {
        for(int i = 0; i < subjects.size(); i++)
        {
            if(labVarifier(subjects.get(i)))
            {
                ArrayList<CourseL> coursesWithLab = courseLDataSource.findAll(ShowSortCourses.this, subjects.get(i));
                wLabCourses.add(coursesWithLab);
            }
            else if(!labVarifier(subjects.get(i)))
            {
                ArrayList<Course> coursesWithOutLab = courseDataSource.findAll(ShowSortCourses.this, subjects.get(i));
                wOLabCourses.add(coursesWithOutLab);
            }
        }
    }

    public int findTotalLabCourse()
    {
        int total = 0;
        for(int i = 0; i < subjects.size(); i++)
        {
            if(labVarifier(subjects.get(i)))
            {
                total++;
            }
        }
        return total;
    }

    public boolean labVarifier(String subject)
    {
        for(int i = 0; i < labCourse.length; i++)
        {
            if (labCourse[i].equals(subject))
            {
                return true;
            }
        }
        return false;
    }


    public void findConflictFreeCourse()
    {
        Object[] withLab = wLabCourses.toArray();
        Object[] withoutLab = wOLabCourses.toArray();
        if(numberOfCourse == 3)
        {
            if(totalLabCourse == 0)
            {
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> withOutLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> withOutLab3 = (ArrayList<Course>) withoutLab[2];
                for(int loop1 = 0; loop1 < withOutLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withOutLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab3.size(); loop3++)
                        {
                            if(withOutLab1.get(loop1).findConfliction(withOutLab2.get(loop2)) && withOutLab1.get(loop1).findConfliction(withOutLab3.get(loop3))
                                    && withOutLab2.get(loop2).findConfliction(withOutLab3.get(loop3)))
                            {


                                String header = withOutLab1.get(loop1).courseName() + ", " +
                                        withOutLab2.get(loop2).courseName() + ", " +
                                        withOutLab3.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, withOutLab1.get(loop1),
                                        withOutLab2.get(loop2),
                                        withOutLab3.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 1)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> withOutLab2 = (ArrayList<Course>) withoutLab[1];

                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withOutLab1.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab2.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withOutLab1.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab2.get(loop3))
                                    && withOutLab1.get(loop2).findConfliction(withOutLab2.get(loop3)))
                            {


                                String header = withLab1.get(loop1).courseName() + ", " +
                                        withOutLab1.get(loop2).courseName() + ", " +
                                        withOutLab2.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header,
                                        withLab1.get(loop1),
                                        withOutLab1.get(loop2),
                                        withOutLab2.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 2)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> withLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab1.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab1.get(loop3))
                                    && withLab2.get(loop2).findConfliction(withOutLab1.get(loop3)))
                            {


                                String header = withLab1.get(loop1).courseName() + ", " +
                                        withLab2.get(loop2).courseName() + ", " +
                                        withOutLab1.get(loop3).courseName();

                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, withLab1.get(loop1),
                                        withLab2.get(loop2),
                                        withOutLab1.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 3)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> withLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> withLab3 = (ArrayList<CourseL>) withLab[2];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withLab3.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withLab3.get(loop3))
                                    && withLab2.get(loop2).findConfliction(withLab3.get(loop3)))
                            {


                                String header = withLab1.get(loop1).courseName() + ", " +
                                        withLab2.get(loop2).courseName() + ", " +
                                        withLab3.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, withLab1.get(loop1),
                                        withLab2.get(loop2),
                                        withLab3.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
        }
        else if(numberOfCourse == 4)
        {
            if(totalLabCourse == 0)
            {
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> withOutLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> withOutLab3 = (ArrayList<Course>) withoutLab[2];
                ArrayList<Course> withOutLab4 = (ArrayList<Course>) withoutLab[3];
                for(int loop1 = 0; loop1 < withOutLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withOutLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < withOutLab4.size(); loop4++)
                            {
                                if(withOutLab1.get(loop1).findConfliction(withOutLab2.get(loop2)) && withOutLab1.get(loop1).findConfliction(withOutLab3.get(loop3))
                                        && withOutLab1.get(loop1).findConfliction(withOutLab4.get(loop4)) && withOutLab2.get(loop2).findConfliction(withOutLab3.get(loop3))
                                        && withOutLab2.get(loop2).findConfliction(withOutLab4.get(loop4)) && withOutLab3.get(loop3).findConfliction(withOutLab4.get(loop4)))
                                {


                                    String header = withOutLab1.get(loop1).courseName() + ", " +
                                            withOutLab2.get(loop2).courseName() + ", " +
                                            withOutLab3.get(loop3).courseName() + "," +
                                            withOutLab4.get(loop4).courseName();
                                    Log.i(LOGTAG, "COURSE Found !");
                                    Viewer viewer = new Viewer(header, withOutLab1.get(loop1),
                                            withOutLab2.get(loop2),
                                            withOutLab3.get(loop3),withOutLab4.get(loop4));
                                    recycleView.add(viewer);
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 1)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> withOutLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> withOutLab3 = (ArrayList<Course>) withoutLab[2];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withOutLab1.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab2.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < withOutLab3.size(); loop4++)
                            {
                                if(withLab1.get(loop1).findConfliction(withOutLab1.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab2.get(loop3))
                                        && withLab1.get(loop1).findConfliction(withOutLab3.get(loop4)) && withOutLab1.get(loop2).findConfliction(withOutLab2.get(loop3))
                                        && withOutLab1.get(loop2).findConfliction(withOutLab3.get(loop4)) && withOutLab2.get(loop3).findConfliction(withOutLab3.get(loop4)))
                                {

                                    String header = withLab1.get(loop1).courseName() + ", " +
                                            withOutLab1.get(loop2).courseName() + ", " +
                                            withOutLab2.get(loop3).courseName() +
                                            "," + withOutLab3.get(loop4).courseName();
                                    Log.i(LOGTAG, "COURSE Found !");
                                    Viewer viewer = new Viewer(header,
                                            withLab1.get(loop1),
                                            withOutLab1.get(loop2),
                                            withOutLab2.get(loop3),withOutLab3.get(loop4));
                                    recycleView.add(viewer);
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 2)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> withLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> withOutLab2 = (ArrayList<Course>) withoutLab[1];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab1.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < withOutLab2.size(); loop4++)
                            {
                                if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab1.get(loop3))
                                        && withLab1.get(loop1).findConfliction(withOutLab2.get(loop4)) && withLab2.get(loop2).findConfliction(withOutLab1.get(loop3))
                                        && withLab2.get(loop2).findConfliction(withOutLab2.get(loop4)) && withOutLab1.get(loop3).findConfliction(withOutLab2.get(loop4)))
                                {

                                    String header = withLab1.get(loop1).courseName() + ", " +
                                            withLab2.get(loop2).courseName() + ", " +
                                            withOutLab1.get(loop3).courseName() +
                                            "," + withOutLab2.get(loop4).courseName();

                                    Log.i(LOGTAG, "COURSE Found !");
                                    Viewer viewer = new Viewer(header, withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withOutLab1.get(loop3),
                                            withOutLab2.get(loop4));
                                    recycleView.add(viewer);
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 3)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> withLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> withLab3 = (ArrayList<CourseL>) withLab[2];
                ArrayList<Course> withOutLab1 = (ArrayList<Course>) withoutLab[0];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < withOutLab1.size(); loop4++)
                            {
                                if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withLab3.get(loop3))
                                        && withLab1.get(loop1).findConfliction(withOutLab1.get(loop4)) && withLab2.get(loop2).findConfliction(withLab3.get(loop3))
                                        && withLab2.get(loop2).findConfliction(withOutLab1.get(loop4)) && withLab3.get(loop3).findConfliction(withOutLab1.get(loop4)))
                                {


                                    String header = withLab1.get(loop1).courseName() + ", " +
                                            withLab2.get(loop2).courseName() + ", " +
                                            withLab3.get(loop3).courseName() + "," +
                                            withOutLab1.get(loop4).courseName();

                                    Log.i(LOGTAG, "COURSE Found !");
                                    Viewer viewer = new Viewer(header, withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withLab3.get(loop3),
                                            withOutLab1.get(loop4));
                                    recycleView.add(viewer);

                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 4)
            {
                ArrayList<CourseL> withLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> withLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> withLab3 = (ArrayList<CourseL>) withLab[2];
                ArrayList<CourseL> withLab4 = (ArrayList<CourseL>) withLab[3];
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < withLab4.size(); loop4++)
                            {
                                if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withLab3.get(loop3))
                                        && withLab1.get(loop1).findConfliction(withLab4.get(loop4)) && withLab2.get(loop2).findConfliction(withLab3.get(loop3))
                                        && withLab2.get(loop2).findConfliction(withLab4.get(loop4)) && withLab3.get(loop3).findConfliction(withLab4.get(loop4)))
                                {

                                    String header = withLab1.get(loop1).courseName() + ", " +
                                            withLab2.get(loop2).courseName() + ", " +
                                            withLab3.get(loop3).courseName() + "," +
                                            withLab4.get(loop4).courseName();

                                    Log.i(LOGTAG, "COURSE Found !");
                                    Viewer viewer = new Viewer(header, withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withLab3.get(loop3),
                                            withLab4.get(loop4));
                                    recycleView.add(viewer);

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
