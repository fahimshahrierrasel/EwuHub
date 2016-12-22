package com.treebricks.ewuhub.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mittsu.markedview.MarkedView;
import com.treebricks.ewuhub.R;

public class FeedMarkDownView extends AppCompatActivity {

    String markData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down_view);

        MarkedView mdView = (MarkedView)findViewById(R.id.md_view);

        if(getIntent().getStringExtra("FEED_DATA") != null)
        {
            markData = getIntent().getStringExtra("FEED_DATA");
        }

        mdView.setMDText(markData);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle("News Feed SortedCourses");
        }
    }
}
