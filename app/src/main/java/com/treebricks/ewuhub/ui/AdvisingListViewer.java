package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.database.AdvisingListSource;
import com.treebricks.ewuhub.database.AllCoursesDataSource;
import com.treebricks.ewuhub.view.AdvisingList;
import com.treebricks.ewuhub.view.AdvisingListAdapter;

import java.util.ArrayList;

public class AdvisingListViewer extends AppCompatActivity {

    RecyclerView advisingRecyclerView;
    AdvisingListSource advisingListSource;
    ArrayList<AdvisingList> allCoursesList;
    ArrayList<String> allCourses;
    AdvisingListAdapter advisingListAdapter;
    AutoCompleteTextView autoCompleteCourse;
    Toolbar toolbar;
    AllCoursesDataSource allCoursesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advising_list);

        toolbar = findViewById(R.id.toolbar);
        advisingRecyclerView = findViewById(R.id.advising_recycler);
        autoCompleteCourse = findViewById(R.id.autocompletecourse);
        setSupportActionBar(toolbar);

        // Initializing Database Data Source Object
        allCoursesDataSource = new AllCoursesDataSource();
        advisingListSource = new AdvisingListSource();

        // Get the data from Database
        allCoursesList = advisingListSource.findAll(AdvisingListViewer.this);

        // Initializing RecyclerView Adapter and set adapter to the recycler and fastscroller.
        advisingListAdapter = new AdvisingListAdapter(allCoursesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        advisingRecyclerView.setLayoutManager(linearLayoutManager);
        advisingRecyclerView.setAdapter(advisingListAdapter);

        // Get the data from database for suggesting
        allCourses = allCoursesDataSource.findAll(this);

        // Converting ArrayList to String Array
        String[] suggestion = allCourses.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestion);
        autoCompleteCourse.setAdapter(adapter);


        autoCompleteCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int i = 0; i < allCoursesList.size(); i++)
                {
                    if(allCoursesList.get(i).getCourseName().equals(autoCompleteCourse.getText().toString()))
                    {
                        if (i+4 <= allCoursesList.size())
                        {
                            advisingRecyclerView.scrollToPosition(i);
                        }
                        else
                        {
                            advisingRecyclerView.scrollToPosition(i);
                        }
                        break;
                    }
                }
                hideSoftKeyboard();
            }
        });

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
