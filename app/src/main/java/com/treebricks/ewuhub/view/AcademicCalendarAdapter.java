package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.treebricks.ewuhub.R;
import com.treebricks.ewuhub.model.AcademicCalendarEvent;

import java.util.List;


public class AcademicCalendarAdapter extends RecyclerView.Adapter<AcademicCalendarViewHolder> {

    private List<AcademicCalendarEvent> calendarModels;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public AcademicCalendarAdapter(List<AcademicCalendarEvent> calendarModels)
    {
        this.calendarModels = calendarModels;
        for (int i = 0; i < calendarModels.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public AcademicCalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_card, parent, false);
        return new AcademicCalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AcademicCalendarViewHolder holder, int position) {

        final int currentPosition = position;

        String calDate = calendarModels.get(currentPosition).getCalDate();
        String calDay = calendarModels.get(currentPosition).getCalDay();
        String calDescription = calendarModels.get(currentPosition).getCalEvent();
        holder.bindData(calDate, calDay, calDescription);

        holder.setIsRecyclable(false);
        if(!expandState.get(position))
        {
            holder.expandableRelativeLayout.collapse();
        }
        holder.expandableRelativeLayout.setInterpolator(Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR));
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
        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });
        holder.calendarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });

    }

    @Override
    public int getItemCount() {
        return calendarModels.size();
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

}
