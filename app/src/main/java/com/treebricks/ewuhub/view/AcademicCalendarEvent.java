package com.treebricks.ewuhub.view;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fahim on 10/23/16.
 */

public class AcademicCalendarEvent
{
    @SerializedName("Date")
    String calDate;

    @SerializedName("ActualDate")
    String calADate;

    @SerializedName("Day")
    String calDay;

    @SerializedName("Event")
    String calEvent;

    public AcademicCalendarEvent() {
    }

    public AcademicCalendarEvent(String calDate, String calADate, String calDay, String calEvent) {
        this.calDate = calDate;
        this.calADate = calADate;
        this.calDay = calDay;
        this.calEvent = calEvent;
    }

    public String getCalDate() {
        return calDate;
    }

    public void setCalDate(String calDate) {
        this.calDate = calDate;
    }

    public String getCalADate() {
        return calADate;
    }

    public void setCalADate(String calADate) {
        this.calADate = calADate;
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

    @Override
    public String toString() {
        return "AcademicCalendarEvent{" +
                "calDate='" + calDate + '\'' +
                ", calADate='" + calADate + '\'' +
                ", calDay='" + calDay + '\'' +
                ", calEvent='" + calEvent + '\'' +
                '}';
    }
}
