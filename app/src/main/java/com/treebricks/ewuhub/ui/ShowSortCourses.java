package com.treebricks.ewuhub.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.CourseDataSource;
import com.treebricks.ewuhub.database.CourseLDataSource;
import com.treebricks.ewuhub.database.LabDataSource;
import com.treebricks.ewuhub.view.SortedCourseAdapter;
import com.treebricks.ewuhub.model.SortedCourses;

import java.util.ArrayList;
import com.treebricks.ewuhub.model.Course;
import com.treebricks.ewuhub.model.CourseL;

public class ShowSortCourses extends AppCompatActivity
{
    public static final String NUMBEROFCOURSES = "NUMBEROFCOURSES";
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    public static final String LOGTAG = ShowSortCourses.class.getSimpleName();

    Bundle bundle;
    private ArrayList<ArrayList<CourseL>> wLabCourses = new ArrayList<ArrayList<CourseL>>();
    private ArrayList<ArrayList<Course>> wOLabCourses = new ArrayList<ArrayList<Course>>();
    private Object[] labCourse;
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayList<String> showSubjects = new ArrayList<String>();
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
    ArrayList<SortedCourses> allSortedCourses = new ArrayList<SortedCourses>();
    ActionBar actionBar;
    int firstSection = 1,
            secondSection = 1,
            thirdSection = 1,
            fourthSection = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sort_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        courseDataSource = new CourseDataSource();
        courseLDataSource = new CourseLDataSource();
        labDataSource = new LabDataSource();
        ArrayList<String> lCrs = labDataSource.findAll(ShowSortCourses.this);
        labCourse = lCrs.toArray();

        bundle = getIntent().getExtras();
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
        setSubjects();
        totalLabCourse = findTotalLabCourse();
        getCourseFromDataBase();
        fillShowSubjects();
        findConflictFreeCourse();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new SortedCourseAdapter(allSortedCourses, numberOfCourse);
        mRecyclerView.setAdapter(mAdapter);



