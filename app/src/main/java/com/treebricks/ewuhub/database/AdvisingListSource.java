package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.treebricks.ewuhub.view.AdvisingList;

import java.io.IOException;
import java.util.ArrayList;

import model.Course;

/**
 * Created by fahim on 10/25/16.
 */

public class AdvisingListSource
{
    public static final String LOGTAG = "EwuHub";
    private Cursor cursor = null;
    private static final String[] allCloumns = {
            "CourseName",
            "Section",
            "TimeFrom",
            "TimeTo",
            "WeekDay",
            "Faculty",
            "Room"
    };

    public ArrayList<AdvisingList> findAll(Context context)
    {
        String table = "AdvisingList";
        DatabaseHelper dbhelper  = new DatabaseHelper(context);
        ArrayList<AdvisingList> allCoursesList = new ArrayList<AdvisingList>();
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
                AdvisingList advisingList = new AdvisingList();
                advisingList.setCourseName(cursor.getString(cursor.getColumnIndex("CourseName")));
                advisingList.setCourseSection(cursor.getString(cursor.getColumnIndex("Section")));
                advisingList.setCourseTimefrom(cursor.getString(cursor.getColumnIndex("TimeFrom")));
                advisingList.setCourseTimeto(cursor.getString(cursor.getColumnIndex("TimeTo")));
                advisingList.setCourseWeekday(cursor.getString(cursor.getColumnIndex("WeekDay")));
                advisingList.setCourseFaculty(cursor.getString(cursor.getColumnIndex("Faculty")));
                advisingList.setCourseRoom(cursor.getString(cursor.getColumnIndex("Room")));
                allCoursesList.add(advisingList);

            }while(cursor.moveToNext());
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
                dbhelper.close();
            }
        }
        return allCoursesList;
    }
}
