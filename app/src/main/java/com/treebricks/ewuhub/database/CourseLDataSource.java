package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import model.CourseL;

public class CourseLDataSource
{

    public ArrayList<CourseL> findAll(Context context, String courseName)
    {
        DatabaseHelper databaseHelper  = new DatabaseHelper(context);
        ArrayList<CourseL> allCourses = new ArrayList<>();

        try {
            databaseHelper.createDatabase();
        }
        catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            databaseHelper.openDatabase();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = databaseHelper.rawQuery("Select * From WithLab Where CourseName = ?", new String[]{courseName});

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
                courseL.setFaculty(cursor.getString(cursor.getColumnIndex("Faculty")));

                allCourses.add(courseL);
            }while(cursor.moveToNext());
            if (!cursor.isClosed())
            {
                cursor.close();
                databaseHelper.close();
            }
        }
        return allCourses;
    }
}
