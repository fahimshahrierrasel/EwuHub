package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.graphics.Color;
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
import java.util.Locale;

import com.treebricks.ewuhub.model.Course;
import com.treebricks.ewuhub.model.CourseL;
import com.treebricks.ewuhub.model.SortedCourses;

public class SortedCourseAdapter extends RecyclerView.Adapter<SortedCourseAdapter.ShortCourseViewHolder>
{
    private ArrayList<SortedCourses> allSortedCourses;
    private int totalCourse;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private String[] colors = {"#EF9A9A", "#80CBC4", "#FFE082", "#90CAF9"};

    public SortedCourseAdapter(ArrayList<SortedCourses> allSortedCourses, int totalCourse)
    {
        this.allSortedCourses = allSortedCourses;
        this.totalCourse = totalCourse;
        for (int i = 0; i < this.allSortedCourses.size(); i++) {
            expandState.append(i, false);
        }
    }

    public void add(int position, SortedCourses item)
    {
        allSortedCourses.add(position,item);
        notifyItemInserted(position);
    }

    @Override
    public ShortCourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_courses_card,parent,false);
        return new ShortCourseViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final ShortCourseViewHolder holder, int position)
    {

        final int currentPosition = position;

        String cardHeader = "No Courses";

        final Object data1 = allSortedCourses.get(currentPosition).getvFirstCourse();
        final Object data2 = allSortedCourses.get(currentPosition).getvSecondCourse();
        final Object data3 = allSortedCourses.get(currentPosition).getvThirdCourse();

        if (CourseL.class.isInstance(data1)) {

            CourseL courseL = CourseL.class.cast(data1);
            holder.course_name_1.setText(courseL.courseName());
            holder.course_section_1.setText(String.format("Section: %s", String.valueOf(courseL.getSection())));
            holder.course_weekday_1.setText(courseL.getWeekDay());
            holder.course_timefrom_1.setText(time12HourFormat(courseL.getTimeFrom()));
            holder.course_timeto_1.setText(time12HourFormat(courseL.getTimeTo()));
            holder.course_labweekday_1.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_1.setText( time12HourFormat(courseL.getLabTimeFrom()));
            holder.course_labtimeto_1.setText( time12HourFormat(courseL.getLabTimeTo()));
            holder.course_faculty_1.setText(String.format("Faculty: %s", courseL.getFaculty()));

            cardHeader = courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

        }
        else
        {
            Course course = Course.class.cast(data1);
            holder.course_name_1.setText(course.courseName());
            holder.course_section_1.setText(String.format("Section: %s", String.valueOf(course.getSection())));
            holder.course_weekday_1.setText(course.getWeekDay());
            holder.course_timefrom_1.setText(time12HourFormat(course.getTimeFrom()));
            holder.course_timeto_1.setText(time12HourFormat(course.getTimeTo()));
            holder.course_faculty_1.setText(String.format("Faculty: %s", course.getFaculty()));

            // Removing Views When Course has no Lab
            holder.course1.removeView(holder.course_labweekday_1);
            holder.course1.removeView(holder.course_labtimefrom_1);
            holder.course1.removeView(holder.course_labtimeto_1);
            holder.course1.removeView(holder.course_labweekday_label_1);
            holder.course1.removeView(holder.course_labtimefrom_label_1);
            holder.course1.removeView(holder.course_labtimeto_label_1);

            cardHeader = course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";
        }

        if (CourseL.class.isInstance(data2)) {
            CourseL courseL = CourseL.class.cast(data2);
            holder.course_name_2.setText(courseL.courseName());
            holder.course_section_2.setText(String.format("Section: %s", String.valueOf(courseL.getSection())));
            holder.course_weekday_2.setText(courseL.getWeekDay());
            holder.course_timefrom_2.setText(time12HourFormat(courseL.getTimeFrom()));
            holder.course_timeto_2.setText(time12HourFormat(courseL.getTimeTo()));
            holder.course_labweekday_2.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_2.setText( time12HourFormat(courseL.getLabTimeFrom()));
            holder.course_labtimeto_2.setText( time12HourFormat(courseL.getLabTimeTo()));
            holder.course_faculty_2.setText(String.format("Faculty: %s", courseL.getFaculty()));

            cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";
        }
        else
        {
            Course course = Course.class.cast(data2);
            holder.course_name_2.setText(course.courseName());
            holder.course_section_2.setText(String.format("Section: %s", String.valueOf(course.getSection())));
            holder.course_weekday_2.setText(course.getWeekDay());
            holder.course_timefrom_2.setText(time12HourFormat(course.getTimeFrom()));
            holder.course_timeto_2.setText(time12HourFormat(course.getTimeTo()));
            holder.course_faculty_2.setText(String.format("Faculty: %s", course.getFaculty()));

            // Removing Views When Course has no Lab
            holder.course2.removeView(holder.course_labweekday_2);
            holder.course2.removeView(holder.course_labtimefrom_2);
            holder.course2.removeView(holder.course_labtimeto_2);
            holder.course2.removeView(holder.course_labweekday_label_2);
            holder.course2.removeView(holder.course_labtimefrom_label_2);
            holder.course2.removeView(holder.course_labtimeto_label_2);

            cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

        }

        if (CourseL.class.isInstance(data3)) {
            CourseL courseL = CourseL.class.cast(data3);
            holder.course_name_3.setText(courseL.courseName());
            holder.course_section_3.setText(String.format("Section: %s", String.valueOf(courseL.getSection())));
            holder.course_weekday_3.setText(courseL.getWeekDay());
            holder.course_timefrom_3.setText(time12HourFormat(courseL.getTimeFrom()));
            holder.course_timeto_3.setText(time12HourFormat(courseL.getTimeTo()));
            holder.course_labweekday_3.setText( courseL.getLabWeekDay());
            holder.course_labtimefrom_3.setText( time12HourFormat(courseL.getLabTimeFrom()));
            holder.course_labtimeto_3.setText( time12HourFormat(courseL.getLabTimeTo()));
            holder.course_faculty_3.setText(String.format("Faculty: %s", courseL.getFaculty()));

            cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

        }
        else
        {
            Course course = Course.class.cast(data3);
            holder.course_name_3.setText(course.courseName());
            holder.course_section_3.setText(String.format("Section: %s", String.valueOf(course.getSection())));
            holder.course_weekday_3.setText(course.getWeekDay());
            holder.course_timefrom_3.setText(time12HourFormat(course.getTimeFrom()));
            holder.course_timeto_3.setText(time12HourFormat(course.getTimeTo()));
            holder.course_faculty_3.setText(String.format("Faculty: %s", course.getFaculty()));

            // Removing Views When Course has no Lab
            holder.course3.removeView(holder.course_labweekday_3);
            holder.course3.removeView(holder.course_labtimefrom_3);
            holder.course3.removeView(holder.course_labtimeto_3);
            holder.course3.removeView(holder.course_labweekday_label_3);
            holder.course3.removeView(holder.course_labtimefrom_label_3);
            holder.course3.removeView(holder.course_labtimeto_label_3);

            cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

        }
        if(totalCourse == 4)
        {
            final Object data4 = allSortedCourses.get(currentPosition).getvFourthCourse();

            if (CourseL.class.isInstance(data4)) {
                CourseL courseL = CourseL.class.cast(data4);
                holder.course_name_4.setText(courseL.courseName());
                holder.course_section_4.setText(String.format("Section: %s", String.valueOf(courseL.getSection())));
                holder.course_weekday_4.setText(courseL.getWeekDay());
                holder.course_timefrom_4.setText(time12HourFormat(courseL.getTimeFrom()));
                holder.course_timeto_4.setText(time12HourFormat(courseL.getTimeTo()));
                holder.course_labweekday_4.setText( courseL.getLabWeekDay());
                holder.course_labtimefrom_4.setText( time12HourFormat(courseL.getLabTimeFrom()));
                holder.course_labtimeto_4.setText( time12HourFormat(courseL.getLabTimeTo()));
                holder.course_faculty_4.setText(String.format("Faculty: %s", courseL.getFaculty()));

                cardHeader += ", " + courseL.getCourseName()+"(Sec: " + String.valueOf(courseL.getSection())+")";

            }
            else
            {
                Course course = Course.class.cast(data4);
                holder.course_name_4.setText(course.courseName());
                holder.course_section_4.setText(String.format("Section: %s", String.valueOf(course.getSection())));
                holder.course_weekday_4.setText(course.getWeekDay());
                holder.course_timefrom_4.setText(time12HourFormat(course.getTimeFrom()));
                holder.course_timeto_4.setText(time12HourFormat(course.getTimeTo()));
                holder.course_faculty_4.setText(String.format("Faculty: %s", course.getFaculty()));

                // Removing Views When Course has no Lab
                holder.course4.removeView(holder.course_labweekday_4);
                holder.course4.removeView(holder.course_labtimefrom_4);
                holder.course4.removeView(holder.course_labtimeto_4);
                holder.course4.removeView(holder.course_labweekday_label_4);
                holder.course4.removeView(holder.course_labtimefrom_label_4);
                holder.course4.removeView(holder.course_labtimeto_label_4);

                cardHeader += ", " + course.getCourseName()+"(Sec: " + String.valueOf(course.getSection())+")";

            }
        }
        else if(totalCourse == 3)
        {
            holder.expandableRelativeLayout.removeView(holder.course4);
        }

        holder.sortedCoursesName.setText(cardHeader);

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

        holder.sortedCoursesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });

        holder.course1.setBackgroundColor(Color.parseColor(colors[0]));
        holder.course2.setBackgroundColor(Color.parseColor(colors[1]));
        holder.course3.setBackgroundColor(Color.parseColor(colors[2]));
        holder.course4.setBackgroundColor(Color.parseColor(colors[3]));
    }

    class ShortCourseViewHolder extends RecyclerView.ViewHolder
    {
        // Master Layouts
        ExpandableRelativeLayout expandableRelativeLayout;
        RelativeLayout buttonLayout;
        CardView sortedCoursesCard;
        // Courses Layouts
        RelativeLayout course1;
        RelativeLayout course2;
        RelativeLayout course3;
        RelativeLayout course4;
        // Card Header Views
        TextView sortedCoursesName;
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
        TextView course_labweekday_label_1;
        TextView course_labtimefrom_label_1;
        TextView course_labtimeto_label_1;
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
        TextView course_labweekday_label_2;
        TextView course_labtimefrom_label_2;
        TextView course_labtimeto_label_2;
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
        TextView course_labweekday_label_3;
        TextView course_labtimefrom_label_3;
        TextView course_labtimeto_label_3;
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
        TextView course_labweekday_label_4;
        TextView course_labtimefrom_label_4;
        TextView course_labtimeto_label_4;

        ShortCourseViewHolder(View itemView)
        {
            super(itemView);
            // Master Layouts
            sortedCoursesCard = (CardView) itemView.findViewById(R.id.sortedcard);
            buttonLayout = (RelativeLayout) itemView.findViewById(R.id.sorted_triangle_button);
            expandableRelativeLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.sorted_expand_layout);
            // Courses Layouts
            course1 = (RelativeLayout) itemView.findViewById(R.id.course1);
            course2 = (RelativeLayout) itemView.findViewById(R.id.course2);
            course3 = (RelativeLayout) itemView.findViewById(R.id.course3);
            course4 = (RelativeLayout) itemView.findViewById(R.id.course4);
            // Card Header Views
            sortedCoursesName = (TextView) itemView.findViewById(R.id.all_course_name);
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
            course_labweekday_label_1 = (TextView) itemView.findViewById(R.id.course_labweekday_label_1);
            course_labtimefrom_label_1 = (TextView) itemView.findViewById(R.id.course_labtimefrom_label_1);
            course_labtimeto_label_1 = (TextView) itemView.findViewById(R.id.course_labtimeto_label_1);
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
            course_labweekday_label_2 = (TextView) itemView.findViewById(R.id.course_labweekday_label_2);
            course_labtimefrom_label_2 = (TextView) itemView.findViewById(R.id.course_labtimefrom_label_2);
            course_labtimeto_label_2 = (TextView) itemView.findViewById(R.id.course_labtimeto_label_2);
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
            course_labweekday_label_3 = (TextView) itemView.findViewById(R.id.course_labweekday_label_3);
            course_labtimefrom_label_3 = (TextView) itemView.findViewById(R.id.course_labtimefrom_label_3);
            course_labtimeto_label_3 = (TextView) itemView.findViewById(R.id.course_labtimeto_label_3);
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
            course_labweekday_label_4 = (TextView) itemView.findViewById(R.id.course_labweekday_label_4);
            course_labtimefrom_label_4 = (TextView) itemView.findViewById(R.id.course_labtimefrom_label_4);
            course_labtimeto_label_4 = (TextView) itemView.findViewById(R.id.course_labtimeto_label_4);

        }
    }

    @Override
    public int getItemCount() {
        return allSortedCourses.size();
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    private String time12HourFormat(int universalTime)
    {
        int minute = universalTime % 100;
        int hour =  universalTime / 100;
        return String.format(Locale.US, "%d:%02d %s", ((hour == 0 || hour == 12) ? 12 : hour % 12), minute,
                (hour < 12 ? "AM" : "PM"));
    }
}

