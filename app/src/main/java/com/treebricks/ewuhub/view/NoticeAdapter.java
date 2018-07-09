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
import com.treebricks.ewuhub.ui.NoticeWebViewer;
import java.util.ArrayList;
import java.util.Random;





public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>
{

    private ArrayList<NoticeView> mDataset;
    public Random randomNumber = new Random();
    public String[] colors = {"#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    private Context context = null;




    public void add(int position, NoticeView item)
    {
        mDataset.add(position,item);
        notifyItemInserted(position);
    }

    public NoticeAdapter(ArrayList<NoticeView> myDataset, Context context)
    {
        mDataset = myDataset;
        this.context = context;
    }



    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card,parent,false);
        ((CardView)v).setCardBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position)
    {
        final String noticeTitle = mDataset.get(position).getNotice_title();
        final String noticeDate = mDataset.get(position).getNotice_date();

        holder.noticeTitle.setText(noticeTitle);
        holder.noticeDate.setText(noticeDate);

    }
    // // TODO: 4/17/16 Add chrome custom tab on notice webview

    public class NoticeViewHolder extends RecyclerView.ViewHolder
    {
        public TextView noticeTitle;
        public TextView noticeDate;

        public NoticeViewHolder(View itemView)
        {
            super(itemView);
            noticeTitle = (TextView) itemView.findViewById(R.id.notice_title);
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
            });
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}



