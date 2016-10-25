package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.futuremind.recyclerviewfastscroll.FastScroller;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.AdvisingListSource;
import com.treebricks.ewuhub.database.AllCoursesDataSource;
import com.treebricks.ewuhub.view.AdvisingList;
import com.treebricks.ewuhub.view.AdvisingListAdapter;

import java.util.ArrayList;

public class ActivityAdvisingList extends AppCompatActivity {

    RecyclerView advisingRecyclerView;
    AdvisingListSource advisingListSource;
    ArrayList<AdvisingList> allcoursesList;
    ArrayList<String> allcourses;
    AdvisingListAdapter advisingListAdapter;
    FastScroller fastScroller;
    MaterialSearchView searchView;
    Toolbar toolbar;
    AllCoursesDataSource allCoursesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advising_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        advisingRecyclerView = (RecyclerView) findViewById(R.id.advising_recycler);
        fastScroller = (FastScroller) findViewById(R.id.fastscroll);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        setSupportActionBar(toolbar);

        // Intializing Database Data Source Object
        allCoursesDataSource = new AllCoursesDataSource();
        advisingListSource = new AdvisingListSource();

        // Get the data from Database
        allcoursesList = advisingListSource.findAll(ActivityAdvisingList.this);

        // Initializing RecyclerView Adapter and set adapter to the recycler and fastscroller.
        advisingListAdapter = new AdvisingListAdapter(allcoursesList, ActivityAdvisingList.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        advisingRecyclerView.setLayoutManager(linearLayoutManager);
        advisingRecyclerView.setAdapter(advisingListAdapter);
        fastScroller.setRecyclerView(advisingRecyclerView);

        // Get the data from database for suggestin
        allcourses = allCoursesDataSource.findAll(this);

        // Converting ArrayList to String Array
        String[] suggestion = allcourses.toArray(new String[0]);

        // Search View Implementation
        searchView.setSuggestions(suggestion);
        searchView.setVoiceSearch(false);
        searchView.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.md_yellow_200, null));
        searchView.setHint("Search Courses...");
        searchView.setEllipsize(true);
        searchView.setSuggestionBackground(ResourcesCompat.getDrawable(getResources(), R.color.md_green_200, null));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                for(int i = 0; i < allcoursesList.size(); i++)
                {
                    if(allcoursesList.get(i).getCourseName().matches(query))
                    {
                        advisingRecyclerView.scrollToPosition(i);
                        searchView.closeSearch();
                        break;
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("Advising List");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.advising_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
