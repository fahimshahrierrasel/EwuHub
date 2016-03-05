package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.NoticeAdapter;
import com.treebricks.ewuhub.view.NoticeView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity
{
    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<NoticeView> recycleView = new ArrayList<NoticeView>();
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jsonString = getIntent().getExtras().getString("JSON_DATA");
        System.out.println("Json String : " + jsonString);



        createRecyclerView();


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.notice_recycler_view);



        mRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        RecyclerView.Adapter mAdapter = new NoticeAdapter(recycleView, NoticeActivity.this, jsonString);
        mRecyclerView.setAdapter(mAdapter);



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    public void createRecyclerView()
    {
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("Notice");
            int count = 0;
            String noticeTitle, noticeDate, noticeUrl;
            while(count < jsonArray.length())
            {
                JSONObject jObject = jsonArray.getJSONObject(count);
                noticeTitle = jObject.getString("NoticeTitle");
                noticeDate = jObject.getString("NoticeDate");
                noticeUrl = jObject.getString("NoticeUrl");
                NoticeView viewer  = new NoticeView(noticeTitle,noticeDate,noticeUrl);
                recycleView.add(viewer);

                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
