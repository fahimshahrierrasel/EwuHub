package com.treebricks.ewuhub.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.ui.NoticeWebViewer;

import java.util.ArrayList;
import java.util.Random;





public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder>
{

    String jsonData;

    private ArrayList<NoticeView> mDataset;
    public static int counter = 0;
    public Random randomNumber = new Random();
    public String[] colors = {"#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    private Context context = null;


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView noticeTitle;
        public TextView noticeDate;


        public RelativeLayout relativeLayout;


        public ViewHolder(View v)
        {
            super(v);
            noticeTitle = (TextView) v.findViewById(R.id.notice_title);
            noticeDate = (TextView) v.findViewById(R.id.notice_date);

        }
    }

    public void add(int position, NoticeView item)
    {
        mDataset.add(position,item);

        notifyItemInserted(position);
    }

    public void remove(NoticeView item)
    {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public NoticeAdapter(ArrayList<NoticeView> myDataset, Context context, String jsonData)
    {
        mDataset = myDataset;
        this.context = context;
        this.jsonData = jsonData;
    }

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card,parent,false);
        ((CardView)v).setCardBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.ViewHolder holder, int position)
    {
        final String noticeUrl = mDataset.get(position).getNoticeUrl();
        final String noticeTitle = mDataset.get(position).getNoticeTitle();
        final String noticeDate = mDataset.get(position).getNoticeDate();



        holder.noticeTitle.setText(noticeTitle);
        holder.noticeTitle.setSelected(true);
        holder.noticeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webView = new Intent(context, NoticeWebViewer.class);
                webView.putExtra("URL", noticeUrl);
                webView.putExtra("JSON_DATA",jsonData);
                context.startActivity(webView);
            }
        });



        holder.noticeDate.setText(noticeDate);
        holder.noticeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webView = new Intent(context, NoticeWebViewer.class);
                webView.putExtra("URL", noticeUrl);
                webView.putExtra("JSON_DATA",jsonData);
                context.startActivity(webView);
            }
        });



        Log.i("RecyclerView", "Showing RecyclerView");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}



