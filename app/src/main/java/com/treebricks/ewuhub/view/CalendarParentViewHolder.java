package com.treebricks.ewuhub.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.treebricks.ewuhub.R;


/**
 * Created by fahim on 10/23/16.
 */

public class CalendarParentViewHolder extends ParentViewHolder {

    public TextView eventDate;
    public TextView eventDay;
    public ImageButton parentDropDownArrow;

    public CalendarParentViewHolder(View itemView) {
        super(itemView);
        eventDate = (TextView)itemView.findViewById(R.id.calendar_event_date);
        eventDay = (TextView)itemView.findViewById(R.id.calendar_event_day);
        parentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_recycle_item_expand_arrow);
        parentDropDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                } else {
                    expandView();
                }
            }
        });

        final Context context = itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Parent", "Parent at " + getParentAdapterPosition() + " clicked, parent item is \"" + getParent() + "\"");
                Toast.makeText(context, "This sample shows how to make a row only expand upon clicking our custom arrow view", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void bind(String date, String day)
    {
        eventDate.setText(date);
        eventDay.setText(day);
    }
}
