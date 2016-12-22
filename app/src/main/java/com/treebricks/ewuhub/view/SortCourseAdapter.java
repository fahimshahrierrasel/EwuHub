package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.aakira.expandablelayout.Utils;
import com.treebricks.ewuhub.R;

import java.util.ArrayList;
import java.util.Random;

import model.Course;
import model.CourseL;

public class SortCourseAdapter extends RecyclerView.Adapter<SortCourseAdapter.ShortCourseViewHolder>
{

    private ArrayList<SortedCourses> allSortedCourses;
    private int totalCourse = 0;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    public Random randomNumber = new Random();
    public String[] colors = {
            "#E57373", "#F48FB1", "#CE93D8", "#B39DDB", "#9FA8DA", "#42A5F5",
            "#00ACC1", "#00897B", "#4CAF50", "#AFB42B", "#FF7043", "#90A4AE"};
    private Context context = null;



    public SortCourseAdapter(ArrayList<SortedCourses> allSortedCourses, int totalCourse, Context context)
    {
        this.allSortedCourses = allSortedCourses;
        this.totalCourse = totalCourse;
        this.context = context;
        for (int i = 0; i < this.allSortedCourses.size(); i++) {
            expandState.append(i, false);
        }
    }

    public void add(int position, SortedCourses item)
    {
        allSortedCourses.add(position,item);
        notifyItemInserted(position);
    }

    public void remove(SortedCourses item)
    {
        int position = allSortedCourses.indexOf(item);
        allSortedCourses.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ShortCourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showsortcourses,parent,false);

