package com.treebricks.ewuhub.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.treebricks.ewuhub.R;

/**
 * Created by fahim on 10/23/16.
 */

public class CalendarEventViewHolder extends ChildViewHolder {

    public TextView event;

    public CalendarEventViewHolder(View itemView) {
        super(itemView);

        event=(TextView) itemView.findViewById(R.id.calendar_event_details);
    }
    public void bind(String eventDetails)
    {
        event.setText(eventDetails);
    }

}
