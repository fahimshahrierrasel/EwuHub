package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import com.treebricks.ewuhub.view.AdvisingList;
import java.io.IOException;
import java.util.ArrayList;

public class AdvisingListSource
{
    public ArrayList<AdvisingList> findAll(Context context)
    {
        DatabaseHelper databaseHelper  = new DatabaseHelper(context);
        ArrayList<AdvisingList> allCoursesList = new ArrayList<>();

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

        Cursor cursor = databaseHelper.rawQuery("Select * From AdvisingList", null);

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
            if (!cursor.isClosed()) {
                cursor.close();
                databaseHelper.close();
            }
        }
        return allCoursesList;
    }
}
