package com.treebricks.ewuhub.view;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.Utils;
import com.treebricks.ewuhub.R;

import java.util.ArrayList;


public class AdvisingListAdapter extends RecyclerView.Adapter<AdvisingListViewHolder>
    implements SectionTitleProvider
{

    private ArrayList<AdvisingList> allCoursesList;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public AdvisingListAdapter(ArrayList<AdvisingList> allCoursesList)
    {
        this.allCoursesList = allCoursesList;
        for (int i = 0; i < allCoursesList.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public AdvisingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advisinglist_card, parent, false);
        return new AdvisingListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdvisingListViewHolder holder, int position)
    {
        final int currentPosition = position;

        AdvisingList advisingList = allCoursesList.get(currentPosition);

        holder.dataBind(advisingList);

        holder.setIsRecyclable(false);
        if(!expandState.get(currentPosition))
        {
            holder.expandableRelativeLayout.collapse();
        }
        holder.expandableRelativeLayout.setInterpolator(Utils.createInterpolator(Utils.DECELERATE_INTERPOLATOR));
        holder.expandableRelativeLayout.setExpanded(expandState.get(position));
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
        holder.advisingListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableRelativeLayout);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCoursesList.size();
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

    @Override
    public String getSectionTitle(int position) {
        String courseName = allCoursesList.get(position).getCourseName();
        String sentName;
        if (courseName.length() > 5){

            sentName = courseName.substring(0, 6);
        }
        else
        {
            sentName = courseName.substring(0, 5);
        }
        return sentName;
    }
}
