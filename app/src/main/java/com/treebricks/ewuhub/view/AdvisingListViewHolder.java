package com.treebricks.ewuhub.view;


import android.support.v7.widget.CardView;
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
    RelativeLayout buttonLayout;
    CardView advisingListCard;

    public AdvisingListViewHolder(View itemView) {
        super(itemView);
        courseName = (TextView) itemView.findViewById(R.id.course_name_1);
        courseSection = (TextView) itemView.findViewById(R.id.course_section_1);
        courseWeekday = (TextView) itemView.findViewById(R.id.course_weekday_1);
        courseTimefrom = (TextView) itemView.findViewById(R.id.course_timefrom_1);
        courseTimeTo = (TextView) itemView.findViewById(R.id.course_timeto_1);
        courseFaculty = (TextView) itemView.findViewById(R.id.course_faculty_1);
        courseRoom = (TextView) itemView.findViewById(R.id.course_room_1);
        expandableRelativeLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.advising_expand_layout);
        buttonLayout = (RelativeLayout) itemView.findViewById(R.id.advising_triangle_button);
        advisingListCard = (CardView) itemView.findViewById(R.id.advising_list_card);
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
