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
import com.treebricks.ewuhub.ui.Details;

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
    public static final String FIRSTCOURSE = "FIRSTCOURSE";
    public static final String SECONDCOURSE = "SECONDCOURSE";
    public static final String THIRDCOURSE = "THIRDCOURSE";
    public static final String FOURTHCOURSE = "FOURTHCOURSE";
    public static final String SHOWSORTCOURSEBUNDLE = "SHOWSORTCOURSEBUNDLE";


    private ArrayList<Viewer> mDataset;
    private int totalCourse = 0;
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

        //public RelativeLayout relativeLayout;


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
        final Bundle firstBundle = new Bundle();
        final Bundle secondBundle = new Bundle();
        final Bundle thirdBundle = new Bundle();
        final Bundle fourthBundle = new Bundle();


        final String name = mDataset.get(position).getvHeader().toString();
        holder.txtHeader.setText(name);
        final Object data1 = mDataset.get(position).getvFirstCourse();
        holder.textC1.setText(data1.toString());
        final Object data2 = mDataset.get(position).getvSecondCourse();
        holder.textC2.setText(data2.toString());
        final Object data3 = mDataset.get(position).getvThirdCourse();
        holder.textC3.setText(data3.toString());


        if (CourseL.class.isInstance(data1)) {
            CourseL courseL = CourseL.class.cast(data1);
            firstBundle.putBoolean(HASLAB, true);
            firstBundle.putString(COURSENAME, courseL.courseName());
            firstBundle.putInt(SECTION, courseL.getSection());
            firstBundle.putString(WEEKDAY, courseL.getWeekDay());
            firstBundle.putInt(TIMEFROM, courseL.getTimeFrom());
            firstBundle.putInt(TIMETO, courseL.getTimeTo());
            firstBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
            firstBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
            firstBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

        }
        else
        {
            Course course = Course.class.cast(data1);
            firstBundle.putBoolean(HASLAB, false);
            firstBundle.putString(COURSENAME, course.courseName());
            firstBundle.putInt(SECTION, course.getSection());
            firstBundle.putString(WEEKDAY, course.getWeekDay());
            firstBundle.putInt(TIMEFROM, course.getTimeFrom());
            firstBundle.putInt(TIMETO, course.getTimeTo());

        }

        if (CourseL.class.isInstance(data2)) {
            CourseL courseL = CourseL.class.cast(data2);
            secondBundle.putBoolean(HASLAB, true);
            secondBundle.putString(COURSENAME, courseL.courseName());
            secondBundle.putInt(SECTION, courseL.getSection());
            secondBundle.putString(WEEKDAY, courseL.getWeekDay());
            secondBundle.putInt(TIMEFROM, courseL.getTimeFrom());
            secondBundle.putInt(TIMETO, courseL.getTimeTo());
            secondBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
            secondBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
            secondBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

        }
        else
        {
            Course course = Course.class.cast(data2);
            secondBundle.putBoolean(HASLAB, false);
            secondBundle.putString(COURSENAME, course.courseName());
            secondBundle.putInt(SECTION, course.getSection());
            secondBundle.putString(WEEKDAY, course.getWeekDay());
            secondBundle.putInt(TIMEFROM, course.getTimeFrom());
            secondBundle.putInt(TIMETO, course.getTimeTo());

        }

        if (CourseL.class.isInstance(data3)) {
            CourseL courseL = CourseL.class.cast(data3);
            thirdBundle.putBoolean(HASLAB, true);
            thirdBundle.putString(COURSENAME, courseL.courseName());
            thirdBundle.putInt(SECTION, courseL.getSection());
            thirdBundle.putString(WEEKDAY, courseL.getWeekDay());
            thirdBundle.putInt(TIMEFROM, courseL.getTimeFrom());
            thirdBundle.putInt(TIMETO, courseL.getTimeTo());
            thirdBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
            thirdBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
            thirdBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

        }
        else
        {
            Course course = Course.class.cast(data3);
            thirdBundle.putBoolean(HASLAB, false);
            thirdBundle.putString(COURSENAME, course.courseName());
            thirdBundle.putInt(SECTION, course.getSection());
            thirdBundle.putString(WEEKDAY, course.getWeekDay());
            thirdBundle.putInt(TIMEFROM, course.getTimeFrom());
            thirdBundle.putInt(TIMETO, course.getTimeTo());

        }



        if(totalCourse == 4)
        {
            final Object data4 = mDataset.get(position).getvFourthCourse();
            holder.textC4.setText(data4.toString());


            if (CourseL.class.isInstance(data4)) {
                CourseL courseL = CourseL.class.cast(data4);
                fourthBundle.putBoolean(HASLAB, true);
                fourthBundle.putString(COURSENAME, courseL.courseName());
                fourthBundle.putInt(SECTION, courseL.getSection());
                fourthBundle.putString(WEEKDAY, courseL.getWeekDay());
                fourthBundle.putInt(TIMEFROM, courseL.getTimeFrom());
                fourthBundle.putInt(TIMETO, courseL.getTimeTo());
                fourthBundle.putString(LABWEEKDAY, courseL.getLabWeekDay());
                fourthBundle.putInt(LABTIMEFROM, courseL.getLabTimeFrom());
                fourthBundle.putInt(LABTIMETO, courseL.getLabTimeTo());

            }
            else
            {
                Course course = Course.class.cast(data4);
                fourthBundle.putBoolean(HASLAB, false);
                fourthBundle.putString(COURSENAME, course.courseName());
                fourthBundle.putInt(SECTION, course.getSection());
                fourthBundle.putString(WEEKDAY, course.getWeekDay());
                fourthBundle.putInt(TIMEFROM, course.getTimeFrom());
                fourthBundle.putInt(TIMETO, course.getTimeTo());

            }
        }
        else
        {
            holder.textC4.setVisibility(View.INVISIBLE);
        }

        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Subjects are : " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        holder.textC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtra(FIRSTCOURSE, firstBundle);
                courseDetail.putExtra(SECONDCOURSE, secondBundle);
                courseDetail.putExtra(THIRDCOURSE, thirdBundle);
                if(totalCourse == 4)
                    courseDetail.putExtra(FOURTHCOURSE, fourthBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });

        holder.textC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtra(FIRSTCOURSE, firstBundle);
                courseDetail.putExtra(SECONDCOURSE, secondBundle);
                courseDetail.putExtra(THIRDCOURSE, thirdBundle);
                if(totalCourse == 4)
                    courseDetail.putExtra(FOURTHCOURSE, fourthBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });

        holder.textC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtra(FIRSTCOURSE, firstBundle);
                courseDetail.putExtra(SECONDCOURSE, secondBundle);
                courseDetail.putExtra(THIRDCOURSE, thirdBundle);
                if(totalCourse == 4)
                    courseDetail.putExtra(FOURTHCOURSE, fourthBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);
            }
        });

        holder.textC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent courseDetail = new Intent(context, CourseDetails.class);
                courseDetail.putExtra(FIRSTCOURSE, firstBundle);
                courseDetail.putExtra(SECONDCOURSE, secondBundle);
                courseDetail.putExtra(THIRDCOURSE, thirdBundle);
                courseDetail.putExtra(FOURTHCOURSE, fourthBundle);
                if(showSortCourseBundle != null)
                {
                    courseDetail.putExtra(SHOWSORTCOURSEBUNDLE, showSortCourseBundle);
                }
                context.startActivity(courseDetail);

            }
        });

        Log.i("RecyclerView", "Showing RecyclerView");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}

