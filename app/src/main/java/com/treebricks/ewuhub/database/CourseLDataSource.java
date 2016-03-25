package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import model.CourseL;


public class CourseLDataSource
{
    public static final String LOGTAG = "EwuHub";
    Cursor cursor = null;
    private String table = "WithLab";
    private static final String[] allCloumns = {
            "CourseName",
            "Section",
            "TimeFrom",
            "TimeTo",
            "WeekDay",
            "LabTimeFrom",
            "LabTimeTo",
            "LabWeekDay"
    };

    public ArrayList<CourseL> findAll(Context context, String courseName)
    {
        DatabaseHelper dbhelper  = new DatabaseHelper(context);
        ArrayList<CourseL> allCourses = new ArrayList<CourseL>();
        try {
            dbhelper.createDataBase();
        }
        catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbhelper.openDataBase();
        }
        catch (SQLException sqle) {
            throw sqle;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        cursor = dbhelper.query(table, allCloumns, null, null, null, null, null);
        Log.i(LOGTAG, "Database Successfully Connected! With Lab");
        if(cursor.moveToFirst())
        {
            do
            {
                CourseL courseL = new CourseL();
                courseL.setCourseName(cursor.getString(cursor.getColumnIndex("CourseName")));
                courseL.setSection(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Section"))));
                courseL.setTimeFrom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeFrom"))));
                courseL.setTimeTo(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeTo"))));
                courseL.setWeekDay(cursor.getString(cursor.getColumnIndex("WeekDay")));
                courseL.setLabTimeFrom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("LabTimeFrom"))));
                courseL.setLabTimeTo(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("LabTimeTo"))));
                courseL.setLabWeekDay(cursor.getString(cursor.getColumnIndex("LabWeekDay")));

                if((courseL.getCourseName()).equals(courseName))
                {
                    Log.i(LOGTAG,"Need " + courseName + " & " + courseL.getCourseName() + " found!");
                    allCourses.add(courseL);
                }
            }while(cursor.moveToNext());
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return allCourses;
    }
}
