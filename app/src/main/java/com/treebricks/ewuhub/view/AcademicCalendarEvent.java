package com.treebricks.ewuhub.view;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fahim on 10/23/16.
 */

public class AcademicCalendarEvent
{
    @SerializedName("Date")
    String calDate;

    @SerializedName("Day")
    String calDay;

    @SerializedName("Event")
    String calEvent;

    public AcademicCalendarEvent() {
    }

    public AcademicCalendarEvent(String calDate, String calDay, String calEvent) {
        this.calDate = calDate;
        this.calDay = calDay;
        this.calEvent = calEvent;
    }

    public String getCalDate() {
        return calDate;
    }

    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }

    public String getCalDay() {
        return calDay;
    }

    public void setCalDay(String calDay) {
        this.calDay = calDay;
    }

    public String getCalEvent() {
        return calEvent;
    }

    public void setCalEvent(String calEvent) {
        this.calEvent = calEvent;
    }
}
