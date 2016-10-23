package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.treebricks.ewuhub.R;
import java.util.List;


/**
 * Created by fahim on 10/23/16.
 */

public class AcademicCalendarAdapter extends RecyclerView.Adapter<AcademicCalendarViewHolder> {

    List<AcademicCalendarEvent> calendarModels;
    Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public AcademicCalendarAdapter(List<AcademicCalendarEvent> calendarModels, Context context)
    {
        this.calendarModels = calendarModels;
        this.context = context;
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
    public void onBindViewHolder(final AcademicCalendarViewHolder holder, final int position) {
        String calDate = calendarModels.get(position).getCalDate();
        String calDay = calendarModels.get(position).getCalDay();
        String calDescription = calendarModels.get(position).getCalEvent();
        holder.bindData(calDate, calDay, calDescription);

        holder.setIsRecyclable(false);
        if(!expandState.get(position))
        {
            holder.expandableRelativeLayout.collapse();
        }
        holder.expandableRelativeLayout.setInterpolator(Utils.createInterpolator(Utils.BOUNCE_INTERPOLATOR));
        holder.expandableRelativeLayout.setExpanded(expandState.get(position));
        holder.expandableRelativeLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandState.put(position, false);
            }
        });
        holder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        holder.buttonLayout.setOnClickListener(new View.OnClickListener() {
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

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

}
