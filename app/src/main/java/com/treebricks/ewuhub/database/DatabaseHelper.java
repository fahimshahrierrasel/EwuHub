package com.treebricks.ewuhub.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;


class DatabaseHelper extends SQLiteOpenHelper
{
    private String DATABASE_PATH = null;
    private static String DATABASE_NAME = "CoursesDatabase.db";
    private static int DATABASE_VERSION = 1;
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        this.DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    private boolean checkDatabase()
    {
        SQLiteDatabase checkDatabase = null;
        try
        {
            String databasePath = DATABASE_PATH + DATABASE_NAME;
            checkDatabase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException ignored){}

        if(checkDatabase != null)
        {
            checkDatabase.close();
        }
        return (checkDatabase != null);
    }

    private void copyDatabase() throws IOException
    {
        InputStream databaseIS = myContext.getAssets().open(DATABASE_NAME);
        String databaseFile = DATABASE_PATH + DATABASE_NAME;
        OutputStream databaseOS = new FileOutputStream(databaseFile);
        byte[] buffer = new byte[10];
        int length;
        while((length = databaseIS.read(buffer)) > 0)
        {
            databaseOS.write(buffer, 0, length);
        }
        databaseOS.flush();
        databaseOS.close();
        databaseIS.close();
    }



    void createDatabase() throws IOException
    {
        boolean dataBaseExist = checkDatabase();
        if(!dataBaseExist)
        {
            this.getReadableDatabase();
            try
            {
                copyDatabase();
            }
            catch (IOException ioe)
            {
                throw new Error("Error copying Database!");
            }
        }
    }

    void openDatabase() throws SQLException
    {
        String myPath= DATABASE_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close()
    {
        if(myDataBase != null)
        {
            myDataBase.close();
        }
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(newVersion > oldVersion)
        {
            try
            {
                copyDatabase();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

    /*
    Cursor query(String table, String[] columns,
                 String selection, String[] selectionArgs,
                 String groupBy, String having, String orderBy) {
        return myDataBase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    */

    Cursor rawQuery(String sqlQuery, String[] id)
    {
        return myDataBase.rawQuery(sqlQuery, id);
    }
}