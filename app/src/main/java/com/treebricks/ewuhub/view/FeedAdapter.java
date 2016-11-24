package com.treebricks.ewuhub.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.ui.FeedMarkDownView;

import java.util.ArrayList;
import java.util.Random;





public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>
{

    private ArrayList<NewsFeedModel> mDataset;
    public Random randomNumber = new Random();
    public String[] colors = {"#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    private Context context = null;




    public void add(int position, NewsFeedModel item)
    {
        mDataset.add(position,item);
        notifyItemInserted(position);
    }

    public FeedAdapter(ArrayList<NewsFeedModel> myDataset, Context context)
    {
        mDataset = myDataset;
        this.context = context;
    }



    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card,parent,false);
        ((CardView)v).setCardBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        return new FeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position)
    {
        final String noticeTitle = mDataset.get(position).getFeed_title();
        final String noticeDate = mDataset.get(position).getFeed_date();

        holder.feedTitle.setText(noticeTitle);
        holder.feedDate.setText(noticeDate);

    }
    // // TODO: 4/17/16 Add chrome custom tab on notice webview

    public class FeedViewHolder extends RecyclerView.ViewHolder
    {
        public TextView feedTitle;
        public TextView feedDate;

        public FeedViewHolder(View itemView)
        {
            super(itemView);
            feedTitle = (TextView) itemView.findViewById(R.id.feed_title);
            feedDate = (TextView) itemView.findViewById(R.id.feed_date);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v)
                {
                    int position = getAdapterPosition();
                    final String markedData = mDataset.get(position).getFeed_data();
                    Intent markView = new Intent(context, FeedMarkDownView.class);

                    markView.putExtra("FEED_DATA", markedData);
                    context.startActivity(markView);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}