        return new ShortCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShortCourseViewHolder holder, int position)
    {

        final int currentPosition = position;

        String cardHeader = null;

        final Object data1 = allSortedCourses.get(currentPosition).getvFirstCourse();
        final Object data2 = allSortedCourses.get(currentPosition).getvSecondCourse();
        final Object data3 = allSortedCourses.get(currentPosition).getvThirdCourse();

        if (CourseL.class.isInstance(data1)) {

            CourseL courseL = CourseL.class.cast(data1);
            holder.course_name_1.setText(courseL.courseName());
            holder.course_section_1.setText("Section: " + String.valueOf(courseL.getSection()));
            holder.course_weekday_1.setText("Weekday: " + courseL.getWeekDay());
            holder.course_timefrom_1.setText(String.valueOf(courseL.getTimeFrom()));
            holder.course_timeto_1.setText(String.valueOf(courseL.getTimeTo()));
            holder.course_labweekday_1.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_1.setText( String.valueOf(courseL.getLabTimeFrom()));
            holder.course_labtimeto_1.setText( String.valueOf(courseL.getLabTimeTo()));
            holder.course_faculty_1.setText("Faculty: " + courseL.getFaculty());

            cardHeader = courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

        }
        else
        {
            Course course = Course.class.cast(data1);
            holder.course_name_1.setText(course.courseName());
            holder.course_section_1.setText("Section: " + String.valueOf(course.getSection()));
            holder.course_weekday_1.setText("Weekday: " +course.getWeekDay());
            holder.course_timefrom_1.setText(String.valueOf(course.getTimeFrom()));
            holder.course_timeto_1.setText(String.valueOf(course.getTimeTo()));
            holder.course_faculty_1.setText("Faculty: " + course.getFaculty());

            holder.course1.removeView(holder.course_labweekday_1);
            holder.course1.removeView(holder.course_labtimefrom_1);
            holder.course1.removeView(holder.course_labtimeto_1);

            cardHeader = course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";
        }

        if (CourseL.class.isInstance(data2)) {
            CourseL courseL = CourseL.class.cast(data2);
            holder.course_name_2.setText(courseL.courseName());
            holder.course_section_2.setText("Section: " + String.valueOf(courseL.getSection()));
            holder.course_weekday_2.setText("Weekday: " +courseL.getWeekDay());
            holder.course_timefrom_2.setText(String.valueOf(courseL.getTimeFrom()));
            holder.course_timeto_2.setText(String.valueOf(courseL.getTimeTo()));
            holder.course_labweekday_2.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_2.setText( String.valueOf(courseL.getLabTimeFrom()));
            holder.course_labtimeto_2.setText( String.valueOf(courseL.getLabTimeTo()));
            holder.course_faculty_2.setText("Faculty: " + courseL.getFaculty());

            cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";
        }
        else
        {
            Course course = Course.class.cast(data2);
            holder.course_name_2.setText(course.courseName());
            holder.course_section_2.setText("Section: " + String.valueOf(course.getSection()));
            holder.course_weekday_2.setText("Weekday: " +course.getWeekDay());
            holder.course_timefrom_2.setText(String.valueOf(course.getTimeFrom()));
            holder.course_timeto_2.setText(String.valueOf(course.getTimeTo()));
            holder.course_faculty_2.setText("Faculty: " + course.getFaculty());

            holder.course2.removeView(holder.course_labweekday_2);
            holder.course2.removeView(holder.course_labtimefrom_2);
            holder.course2.removeView(holder.course_labtimeto_2);

            cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

        }

        if (CourseL.class.isInstance(data3)) {
            CourseL courseL = CourseL.class.cast(data3);
            holder.course_name_3.setText(courseL.courseName());
            holder.course_section_3.setText("Section: " + String.valueOf(courseL.getSection()));
            holder.course_weekday_3.setText("Weekday: " +courseL.getWeekDay());
            holder.course_timefrom_3.setText(String.valueOf(courseL.getTimeFrom()));
            holder.course_timeto_3.setText(String.valueOf(courseL.getTimeTo()));
            holder.course_labweekday_3.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_3.setText( String.valueOf(courseL.getLabTimeFrom()));
            holder.course_labtimeto_3.setText( String.valueOf(courseL.getLabTimeTo()));
            holder.course_faculty_3.setText("Faculty: " + courseL.getFaculty());

            cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

        }
        else
        {
            Course course = Course.class.cast(data3);
            holder.course_name_3.setText(course.courseName());
            holder.course_section_3.setText("Section: " + String.valueOf(course.getSection()));
            holder.course_weekday_3.setText("Weekday: " +course.getWeekDay());
            holder.course_timefrom_3.setText(String.valueOf(course.getTimeFrom()));
            holder.course_timeto_3.setText(String.valueOf(course.getTimeTo()));
            holder.course_faculty_3.setText("Faculty: " + course.getFaculty());

            holder.course3.removeView(holder.course_labweekday_3);
            holder.course3.removeView(holder.course_labtimefrom_3);
            holder.course3.removeView(holder.course_labtimeto_3);

            cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

        }
        if(totalCourse == 4)
        {
            final Object data4 = allSortedCourses.get(currentPosition).getvFourthCourse();

            if (CourseL.class.isInstance(data4)) {
                CourseL courseL = CourseL.class.cast(data4);
                holder.course_name_4.setText(courseL.courseName());
                holder.course_section_4.setText("Section: " + String.valueOf(courseL.getSection()));
                holder.course_weekday_4.setText("Weekday: " +courseL.getWeekDay());
                holder.course_timefrom_4.setText(String.valueOf(courseL.getTimeFrom()));
                holder.course_timeto_4.setText(String.valueOf(courseL.getTimeTo()));
                holder.course_labweekday_4.setText( courseL.getLabWeekDay());
                holder.course_labtimefrom_4.setText( String.valueOf(courseL.getLabTimeFrom()));
                holder.course_labtimeto_4.setText( String.valueOf(courseL.getLabTimeTo()));
                holder.course_faculty_4.setText("Faculty: " + courseL.getFaculty());

                cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

            }
            else
            {
                Course course = Course.class.cast(data4);
                holder.course_name_4.setText(course.courseName());
                holder.course_section_4.setText("Section: " + String.valueOf(course.getSection()));
                holder.course_weekday_4.setText("Weekday: " +course.getWeekDay());
                holder.course_timefrom_4.setText(String.valueOf(course.getTimeFrom()));
                holder.course_timeto_4.setText(String.valueOf(course.getTimeTo()));
                holder.course_faculty_4.setText("Faculty: " + course.getFaculty());

                holder.course4.removeView(holder.course_labweekday_4);
                holder.course4.removeView(holder.course_labtimefrom_4);
                holder.course4.removeView(holder.course_labtimeto_4);

                cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

            }
        }
        else if(totalCourse == 3)
        {
            holder.expandableRelativeLayout.removeView(holder.course4);
        }

        holder.txtHeader.setText(cardHeader);

        holder.setIsRecyclable(false);
        if(!expandState.get(currentPosition))
        {
            holder.expandableRelativeLayout.collapse();
        }
        holder.expandableRelativeLayout.setInterpolator(Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR));
        holder.expandableRelativeLayout.setExpanded(expandState.get(currentPosition));

        holder.expandableRelativeLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandState.put(currentPosition, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandState.put(currentPosition, false);
            }
        });
        holder.buttonLayout.setRotation(expandState.get(currentPosition) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });
        holder.threeCard.setCardBackgroundColor(Color.parseColor(colors[randomNumber.nextInt(12)]));
        holder.threeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });
    }

    public class ShortCourseViewHolder extends RecyclerView.ViewHolder
    {
        // Master Layouts
        ExpandableRelativeLayout expandableRelativeLayout;
        RelativeLayout buttonLayout;
        CardView threeCard;
        // Courses Layouts
        RelativeLayout course1;
        RelativeLayout course2;
        RelativeLayout course3;
        RelativeLayout course4;
        // Card Header Views
        TextView txtHeader;
        // Course 1 Views
        TextView course_name_1;
        TextView course_section_1;
        TextView course_timefrom_1;
        TextView course_timeto_1;
        TextView course_weekday_1;
        TextView course_labweekday_1;
        TextView course_labtimefrom_1;
        TextView course_labtimeto_1;
        TextView course_faculty_1;
        // Course 2 Views
        TextView course_name_2;
        TextView course_section_2;
        TextView course_timefrom_2;
        TextView course_timeto_2;
        TextView course_weekday_2;
        TextView course_labweekday_2;
        TextView course_labtimefrom_2;
        TextView course_labtimeto_2;
        TextView course_faculty_2;
        // Course 3 Views
        TextView course_name_3;
        TextView course_section_3;
        TextView course_timefrom_3;
        TextView course_timeto_3;
        TextView course_weekday_3;
        TextView course_labweekday_3;
        TextView course_labtimefrom_3;
        TextView course_labtimeto_3;
        TextView course_faculty_3;
        // Course 4 Views
        TextView course_name_4;
        TextView course_section_4;
        TextView course_timefrom_4;
        TextView course_timeto_4;
        TextView course_weekday_4;
        TextView course_labweekday_4;
        TextView course_labtimefrom_4;
        TextView course_labtimeto_4;
        TextView course_faculty_4;

        public ShortCourseViewHolder(View itemView)
        {
            super(itemView);
            // Master Layouts
            threeCard = (CardView) itemView.findViewById(R.id.threecard);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.sorted_triangle_button);
            expandableRelativeLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.sorted_expand_layout);
            // Courses Layouts
            course1 = (RelativeLayout) itemView.findViewById(R.id.course1);
            course2 = (RelativeLayout) itemView.findViewById(R.id.course2);
            course3 = (RelativeLayout) itemView.findViewById(R.id.course3);
            course4 = (RelativeLayout) itemView.findViewById(R.id.course4);
            // Card Header Views
            txtHeader = (TextView) itemView.findViewById(R.id.all_course_name);
            // Course 1 Views
            course_name_1 = (TextView) itemView.findViewById(R.id.course_name_1);
            course_section_1 = (TextView) itemView.findViewById(R.id.course_section_1);
            course_timefrom_1 = (TextView) itemView.findViewById(R.id.course_timefrom_1);
            course_timeto_1 = (TextView) itemView.findViewById(R.id.course_timeto_1);
            course_weekday_1 = (TextView) itemView.findViewById(R.id.course_weekday_1);
            course_labweekday_1 = (TextView) itemView.findViewById(R.id.course_labweekday_1);
            course_labtimefrom_1 = (TextView) itemView.findViewById(R.id.course_labtimefrom_1);
            course_labtimeto_1 = (TextView) itemView.findViewById(R.id.course_labtimeto_1);
            course_faculty_1 = (TextView) itemView.findViewById(R.id.course_faculty_1);
            // Course 2 Views
            course_name_2 = (TextView) itemView.findViewById(R.id.course_name_2);
            course_section_2 = (TextView) itemView.findViewById(R.id.course_section_2);
            course_timefrom_2 = (TextView) itemView.findViewById(R.id.course_timefrom_2);
            course_timeto_2 = (TextView) itemView.findViewById(R.id.course_timeto_2);
            course_weekday_2 = (TextView) itemView.findViewById(R.id.course_weekday_2);
            course_labweekday_2 = (TextView) itemView.findViewById(R.id.course_labweekday_2);
            course_labtimefrom_2 = (TextView) itemView.findViewById(R.id.course_labtimefrom_2);
            course_labtimeto_2 = (TextView) itemView.findViewById(R.id.course_labtimeto_2);
            course_faculty_2 = (TextView) itemView.findViewById(R.id.course_faculty_2);
            // Course 3 Views
            course_name_3 = (TextView) itemView.findViewById(R.id.course_name_3);
            course_section_3 = (TextView) itemView.findViewById(R.id.course_section_3);
            course_timefrom_3 = (TextView) itemView.findViewById(R.id.course_timefrom_3);
            course_timeto_3 = (TextView) itemView.findViewById(R.id.course_timeto_3);
            course_weekday_3 = (TextView) itemView.findViewById(R.id.course_weekday_3);
            course_labweekday_3 = (TextView) itemView.findViewById(R.id.course_labweekday_3);
            course_labtimefrom_3 = (TextView) itemView.findViewById(R.id.course_labtimefrom_3);
            course_labtimeto_3 = (TextView) itemView.findViewById(R.id.course_labtimeto_3);
            course_faculty_3 = (TextView) itemView.findViewById(R.id.course_faculty_3);
            // Course 4 Views
            course_name_4 = (TextView) itemView.findViewById(R.id.course_name_4);
            course_section_4 = (TextView) itemView.findViewById(R.id.course_section_4);
            course_timefrom_4 = (TextView) itemView.findViewById(R.id.course_timefrom_4);
            course_timeto_4 = (TextView) itemView.findViewById(R.id.course_timeto_4);
            course_weekday_4 = (TextView) itemView.findViewById(R.id.course_weekday_4);
            course_labweekday_4 = (TextView) itemView.findViewById(R.id.course_labweekday_4);
            course_labtimefrom_4 = (TextView) itemView.findViewById(R.id.course_labtimefrom_4);
            course_labtimeto_4 = (TextView) itemView.findViewById(R.id.course_labtimeto_4);
            course_faculty_4 = (TextView) itemView.findViewById(R.id.course_faculty_4);

        }
    }

    @Override
    public int getItemCount() {
        return allSortedCourses.size();
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}

