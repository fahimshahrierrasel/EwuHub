package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import java.io.IOException;
import java.util.ArrayList;

public class AllCoursesDataSource
{

    public ArrayList<String> findAll(Context context)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        ArrayList<String> allCourses = new ArrayList<>();
        try {
            databaseHelper.createDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            databaseHelper.openDatabase();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = databaseHelper.rawQuery("Select CourseName From AllCourses", null);

        if (cursor.moveToFirst()) {
            do {
                String string = cursor.getString(cursor.getColumnIndex("CourseName"));
                allCourses.add(string);
            } while (cursor.moveToNext());
            if (!cursor.isClosed()) {
                cursor.close();
                databaseHelper.close();
            }
        }
        return allCourses;
    }

}
