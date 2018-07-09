package com.treebricks.ewuhub.view;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahim on 10/24/16.
 */

public class AcademicCalendarModel {

    @SerializedName("semester")
    private String semester;

    @SerializedName("lebel")
    private String lebel;

    @SerializedName("events")
    private List<AcademicCalendarEvent> academicCalendarEvents = new ArrayList<AcademicCalendarEvent>();

    public AcademicCalendarModel() {
    }

    public AcademicCalendarModel(String semester, String lebel, List<AcademicCalendarEvent> academicCalendarEvents) {
        this.semester = semester;
        this.lebel = lebel;
        this.academicCalendarEvents = academicCalendarEvents;
    }

    public String getSemester() {
        return semester;
    }


    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLebel() {
        return lebel;
    }

    public void setLebel(String lebel) {
        this.lebel = lebel;
    }


    public List<AcademicCalendarEvent> getAcademicCalendarEvents() {
        return academicCalendarEvents;
    }

    public void setAcademicCalendarEvents(List<AcademicCalendarEvent> AcademicCalendarEvents) {
        this.academicCalendarEvents = AcademicCalendarEvents;
    }
}