        //  Fab Menu
        final FloatingActionMenu fabmenu = (FloatingActionMenu) findViewById(R.id.fab_menu);
        if(fabmenu != null)
        {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                    if (dy > 0 && fabmenu.isShown())
                        fabmenu.hideMenu(true);
                    else if (dy < 0 && !fabmenu.isShown())
                        fabmenu.showMenu(true);
                }
            });
        }
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.menu_item1);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (fabmenu != null) {
                        fabmenu.close(true);
                    }
                    //String inputedText;
                    LayoutInflater inflater = LayoutInflater.from(ShowSortCourses.this);
                    View viewInflated = inflater.inflate(R.layout.section_sort, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowSortCourses.this);
                    builder.setTitle("Sort by Section");
                    // Set up the input
                    final TextView firstTextView = (TextView) viewInflated.findViewById(R.id.textview1);
                    final TextView secondTextView = (TextView) viewInflated.findViewById(R.id.textview2);
                    final TextView thirdTextView = (TextView) viewInflated.findViewById(R.id.textview3);
                    final TextView fourthTextView = (TextView) viewInflated.findViewById(R.id.textview4);


                    final EditText firstInput = (EditText) viewInflated.findViewById(R.id.input);
                    final EditText secondInput = (EditText) viewInflated.findViewById(R.id.input2);
                    final EditText thirdInput = (EditText) viewInflated.findViewById(R.id.input3);
                    final EditText fourthInput = (EditText) viewInflated.findViewById(R.id.input4);

                    final TextInputLayout textInputFourth = (TextInputLayout) viewInflated.findViewById(R.id.textinput4);

                    firstTextView.setText(String.format("%s%s", getString(R.string.sort_section_dialog_text), showSubjects.get(0)));
                    secondTextView.setText(String.format("%s%s", getString(R.string.sort_section_dialog_text), showSubjects.get(1)));
                    thirdTextView.setText(String.format("%s%s", getString(R.string.sort_section_dialog_text), showSubjects.get(2)));
                    if(numberOfCourse == 3)
                    {
                        fourthTextView.setVisibility(View.GONE);
                        textInputFourth.setVisibility(View.GONE);
                        fourthInput.setVisibility(View.GONE);

                    }
                    else
                    {
                        fourthTextView.setText(String.format("%s%s", getString(R.string.sort_section_dialog_text), showSubjects.get(3)));
                    }

                    builder.setView(viewInflated);

                    // Set up the buttons
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            String firstInputText = firstInput.getText().toString();

                            String secondInputText = secondInput.getText().toString();

                            String thirdInputText = thirdInput.getText().toString();

                            String fourthInputText = "NULL";
                            if(numberOfCourse == 4)
                            {
                                fourthInputText = fourthInput.getText().toString();
                            }

                            if(!("".equals(firstInputText)) &&
                                    !("".equals(secondInputText)) &&
                                    !("".equals(thirdInputText)) &&
                                    !("".equals(fourthInputText)))
                            {
                                firstSection = Integer.parseInt(firstInputText);
                                secondSection = Integer.parseInt(secondInputText);
                                thirdSection = Integer.parseInt(thirdInputText);
                                if(numberOfCourse == 4)
                                {
                                    fourthSection = Integer.parseInt(fourthInputText);
                                }

                                allSortedCourses.clear();
                                findConflictFreeSortCourse();
                                mAdapter = new SortedCourseAdapter(allSortedCourses, numberOfCourse);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                            else
                            {

                                dialog.cancel();
                                Toast.makeText(ShowSortCourses.this, "Please input the section numbers!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });
        }

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    public void fillShowSubjects()
    {
        for(int i = 0; i < subjects.size(); i++)
        {
            String tempSub = subjects.get(i);
            if(labVarifier(tempSub))
            {
                showSubjects.add(tempSub);
            }
        }
        for(int i = 0; i < subjects.size(); i++)
        {
            String tempSub = subjects.get(i);
            if(!labVarifier(tempSub))
            {
                showSubjects.add(tempSub);
            }
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOGTAG, "onPause");
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOGTAG, "onResume");
    }

    // Method for sorting
    public void setSubjects()
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

                                SortedCourses sortedCourses = new SortedCourses(
                                        withOutLab1.get(loop1),
                                        withOutLab2.get(loop2),
                                        withOutLab3.get(loop3));
                                allSortedCourses.add(sortedCourses);
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

                                SortedCourses sortedCourses = new SortedCourses(
                                        withLab1.get(loop1),
                                        withOutLab1.get(loop2),
                                        withOutLab2.get(loop3));
                                allSortedCourses.add(sortedCourses);
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

                                SortedCourses sortedCourses = new SortedCourses(
                                        withLab1.get(loop1),
                                        withLab2.get(loop2),
                                        withOutLab1.get(loop3));
                                allSortedCourses.add(sortedCourses);
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
                                SortedCourses sortedCourses = new SortedCourses(
                                        withLab1.get(loop1),
                                        withLab2.get(loop2),
                                        withLab3.get(loop3));
                                allSortedCourses.add(sortedCourses);
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
                                    SortedCourses sortedCourses = new SortedCourses(
                                            withOutLab1.get(loop1),
                                            withOutLab2.get(loop2),
                                            withOutLab3.get(loop3),
                                            withOutLab4.get(loop4));
                                    allSortedCourses.add(sortedCourses);
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

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withOutLab1.get(loop2),
                                            withOutLab2.get(loop3),
                                            withOutLab3.get(loop4));
                                    allSortedCourses.add(sortedCourses);
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

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withOutLab1.get(loop3),
                                            withOutLab2.get(loop4));
                                    allSortedCourses.add(sortedCourses);
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

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withLab3.get(loop3),
                                            withOutLab1.get(loop4));
                                    allSortedCourses.add(sortedCourses);

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
                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withLab3.get(loop3),
                                            withLab4.get(loop4));
                                    allSortedCourses.add(sortedCourses);

                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void findConflictFreeSortCourse()
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

                                if( (firstSection == withOutLab1.get(loop1).getSection() && secondSection == withOutLab2.get(loop2).getSection() &&
                                thirdSection == withOutLab3.get(loop3).getSection()) || (firstSection == withOutLab1.get(loop1).getSection() &&
                                        secondSection == withOutLab2.get(loop2).getSection()) || (firstSection == withOutLab1.get(loop1).getSection() &&
                                        thirdSection == withOutLab3.get(loop3).getSection()) || (secondSection == withOutLab2.get(loop2).getSection() &&
                                        thirdSection == withOutLab3.get(loop3).getSection()))
                                {

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withOutLab1.get(loop1),
                                            withOutLab2.get(loop2),
                                            withOutLab3.get(loop3));
                                    allSortedCourses.add(sortedCourses);
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

                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withOutLab1.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab2.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withOutLab1.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab2.get(loop3))
                                    && withOutLab1.get(loop2).findConfliction(withOutLab2.get(loop3)))
                            {

                                if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withOutLab1.get(loop2).getSection() &&
                                        thirdSection == withOutLab2.get(loop3).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        secondSection == withOutLab1.get(loop2).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        thirdSection == withOutLab2.get(loop3).getSection()) || (secondSection == withOutLab1.get(loop2).getSection() &&
                                        thirdSection == withOutLab2.get(loop3).getSection()))
                                {

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withOutLab1.get(loop2),
                                            withOutLab2.get(loop3));
                                    allSortedCourses.add(sortedCourses);

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
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withOutLab1.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withOutLab1.get(loop3))
                                    && withLab2.get(loop2).findConfliction(withOutLab1.get(loop3)))
                            {


                                if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                        thirdSection == withOutLab1.get(loop3).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        secondSection == withLab2.get(loop2).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        thirdSection == withOutLab1.get(loop3).getSection()) || (secondSection == withLab2.get(loop2).getSection() &&
                                        thirdSection == withOutLab1.get(loop3).getSection()))

                                {

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withOutLab1.get(loop3));
                                    allSortedCourses.add(sortedCourses);
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
                for(int loop1 = 0; loop1 < withLab1.size(); loop1++)
                {
                    for(int loop2 = 0; loop2 < withLab2.size(); loop2++)
                    {
                        for(int loop3 = 0; loop3 < withLab3.size(); loop3++)
                        {
                            if(withLab1.get(loop1).findConfliction(withLab2.get(loop2)) && withLab1.get(loop1).findConfliction(withLab3.get(loop3))
                                    && withLab2.get(loop2).findConfliction(withLab3.get(loop3)))
                            {

                                if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                        thirdSection == withLab3.get(loop3).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        secondSection == withLab2.get(loop2).getSection()) || (firstSection == withLab1.get(loop1).getSection() &&
                                        thirdSection == withLab3.get(loop3).getSection()) || (secondSection == withLab2.get(loop2).getSection() &&
                                        thirdSection == withLab3.get(loop3).getSection()))
                                {

                                    SortedCourses sortedCourses = new SortedCourses(
                                            withLab1.get(loop1),
                                            withLab2.get(loop2),
                                            withLab3.get(loop3));
                                    allSortedCourses.add(sortedCourses);
                                }
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
                                    if( (firstSection == withOutLab1.get(loop1).getSection() && secondSection == withOutLab2.get(loop2).getSection() &&
                                            thirdSection == withOutLab3.get(loop3).getSection() && fourthSection == withOutLab4.get(loop4).getSection())
                                            ||
                                            (firstSection == withOutLab1.get(loop1).getSection() && secondSection == withOutLab2.get(loop2).getSection() &&
                                                    thirdSection == withOutLab3.get(loop3).getSection())
                                            ||
                                            (firstSection == withOutLab1.get(loop1).getSection() && secondSection == withOutLab2.get(loop2).getSection() &&
                                                    fourthSection == withOutLab4.get(loop4).getSection())
                                            ||
                                            (firstSection == withOutLab1.get(loop1).getSection() && thirdSection == withOutLab3.get(loop3).getSection()  &&
                                                    fourthSection == withOutLab4.get(loop4).getSection() )
                                            ||
                                            (secondSection == withOutLab2.get(loop2).getSection() && thirdSection == withOutLab3.get(loop3).getSection() &&
                                                    fourthSection == withOutLab4.get(loop4).getSection()))
                                    {


                                        SortedCourses sortedCourses = new SortedCourses(
                                                withOutLab1.get(loop1),
                                                withOutLab2.get(loop2),
                                                withOutLab3.get(loop3),
                                                withOutLab4.get(loop4));
                                        allSortedCourses.add(sortedCourses);
                                    }
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


                                    if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withOutLab1.get(loop2).getSection() &&
                                            thirdSection == withOutLab2.get(loop3).getSection() && fourthSection == withOutLab3.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withOutLab1.get(loop2).getSection() &&
                                                    thirdSection == withOutLab2.get(loop3).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withOutLab1.get(loop2).getSection() &&
                                                    fourthSection == withOutLab3.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && thirdSection == withOutLab2.get(loop3).getSection()  &&
                                                    fourthSection == withOutLab3.get(loop4).getSection() )
                                            ||
                                            (secondSection == withOutLab1.get(loop2).getSection() && thirdSection == withOutLab2.get(loop3).getSection() &&
                                                    fourthSection == withOutLab3.get(loop4).getSection()))
                                    {

                                        SortedCourses sortedCourses = new SortedCourses(
                                                withLab1.get(loop1),
                                                withOutLab1.get(loop2),
                                                withOutLab2.get(loop3),
                                                withOutLab3.get(loop4));
                                        allSortedCourses.add(sortedCourses);
                                    }


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

                                    if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                            thirdSection == withOutLab1.get(loop3).getSection() && fourthSection == withOutLab2.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    thirdSection == withOutLab1.get(loop3).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    fourthSection == withOutLab2.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && thirdSection == withOutLab1.get(loop3).getSection()  &&
                                                    fourthSection == withOutLab2.get(loop4).getSection() )
                                            ||
                                            (secondSection == withLab2.get(loop2).getSection() && thirdSection == withOutLab1.get(loop3).getSection() &&
                                                    fourthSection == withOutLab2.get(loop4).getSection()))
                                    {

                                        SortedCourses sortedCourses = new SortedCourses(
                                                withLab1.get(loop1),
                                                withLab2.get(loop2),
                                                withOutLab1.get(loop3),
                                                withOutLab2.get(loop4));
                                        allSortedCourses.add(sortedCourses);
                                    }
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

                                    if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                            thirdSection == withLab3.get(loop3).getSection() && fourthSection == withOutLab1.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    thirdSection == withLab3.get(loop3).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    fourthSection == withOutLab1.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && thirdSection == withLab3.get(loop3).getSection()  &&
                                                    fourthSection == withOutLab1.get(loop4).getSection() )
                                            ||
                                            (secondSection == withLab2.get(loop2).getSection() && thirdSection == withLab3.get(loop3).getSection() &&
                                                    fourthSection == withOutLab1.get(loop4).getSection()))
                                    {

                                        SortedCourses sortedCourses = new SortedCourses(
                                                withLab1.get(loop1),
                                                withLab2.get(loop2),
                                                withLab3.get(loop3),
                                                withOutLab1.get(loop4));
                                        allSortedCourses.add(sortedCourses);
                                    }




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
                                    if( (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                            thirdSection == withLab3.get(loop3).getSection() && fourthSection == withLab4.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    thirdSection == withLab3.get(loop3).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && secondSection == withLab2.get(loop2).getSection() &&
                                                    fourthSection == withLab4.get(loop4).getSection())
                                            ||
                                            (firstSection == withLab1.get(loop1).getSection() && thirdSection == withLab3.get(loop3).getSection()  &&
                                                    fourthSection == withLab4.get(loop4).getSection() )
                                            ||
                                            (secondSection == withLab2.get(loop2).getSection() && thirdSection == withLab3.get(loop3).getSection() &&
                                                    fourthSection == withLab4.get(loop4).getSection()))
                                    {

                                        SortedCourses sortedCourses = new SortedCourses(
                                                withLab1.get(loop1),
                                                withLab2.get(loop2),
                                                withLab3.get(loop3),
                                                withLab4.get(loop4));
                                        allSortedCourses.add(sortedCourses);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
