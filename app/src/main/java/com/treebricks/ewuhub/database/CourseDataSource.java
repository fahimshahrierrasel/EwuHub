package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import java.io.IOException;
import java.util.ArrayList;

import model.Course;


public class CourseDataSource
{

    public ArrayList<Course> findAll(Context context, String courseName)
    {
        DatabaseHelper databaseHelper  = new DatabaseHelper(context);
        ArrayList<Course> allCourses = new ArrayList<>();
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

        Cursor cursor = databaseHelper.rawQuery("Select * From WithoutLab Where CourseName = ?", new String[]{courseName});

        if(cursor.moveToFirst())
        {
            do
            {
                Course course = new Course();
                course.setCourseName(cursor.getString(cursor.getColumnIndex("CourseName")));
                course.setSection(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("Section"))));
                course.setTimeFrom(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeFrom"))));
                course.setTimeTo(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("TimeTo"))));
                course.setWeekDay(cursor.getString(cursor.getColumnIndex("WeekDay")));
                course.setFaculty(cursor.getString(cursor.getColumnIndex("Faculty")));

                allCourses.add(course);

            } while(cursor.moveToNext());
            if (!cursor.isClosed())
            {
                cursor.close();
                databaseHelper.close();
            }
        }
        return allCourses;
    }
}
