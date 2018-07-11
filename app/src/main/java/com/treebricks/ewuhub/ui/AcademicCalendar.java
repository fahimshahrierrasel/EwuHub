package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.AcademicCalendarAdapter;
import com.treebricks.ewuhub.model.AcademicCalendarModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AcademicCalendar extends AppCompatActivity {
    // ActionBar
    String actionBarTitle = "Academic Calendar";
    ActionBar actionBar;

    // ShadowView
    private View mShadowView;

    // Gson
    Gson gson;

    // RecyclerView
    RecyclerView acdemicCalendarRecycler;
    private int mScrollOffset = 4;
    AcademicCalendarAdapter academicCalendarAdapter;

    // Floating Action Buttons
    FloatingActionMenu fabMenu;
    FloatingActionButton pharmacyGraduateFab;
    FloatingActionButton undergraduateFab;
    FloatingActionButton graduateFab;
    FloatingActionButton pharmacyUndergraduateFab;

    // Json Strings
    String undergradJsonString = null;
    String pharmaUndergradJsonString = null;
    String graduateJsonString = null;
    String pharmagraduateJsonString = null;

    // Object for Different Calendar
    AcademicCalendarModel undergradCalendar;
    AcademicCalendarModel pharmaUndergradCalendar;
    AcademicCalendarModel graduateCalendar;
    AcademicCalendarModel pharmaGraduateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);

        // Toolbar Setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        // Binding view
        acdemicCalendarRecycler = findViewById(R.id.calendat_recyclerView);
        mShadowView = findViewById(R.id.shadow_view);
        fabMenu = findViewById(R.id.calendar_fab_menu);
        pharmacyGraduateFab = findViewById(R.id.pharmacygraduate);
        undergraduateFab = findViewById(R.id.undergraduate);
        graduateFab = findViewById(R.id.graduate);
        pharmacyUndergraduateFab = findViewById(R.id.pharmacyundergraduate);

        // Gson initialization
        gson = new Gson();

        //Layout Manager for RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        acdemicCalendarRecycler.setLayoutManager(linearLayoutManager);

        // Reading Json String from JSON File
        undergradJsonString = readJSONStringFromFile("undergraduate.json");

        // Initialzing Calendar Object
        undergradCalendar = new AcademicCalendarModel();
        pharmaUndergradCalendar = new AcademicCalendarModel();
        graduateCalendar = new AcademicCalendarModel();
        pharmaGraduateCalendar = new AcademicCalendarModel();

        // Gson converting Json String to Object and setting Actionbar title
        if(undergradJsonString != null && !"".equals(undergradJsonString))
        {
            undergradCalendar = gson.fromJson(undergradJsonString, AcademicCalendarModel.class);
            actionBarTitle = undergradCalendar.getLebel() + " " + undergradCalendar.getSemester();
        }
        if(actionBar != null)
        {
            actionBar.setTitle(actionBarTitle);
        }

        // Initializing RecyclerAdapter and set the adapter for Recycler
        academicCalendarAdapter = new AcademicCalendarAdapter(undergradCalendar.getAcademicCalendarEvents());
        acdemicCalendarRecycler.setAdapter(academicCalendarAdapter);



        // Shadow View Click Handler (Fab Will Close if user click outside of the fab)
        mShadowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMenu.close(true);
            }
        });
        mShadowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                fabMenu.close(true);
                return false;
            }
        });

        // Shadow will apear and disappear with toggling of fabmenu
        fabMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if(fabMenu.isOpened())
                {
                    mShadowView.setVisibility(View.VISIBLE);
                }
                else {
                    mShadowView.setVisibility(View.GONE);
                }
            }
        });


        // All Fabs click handler
        undergraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (fabMenu != null) {
                    fabMenu.close(true);
                }
                actionBarTitle = undergradCalendar.getLebel() + " " + undergradCalendar.getSemester();
                if(actionBar != null)
                {
                    actionBar.setTitle(actionBarTitle);
                }
                academicCalendarAdapter = new AcademicCalendarAdapter(undergradCalendar.getAcademicCalendarEvents());
                acdemicCalendarRecycler.setAdapter(academicCalendarAdapter);

            }
        });


        pharmacyUndergraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (fabMenu != null) {
                    fabMenu.close(true);
                }

                if(pharmaUndergradJsonString == null || "".equals(pharmaUndergradJsonString))
                {
                    pharmaUndergradJsonString = readJSONStringFromFile("pharmacyundergraduate.json");
                    pharmaUndergradCalendar = gson.fromJson(pharmaUndergradJsonString, AcademicCalendarModel.class);
                }

                if(actionBar != null)
                {
                    actionBarTitle = pharmaUndergradCalendar.getLebel() + " " + pharmaUndergradCalendar.getSemester();
                    actionBar.setTitle(actionBarTitle);
                }

                academicCalendarAdapter = new AcademicCalendarAdapter(pharmaUndergradCalendar.getAcademicCalendarEvents());
                acdemicCalendarRecycler.setAdapter(academicCalendarAdapter);

            }
        });



        graduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (fabMenu != null) {
                    fabMenu.close(true);
                }
                if(graduateJsonString == null || "".equals(graduateJsonString))
                {
                    graduateJsonString = readJSONStringFromFile("graduate.json");
                    graduateCalendar = gson.fromJson(graduateJsonString, AcademicCalendarModel.class);
                }
                if(actionBar != null)
                {
                    actionBarTitle = graduateCalendar.getLebel() + " " + graduateCalendar.getSemester();
                    actionBar.setTitle(actionBarTitle);
                }

                academicCalendarAdapter = new AcademicCalendarAdapter(graduateCalendar.getAcademicCalendarEvents());
                acdemicCalendarRecycler.setAdapter(academicCalendarAdapter);

            }
        });


        pharmacyGraduateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (fabMenu != null) {
                    fabMenu.close(true);
                }
                if(pharmagraduateJsonString == null || "".equals(pharmagraduateJsonString))
                {
                    pharmagraduateJsonString = readJSONStringFromFile("pharmacygraduate.json");
                    pharmaGraduateCalendar = gson.fromJson(pharmagraduateJsonString, AcademicCalendarModel.class);
                }
                if(actionBar != null)
                {
                    actionBarTitle = pharmaGraduateCalendar.getLebel() + " " + pharmaGraduateCalendar.getSemester();
                    actionBar.setTitle(actionBarTitle);
                }

                academicCalendarAdapter = new AcademicCalendarAdapter(pharmaGraduateCalendar.getAcademicCalendarEvents());
                acdemicCalendarRecycler.setAdapter(academicCalendarAdapter);

            }
        });

        // Hide Fab when scrolling
        acdemicCalendarRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > mScrollOffset) {
                    if (dy > 0) {
                        fabMenu.hideMenu(true);

                    } else {
                        fabMenu.showMenu(true);
                    }
                }
            }
        });



        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    // Function for read JSON String from file.
    public String readJSONStringFromFile(String fileName) {
        String json = null;
        try {
            File file = new File(getBaseContext().getApplicationInfo().dataDir+"/json/"+fileName);
            if(file.exists())
            {
                InputStream is = new FileInputStream(file);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



}
