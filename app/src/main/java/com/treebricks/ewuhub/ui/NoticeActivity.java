package com.treebricks.ewuhub.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<NoticeView> recycleView = new ArrayList<NoticeView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jsonString = getIntent().getExtras().getString("JSON_DATA");



        createRecyclerView();


        mRecyclerView = (RecyclerView) findViewById(R.id.notice_recycler_view);



        mRecyclerView.setHasFixedSize(true);


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new NoticeAdapter(recycleView, NoticeActivity.this, jsonString);
        mRecyclerView.setAdapter(mAdapter);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
