package com.treebricks.ewuhub.view;


import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by fahim on 10/22/16.
 */

public class CalendarParent implements Parent<CalendarEvent>
{
    private String eventDate;
    private String eventDay;
    private List<CalendarEvent> allEvents;



    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public void setChildList(List<CalendarEvent> allEvents) {
        this.allEvents = allEvents;
    }

    @Override
    public List<CalendarEvent> getChildList() {
        return allEvents;
    }


    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
