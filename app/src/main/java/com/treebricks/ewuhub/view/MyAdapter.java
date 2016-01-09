package com.treebricks.ewuhub.view;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.treebricks.ewuhub.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private ArrayList<Viewer> mDataset;
    private int totalCourse = 0;
    public static int counter = 0;
    public Random randomNumber = new Random();
    public String[] colors = {"#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtHeader;
        public TextView textC1;
        public TextView textC2;
        public TextView textC3;
        public TextView textC4;

        public RelativeLayout relativeLayout;


        public ViewHolder(View v)
        {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.header_line);
            textC1 = (TextView) v.findViewById(R.id.first_line);
            textC2 = (TextView) v.findViewById(R.id.second_line);
            textC3 = (TextView) v.findViewById(R.id.third_line);
            textC4 = (TextView) v.findViewById(R.id.fourth_line);
        }
    }

    public void add(int position, Viewer item)
    {
        mDataset.add(position,item);

        notifyItemInserted(position);
    }

    public void remove(Viewer item)
    {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(ArrayList<Viewer> myDataset, int totalCourse)
    {
        mDataset = myDataset;
        this.totalCourse = totalCourse;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list,parent,false);
        v.setBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position)
    {
        final String name = mDataset.get(position).getvHeader();
        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove(name);
                Snackbar.make(v, "Subjects are : " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        holder.textC1.setText(mDataset.get(position).getvFirstCourse());
        holder.textC2.setText(mDataset.get(position).getvSecondCourse());
        holder.textC3.setText(mDataset.get(position).getvThirdCourse());
        if(totalCourse == 4)
        {
            holder.textC4.setText("4th Course");
        }
        else
        {
            holder.textC4.setVisibility(View.INVISIBLE);
        }
        Log.i("RecyclerView", "Showing RecyclerView");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

