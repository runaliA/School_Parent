package com.infitronics.www.School_Parent.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.infitronics.www.School_Parent.models.Get_Remark;
import com.infitronics.www.School_Parent.models.RemarkSQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanka on 7/13/2017.
 */



public class SQLDBHandler
{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RemarkManager";

    // Contacts table name
    private static final String TABLE_REMARK = "remarkdetail";

    private static final String TBL_ID="id";
    private static final String TBL_RemarkTitle="remarktitle";
    private static final String TBL_RemarkData="remarkdata";
    private static final String TBL_RemarkDate="remarkDate";
    private static final String TAG = "DBAdapter";

    private final Context context;
    private SQLiteDatabase db;
    private DatabaseHelper DBHelper;

    public SQLDBHandler(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db)
//    {
//        String SQLQuery="Create table "+ TABLE_REMARK+"("+TBL_ID+ " INGETER PRIMARY KEY,"+TBL_RemarkTitle+" TEXT,"+TBL_RemarkData+" TEXT,"+TBL_RemarkDate+" TEXT)";
//        db.execSQL(SQLQuery);
//        Log.e("Create Table ","creating "+ SQLQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
//    {
//        db.execSQL("DROP TABLE IF EXIST "+TABLE_REMARK);
//        onCreate(db);
//    }


    /****    Getting All Details  *****/

//
//    public List<Get_Remark.Data> allData()
//    {
//        List<Get_Remark.Data> Arr_allRemarkData=new ArrayList<>();
//
//        String StrAllRemark="Select * from "+TABLE_REMARK;
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(StrAllRemark, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Get_Remark.Data remark = null;
////                remark.setId(Integer.parseInt(cursor.getString(0)));
////                contact.setID(Integer.parseInt(cursor.getString(0)));
//                remark.setTitle(cursor.getString(0));
//                remark.setStudent(cursor.getString(1));
//                remark.setDescription(cursor.getString(2));
//                // Adding contact to list
//                Arr_allRemarkData.add(remark);
//            } while (cursor.moveToNext());
//        }
//
//        return  Arr_allRemarkData;
//    }





    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(TABLE_REMARK, new String[] {TBL_RemarkTitle, TBL_RemarkData,TBL_RemarkDate},
                null, null, null, null, null);
    }


    public void addRemark(RemarkSQL remarkSQL)
    {
        SQLiteDatabase db=DBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBL_RemarkTitle, remarkSQL.getRemarkTitle()); // Remark Title
        values.put(TBL_RemarkData, remarkSQL.getRemarkData()); // Remark Data
        values.put(TBL_RemarkDate, remarkSQL.getStrDate()); // Remark Date

        // Inserting Row
        db.insert(TABLE_REMARK, null, values);
//        db.close(); // Closing database connection
    }


    public void deletetablerow()
    {
        SQLiteDatabase db=DBHelper.getWritableDatabase();
        db.delete(TABLE_REMARK,null,null);
        Log.e("Deleting Data","Data");
    }



    //---opens the database---
    public SQLDBHandler open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }



    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.e("Create Table ","creating ");
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

                String SQLQuery="Create table "+ TABLE_REMARK+"("+TBL_ID+ " INGETER PRIMARY KEY,"+TBL_RemarkTitle+" TEXT,"+TBL_RemarkData+" TEXT,"+TBL_RemarkDate+" TEXT)";
                db.execSQL(SQLQuery);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXIST "+TABLE_REMARK);
            onCreate(db);
        }
    }

}
