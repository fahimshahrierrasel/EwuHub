package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.AcademicCalendarEvent;
import com.treebricks.ewuhub.view.AcademicCalendarAdapter;
import com.treebricks.ewuhub.view.AcademicCalendarModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AcademicCalendar extends AppCompatActivity {
    ActionBar actionBar;
    private int mScrollOffset = 4;
    AcademicCalendarAdapter academicCalendarAdapter;
    private View mShadowView;
    FloatingActionMenu fabMenu;
    String actionBarTitle = "Academic Calendar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();




        fabMenu = (FloatingActionMenu) findViewById(R.id.calendar_fab_menu);
        mShadowView = findViewById(R.id.shadow_view);

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


        final FloatingActionButton undergraduateFab = (FloatingActionButton) findViewById(R.id.undergraduate);
        if (undergraduateFab != null) {
            undergraduateFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (fabMenu != null) {
                        fabMenu.close(true);
                    }
                    if(actionBar != null)
                    {
                        actionBar.setTitle("Undergraduate Fall-2016");
                    }

                }
            });
        }

        final FloatingActionButton pharmacyUndergraduateFab = (FloatingActionButton) findViewById(R.id.pharmacyundergraduate);
        if (pharmacyUndergraduateFab != null) {
            pharmacyUndergraduateFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (fabMenu != null) {
                        fabMenu.close(true);
                    }
                    if(actionBar != null)
                    {
                        actionBar.setTitle("Pharmacy Undergrad Fall-2016");
                    }
                }
            });
        }

        FloatingActionButton GraduateFab = (FloatingActionButton) findViewById(R.id.graduate);
        if (GraduateFab != null) {
            GraduateFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (fabMenu != null) {
                        fabMenu.close(true);
                    }
                    if(actionBar != null)
                    {
                        actionBar.setTitle("Graduate Fall-2016");
                    }

                }
            });
        }

        final com.github.clans.fab.FloatingActionButton pharmacyGraduateFab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.pharmacygraduate);
        if (pharmacyGraduateFab != null) {
            pharmacyGraduateFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if (fabMenu != null) {
                        fabMenu.close(true);
                    }
                    if(actionBar != null)
                    {
                        actionBar.setTitle("Pharmacy Graduate Fall-2016");
                    }

                }
            });
        }

        String calendarJsonString = loadJSONFromAsset();
        AcademicCalendarModel academicCalendarModel = new AcademicCalendarModel();


        if(calendarJsonString != null || !"".equals(calendarJsonString))
        {
            Gson gson = new Gson();
            academicCalendarModel = gson.fromJson(calendarJsonString, AcademicCalendarModel.class);
            actionBarTitle = academicCalendarModel.getLebel() + " " + academicCalendarModel.getSemester();
        }
        if(actionBar != null)
        {
            actionBar.setTitle(actionBarTitle);
        }


        RecyclerView expanRecycler = (RecyclerView) findViewById(R.id.calendat_recyclerView);

        academicCalendarAdapter = new AcademicCalendarAdapter(academicCalendarModel.getAcademicCalendarEvents(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        expanRecycler.setLayoutManager(linearLayoutManager);


        expanRecycler.setAdapter(academicCalendarAdapter);


        expanRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    public String loadJSONFromAsset() {
        String json = null;
        try {
            File file = new File(getBaseContext().getApplicationInfo().dataDir+"/json/undergraduate.json");
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
