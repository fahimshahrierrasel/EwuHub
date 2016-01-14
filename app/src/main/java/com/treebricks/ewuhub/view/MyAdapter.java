package com.treebricks.ewuhub.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.ui.CourseDetails;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import model.Course;
import model.CourseL;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    public static final String HASLAB = "HASLAB";
    public static final String COURSENAME = "COURSENAME";
    public static final String SECTION = "SECTION";
    public static final String WEEKDAY = "WEEKDAY";
    public static final String TIMEFROM = "TIMEFROM";
    public static final String TIMETO = "TIMETO";
    public static final String LABWEEKDAY = "LABWEEKDAY";
    public static final String LABTIMEFROM = "LABTIMEFROM";
    public static final String LABTIMETO = "LABTIMETO";
    public static final String LOGTAG = "EwuHub";
    public static final String SHOWSORTCOURSEBUNDLE = "SHOWSORTCOURSEBUNDLE";


    private ArrayList<Viewer> mDataset;
    private int totalCourse = 0;
    public static int counter = 0;
    public Random randomNumber = new Random();
    public String[] colors = {"#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    private Context context = null;
    Bundle showSortCourseBundle = null;


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

    public MyAdapter(ArrayList<Viewer> myDataset, int totalCourse, Context context, Bundle bundle)
    {
        mDataset = myDataset;
        this.totalCourse = totalCourse;
        this.context = context;
        showSortCourseBundle = bundle;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list,parent,false);
        ((CardView)v).setCardBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position)
    {
        final String name = mDataset.get(position).getvHeader().toString();


        holder.txtHeader.setText(name);
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Subjects are : " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final Object data1 = mDataset.get(position).getvFirstCourse();
        holder.textC1.setText(data1.toString());
        holder.textC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle sentBundle = new Bundle();

                if (CourseL.class.isInstance(data1)) {
                    CourseL courseL = CourseL.class.cast(data1);
                    sentBundle.putBoolean(HASLAB, true);
                    sentBundle.putString(COURSENAME, courseL.courseName());
                    sentBundle.putInt(SECTION, courseL.getSection());
                    sentBundle.putString(WEEKDAY, courseL.getWeekDay());
                    sentBundle.putInt(TIMEFROM, courseL.getTimeFrom());
                    sentBundle.putInt(TIMETO, courseL.getTimeTo());
                    sentBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
                    sentBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
                    sentBundle.putInt(LABTIMETO, courseL.getLabTimeTo());
                    
                }
                else
                {
                    Course course = Course.class.cast(data1);
                    sentBundle.putBoolean(HASLAB, false);
                    sentBundle.putString(COURSENAME, course.courseName());
                    sentBundle.putInt(SECTION, course.getSection());
                    sentBundle.putString(WEEKDAY, course.getWeekDay());
                    sentBundle.putInt(TIMEFROM, course.getTimeFrom());
                    sentBundle.putInt(TIMETO, course.getTimeTo());

                }

                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtras(sentBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });


        final Object data2 = mDataset.get(position).getvSecondCourse();
        holder.textC2.setText(data2.toString());
        holder.textC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle sentBundle = new Bundle();

                if (CourseL.class.isInstance(data2)) {
                    CourseL courseL = CourseL.class.cast(data2);
                    sentBundle.putBoolean(HASLAB, true);
                    sentBundle.putString(COURSENAME, courseL.courseName());
                    sentBundle.putInt(SECTION, courseL.getSection());
                    sentBundle.putString(WEEKDAY, courseL.getWeekDay());
                    sentBundle.putInt(TIMEFROM, courseL.getTimeFrom());
                    sentBundle.putInt(TIMETO, courseL.getTimeTo());
                    sentBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
                    sentBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
                    sentBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

                }
                else
                {
                    Course course = Course.class.cast(data2);
                    sentBundle.putBoolean(HASLAB, false);
                    sentBundle.putString(COURSENAME, course.courseName());
                    sentBundle.putInt(SECTION, course.getSection());
                    sentBundle.putString(WEEKDAY, course.getWeekDay());
                    sentBundle.putInt(TIMEFROM, course.getTimeFrom());
                    sentBundle.putInt(TIMETO, course.getTimeTo());

                }

                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtras(sentBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });


        final Object data3 = mDataset.get(position).getvThirdCourse();
        holder.textC3.setText(data3.toString());
        holder.textC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle sentBundle = new Bundle();

                if (CourseL.class.isInstance(data3)) {
                    CourseL courseL = CourseL.class.cast(data3);
                    sentBundle.putBoolean(HASLAB, true);
                    sentBundle.putString(COURSENAME, courseL.courseName());
                    sentBundle.putInt(SECTION, courseL.getSection());
                    sentBundle.putString(WEEKDAY, courseL.getWeekDay());
                    sentBundle.putInt(TIMEFROM, courseL.getTimeFrom());
                    sentBundle.putInt(TIMETO, courseL.getTimeTo());
                    sentBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
                    sentBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
                    sentBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

                }
                else
                {
                    Course course = Course.class.cast(data3);
                    sentBundle.putBoolean(HASLAB, false);
                    sentBundle.putString(COURSENAME, course.courseName());
                    sentBundle.putInt(SECTION, course.getSection());
                    sentBundle.putString(WEEKDAY, course.getWeekDay());
                    sentBundle.putInt(TIMEFROM, course.getTimeFrom());
                    sentBundle.putInt(TIMETO, course.getTimeTo());

                }

                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtras(sentBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });


        if(totalCourse == 4)
        {
            final Object data4 = mDataset.get(position).getvFourthCourse();
            holder.textC4.setText(data4.toString());
            holder.textC4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Bundle sentBundle = new Bundle();

                    if (CourseL.class.isInstance(data4)) {
                        CourseL courseL = CourseL.class.cast(data4);
                        sentBundle.putBoolean(HASLAB, true);
                        sentBundle.putString(COURSENAME, courseL.courseName());
                        sentBundle.putInt(SECTION, courseL.getSection());
                        sentBundle.putString(WEEKDAY, courseL.getWeekDay());
                        sentBundle.putInt(TIMEFROM, courseL.getTimeFrom());
                        sentBundle.putInt(TIMETO, courseL.getTimeTo());
                        sentBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
                        sentBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
                        sentBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

                    }
                    else
                    {
                        Course course = Course.class.cast(data4);
                        sentBundle.putBoolean(HASLAB, false);
                        sentBundle.putString(COURSENAME, course.courseName());
                        sentBundle.putInt(SECTION, course.getSection());
                        sentBundle.putString(WEEKDAY, course.getWeekDay());
                        sentBundle.putInt(TIMEFROM, course.getTimeFrom());
                        sentBundle.putInt(TIMETO, course.getTimeTo());

                    }

                    Intent courseDetail = new Intent(context, CourseDetails.class);
                    courseDetail.putExtras(sentBundle);
                    if(showSortCourseBundle != null)
                    {
                        courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                    }
                    context.startActivity(courseDetail);

                }
            });
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

