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
                ArrayList<Course> wOLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> wOLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> wOLab3 = (ArrayList<Course>) withoutLab[2];
                for(int loop1 = 0; loop1 < wOLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wOLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab3.size(); loop3++)
                        {
                            if(wOLab1.get(loop1).findConfliction(wOLab2.get(loop2)) && wOLab1.get(loop1).findConfliction(wOLab3.get(loop3))
                                    && wOLab2.get(loop2).findConfliction(wOLab3.get(loop3)))
                            {
                                /*System.out.println("course");
                                System.out.println(wOLab1.get(loop1).printCourse());
                                System.out.println(wOLab2.get(loop2).printCourse());
                                System.out.println(wOLab3.get(loop3).printCourse());*/

                                String header = wOLab1.get(loop1).courseName() + ", " +
                                        wOLab2.get(loop2).courseName() + ", " +
                                        wOLab3.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, wOLab1.get(loop1),
                                        wOLab2.get(loop2),
                                        wOLab3.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 1)
            {
                ArrayList<CourseL> wLab = (ArrayList<CourseL>) withLab[0];
                ArrayList<Course> wOLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> wOLab2 = (ArrayList<Course>) withoutLab[1];

                for(int loop1 = 0; loop1 < wLab.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wOLab1.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab2.size(); loop3++)
                        {
                            if(wLab.get(loop1).findConfliction(wOLab1.get(loop2)) && wLab.get(loop1).findConfliction(wOLab2.get(loop3))
                                    && wOLab1.get(loop2).findConfliction(wOLab2.get(loop3)))
                            {
                                /*System.out.println("course");
                                System.out.println(wLab.get(loop1).printCourse());
                                System.out.println(wOLab1.get(loop2).printCourse());
                                System.out.println(wOLab2.get(loop3).printCourse());*/

                                String header = wLab.get(loop1).courseName() + ", " +
                                        wOLab1.get(loop2).courseName() + ", " +
                                        wOLab2.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header,
                                        wLab.get(loop1),
                                        wOLab1.get(loop2),
                                        wOLab2.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 2)
            {
                ArrayList<CourseL> wLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> wLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<Course> wOLab = (ArrayList<Course>) withoutLab[0];
                for(int loop1 = 0; loop1 < wLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab.size(); loop3++)
                        {
                            if(wLab1.get(loop1).findConfliction(wLab2.get(loop2)) && wLab1.get(loop1).findConfliction(wOLab.get(loop3))
                                    && wLab2.get(loop2).findConfliction(wOLab.get(loop3)))
                            {
                                /*System.out.println("course");
                                System.out.println(wLab1.get(loop1).printCourse());
                                System.out.println(wLab2.get(loop2).printCourse());
                                System.out.println(wOLab.get(loop3).printCourse());*/

                                String header = wLab1.get(loop1).courseName() + ", " +
                                        wLab2.get(loop2).courseName() + ", " +
                                        wOLab.get(loop3).courseName();

                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, wLab1.get(loop1),
                                        wLab2.get(loop2),
                                        wOLab.get(loop3));
                                recycleView.add(viewer);
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 3)
            {
                ArrayList<CourseL> wLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> wLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> wLab3 = (ArrayList<CourseL>) withLab[2];
                for(int loop1 = 0; loop1 < wLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wLab3.size(); loop3++)
                        {
                            if(wLab1.get(loop1).findConfliction(wLab2.get(loop2)) && wLab1.get(loop1).findConfliction(wLab3.get(loop3))
                                    && wLab2.get(loop2).findConfliction(wLab3.get(loop3)))
                            {
                                /*System.out.println("course");
                                System.out.println(wLab1.get(loop1).printCourse());
                                System.out.println(wLab2.get(loop2).printCourse());
                                System.out.println(wLab3.get(loop3).printCourse());*/

                                String header = wLab1.get(loop1).courseName() + ", " +
                                        wLab2.get(loop2).courseName() + ", " +
                                        wLab3.get(loop3).courseName();
                                Log.i(LOGTAG, "COURSE Found !");
                                Viewer viewer = new Viewer(header, wLab1.get(loop1),
                                        wLab2.get(loop2),
                                        wLab3.get(loop3));
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
                ArrayList<Course> wOLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> wOLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> wOLab3 = (ArrayList<Course>) withoutLab[2];
                ArrayList<Course> wOLab4 = (ArrayList<Course>) withoutLab[3];
                for(int loop1 = 0; loop1 < wOLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wOLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < wOLab4.size(); loop4++)
                            {
                                if(wOLab1.get(loop1).findConfliction(wOLab2.get(loop2)) && wOLab1.get(loop1).findConfliction(wOLab3.get(loop3))
                                        && wOLab1.get(loop1).findConfliction(wOLab4.get(loop4)) && wOLab2.get(loop2).findConfliction(wOLab3.get(loop3))
                                        && wOLab2.get(loop2).findConfliction(wOLab4.get(loop4)) && wOLab3.get(loop3).findConfliction(wOLab4.get(loop4)))
                                {
                                    System.out.println("course");
                                    System.out.println(wOLab1.get(loop1).printCourse());
                                    System.out.println(wOLab2.get(loop2).printCourse());
                                    System.out.println(wOLab3.get(loop3).printCourse());
                                    System.out.println(wOLab4.get(loop4).printCourse());
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 1)
            {
                ArrayList<CourseL> wLab = (ArrayList<CourseL>) withLab[0];
                ArrayList<Course> wOLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> wOLab2 = (ArrayList<Course>) withoutLab[1];
                ArrayList<Course> wOLab3 = (ArrayList<Course>) withoutLab[2];
                for(int loop1 = 0; loop1 < wLab.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wOLab1.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab2.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < wOLab3.size(); loop4++)
                            {
                                if(wLab.get(loop1).findConfliction(wOLab1.get(loop2)) && wLab.get(loop1).findConfliction(wOLab2.get(loop3))
                                        && wLab.get(loop1).findConfliction(wOLab3.get(loop4)) && wOLab1.get(loop2).findConfliction(wOLab2.get(loop3))
                                        && wOLab1.get(loop2).findConfliction(wOLab3.get(loop4)) && wOLab2.get(loop3).findConfliction(wOLab3.get(loop4)))
                                {
                                    System.out.println("course");
                                    System.out.println(wLab.get(loop1).printCourse());
                                    System.out.println(wOLab1.get(loop2).printCourse());
                                    System.out.println(wOLab2.get(loop3).printCourse());
                                    System.out.println(wOLab3.get(loop4).printCourse());
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 2)
            {
                ArrayList<CourseL> wLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> wLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<Course> wOLab1 = (ArrayList<Course>) withoutLab[0];
                ArrayList<Course> wOLab2 = (ArrayList<Course>) withoutLab[1];
                for(int loop1 = 0; loop1 < wLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wOLab1.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < wOLab2.size(); loop4++)
                            {
                                if(wLab1.get(loop1).findConfliction(wLab2.get(loop2)) && wLab1.get(loop1).findConfliction(wOLab1.get(loop3))
                                        && wLab1.get(loop1).findConfliction(wOLab2.get(loop4)) && wLab2.get(loop2).findConfliction(wOLab1.get(loop3))
                                        && wLab2.get(loop2).findConfliction(wOLab2.get(loop4)) && wOLab1.get(loop3).findConfliction(wOLab2.get(loop4)))
                                {
                                    System.out.println("course");
                                    System.out.println(wLab1.get(loop1).printCourse());
                                    System.out.println(wLab2.get(loop2).printCourse());
                                    System.out.println(wOLab1.get(loop3).printCourse());
                                    System.out.println(wOLab2.get(loop4).printCourse());
                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 3)
            {
                ArrayList<CourseL> wLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> wLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> wLab3 = (ArrayList<CourseL>) withLab[2];
                ArrayList<Course> wOLab = (ArrayList<Course>) withoutLab[0];
                for(int loop1 = 0; loop1 < wLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < wOLab.size(); loop4++)
                            {
                                if(wLab1.get(loop1).findConfliction(wLab2.get(loop2)) && wLab1.get(loop1).findConfliction(wLab3.get(loop3))
                                        && wLab1.get(loop1).findConfliction(wOLab.get(loop4)) && wLab2.get(loop2).findConfliction(wLab3.get(loop3))
                                        && wLab2.get(loop2).findConfliction(wOLab.get(loop4)) && wLab3.get(loop3).findConfliction(wOLab.get(loop4)))
                                {
                                    System.out.println("course");
                                    System.out.println(wLab1.get(loop1).printCourse());
                                    System.out.println(wLab2.get(loop2).printCourse());
                                    System.out.println(wLab3.get(loop3).printCourse());
                                    System.out.println(wOLab.get(loop4).printCourse());

                                }
                            }
                        }
                    }
                }
            }
            else if(totalLabCourse == 4)
            {
                ArrayList<CourseL> wLab1 = (ArrayList<CourseL>) withLab[0];
                ArrayList<CourseL> wLab2 = (ArrayList<CourseL>) withLab[1];
                ArrayList<CourseL> wLab3 = (ArrayList<CourseL>) withLab[2];
                ArrayList<CourseL> wLab4 = (ArrayList<CourseL>) withLab[3];
                for(int loop1 = 0; loop1 < wLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < wLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < wLab3.size(); loop3++)
                        {
                            for(int loop4 = 0; loop4 < wLab4.size(); loop4++)
                            {
                                if(wLab1.get(loop1).findConfliction(wLab2.get(loop2)) && wLab1.get(loop1).findConfliction(wLab3.get(loop3))
                                        && wLab1.get(loop1).findConfliction(wLab4.get(loop4)) && wLab2.get(loop2).findConfliction(wLab3.get(loop3))
                                        && wLab2.get(loop2).findConfliction(wLab4.get(loop4)) && wLab3.get(loop3).findConfliction(wLab4.get(loop4)))
                                {
                                    System.out.println("course");
                                    System.out.println(wLab1.get(loop1).printCourse());
                                    System.out.println(wLab2.get(loop2).printCourse());
                                    System.out.println(wLab3.get(loop3).printCourse());
                                    System.out.println(wLab4.get(loop4).printCourse());

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
