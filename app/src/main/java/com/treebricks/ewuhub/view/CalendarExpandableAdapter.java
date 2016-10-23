package com.treebricks.ewuhub.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.treebricks.ewuhub.R;

import java.util.List;

/**
 * Created by fahim on 10/23/16.
 */

public class CalendarExpandableAdapter
        extends ExpandableRecyclerAdapter<CalendarParent, CalendarEvent, CalendarParentViewHolder, CalendarEventViewHolder>
{
    private LayoutInflater mInflater;

    public CalendarExpandableAdapter(Context context, @NonNull List<CalendarParent> calendarParentList)
    {
        super(calendarParentList);
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public CalendarParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_item_calendar_parent, parentViewGroup, false);
        return new CalendarParentViewHolder(view);
    }

    @NonNull
    @Override
    public CalendarEventViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_item_calendar_child, childViewGroup, false);
        return new CalendarEventViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull CalendarParentViewHolder parentViewHolder, int parentPosition, @NonNull CalendarParent parent) {
        parentViewHolder.bind(parent.getEventDate(), parent.getEventDay());
    }

    @Override
    public void onBindChildViewHolder(@NonNull CalendarEventViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull CalendarEvent child) {
        childViewHolder.bind(child.getEvent());
    }
}
