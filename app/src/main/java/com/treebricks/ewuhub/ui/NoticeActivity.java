package com.treebricks.ewuhub.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.view.NoticeAdapter;
import com.treebricks.ewuhub.view.NoticeView;

import java.util.ArrayList;
import java.util.Collections;

public class NoticeActivity extends AppCompatActivity
{
    ArrayList<NoticeView> recycleView = new ArrayList<NoticeView>();
    ActionBar actionBar;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting the notice from the Database.\nPlease be patient..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("notices");

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.notice_recycler_view);


        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        final RecyclerView.Adapter mAdapter = new NoticeAdapter(recycleView, NoticeActivity.this);
        /*FirebaseRecyclerAdapter<NoticeView, NoticeViewHolder> mAdapter = new FirebaseRecyclerAdapter<NoticeView, NoticeViewHolder>(
                NoticeView.class, R.layout.notice_card, NoticeViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(NoticeViewHolder viewHolder, NoticeView model, int position) {
                viewHolder.setnoticeTitle(model.getNotice_title());
                viewHolder.setnoticeDate(model.getNotice_date());
            }
        };*/

        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mAdapter);

        }



        Query query = mDatabase.orderByKey();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> notices = dataSnapshot.getChildren();
                recycleView.clear();
                for (DataSnapshot data : notices) {
                    Log.d("ON getting notice", "onDataChange: " + data.getValue(NoticeView.class));
                    NoticeView noticeView = data.getValue(NoticeView.class);
                    recycleView.add(noticeView);
                    mAdapter.notifyDataSetChanged();

                }
                Collections.reverse(recycleView);
                if (mRecyclerView != null) {
                    mRecyclerView.setAdapter(mAdapter);

                }
                progressDialog.hide();
                progressDialog.cancel();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /*public static class NoticeViewHolder extends RecyclerView.ViewHolder
    {
        *//*public TextView noticeTitle;
        public TextView noticeDate;*//*
        View mView;

        public NoticeViewHolder(View itemView)
        {
            super(itemView);
            *//*noticeTitle = (TextView) itemView.findViewById(R.id.notice_title);
            noticeDate = (TextView) itemView.findViewById(R.id.notice_date);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v)
                {
                    int position = getAdapterPosition();
                    final String noticeUrl = mDataset.get(position).getNotice_url();
                    Intent webView = new Intent(context, NoticeWebViewer.class);
                    webView.putExtra("URL", noticeUrl);
                    context.startActivity(webView);
                }
            });*//*
            mView = itemView;
        }
        public void setnoticeTitle(String title)
        {
            TextView noticeTitle = (TextView) mView.findViewById(R.id.notice_title);
            noticeTitle.setText(title);
        }

        public void setnoticeDate(String date)
        {
            TextView noticeDate = (TextView) mView.findViewById(R.id.notice_date);
            noticeDate.setText(date);
        }
    }*/
    /*@Override
    public int getItemCount() {
        return mDataset.size();
    }*/
}
