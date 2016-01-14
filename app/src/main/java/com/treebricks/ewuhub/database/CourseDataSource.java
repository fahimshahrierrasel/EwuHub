package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import model.Course;


public class CourseDataSource
{
    public static final String LOGTAG = "EwuHub";
    Cursor cursor = null;
    private String table = "COURSEWITHOUTLAB";
    private static final String[] allCloumns = {
            "CourseName",
            "CourseCode",
            "Section",
            "TimeFrom",
            "TimeTo",
            "WeekDay"
    };

    public ArrayList<Course> findAll(Context context, String courseName)
    {
        DatabaseHelper dbhelper  = new DatabaseHelper(context, table);
        ArrayList<Course> allCourses = new ArrayList<Course>();
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
        Log.i(LOGTAG, "Database Successfully Connected!");
        if(cursor.moveToFirst())
        {
            do
            {
                Course course = new Course();
                course.setCourseTitle(cursor.getString(cursor.getColumnIndex("CourseName")));
                course.setCourseCode(cursor.getString(cursor.getColumnIndex("CourseCode")));
                course.setSection(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Section"))));
                course.setTimeFrom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeFrom"))));
                course.setTimeTo(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeTo"))));
                course.setWeekDay(cursor.getString(cursor.getColumnIndex("WeekDay")));
                if((course.getCourseTitle()+course.getCourseCode()).equals(courseName))
                {
                    Log.i(LOGTAG,"Need " + courseName + " & " + (course.getCourseTitle()+course.getCourseCode()) + " found!");
                    allCourses.add(course);
                }

            }while(cursor.moveToNext());
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return allCourses;
    }
}
