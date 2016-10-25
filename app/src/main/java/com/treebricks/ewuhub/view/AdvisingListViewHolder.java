package com.treebricks.ewuhub.view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.treebricks.ewuhub.R;

/**
 * Created by fahim on 10/25/16.
 */

public class AdvisingListViewHolder extends RecyclerView.ViewHolder {

    TextView courseName;
    TextView courseSection;
    TextView courseWeekday;
    TextView courseTimefrom;
    TextView courseTimeTo;
    TextView courseFaculty;
    TextView courseRoom;

    ExpandableRelativeLayout expandableRelativeLayout;
    public RelativeLayout buttonLayout;

    public AdvisingListViewHolder(View itemView) {
        super(itemView);
        courseName = (TextView) itemView.findViewById(R.id.course_name);
        courseSection = (TextView) itemView.findViewById(R.id.course_section);
        courseWeekday = (TextView) itemView.findViewById(R.id.course_weekday);
        courseTimefrom = (TextView) itemView.findViewById(R.id.course_timefrom);
        courseTimeTo = (TextView) itemView.findViewById(R.id.course_timeto);
        courseFaculty = (TextView) itemView.findViewById(R.id.course_faculty);
        courseRoom = (TextView) itemView.findViewById(R.id.course_room);
        expandableRelativeLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.advising_expand_layout);
        buttonLayout = (RelativeLayout) itemView.findViewById(R.id.advising_triangle_button);
    }

    public void dataBind(AdvisingList advisingList)
    {
        courseName.setText(advisingList.getCourseName());
        courseSection.setText(String.format("Section: %s", advisingList.getCourseSection()));
        courseWeekday.setText(String.format("WeekDay: %s", advisingList.getCourseWeekday()));
        courseTimefrom.setText(String.format("Time From: %s", advisingList.getCourseTimefrom()));
        courseTimeTo.setText(String.format("Time To: %s", advisingList.getCourseTimeto()));
        courseFaculty.setText(String.format("Faculty: %s", advisingList.getCourseFaculty()));
        courseRoom.setText(String.format("Room: %s", advisingList.getCourseRoom()));
    }
}
