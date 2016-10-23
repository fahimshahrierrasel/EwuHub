package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.treebricks.ewuhub.R;

/**
 * Created by fahim on 10/23/16.
 */

public class AcademicCalendarViewHolder extends RecyclerView.ViewHolder {

    TextView calendarDate;
    TextView calendarDay;
    TextView calendarDescription;
    ExpandableRelativeLayout expandableRelativeLayout;
    public RelativeLayout buttonLayout;

    public AcademicCalendarViewHolder(View itemView) {
        super(itemView);
        calendarDate = (TextView) itemView.findViewById(R.id.calendar_date);
        calendarDay = (TextView) itemView.findViewById(R.id.calendar_day);
        calendarDescription = (TextView) itemView.findViewById(R.id.calendar_description);
        buttonLayout = (RelativeLayout) itemView.findViewById(R.id.triangle_button);
        expandableRelativeLayout = (ExpandableRelativeLayout) itemView.findViewById(R.id.calendar_desc_layout);

    }
    public void bindData(String calDate, String calDay, String calDescription)
    {
        calendarDate.setText(calDate);
        calendarDay.setText(calDay);
        calendarDescription.setText(calDescription);
    }
}
