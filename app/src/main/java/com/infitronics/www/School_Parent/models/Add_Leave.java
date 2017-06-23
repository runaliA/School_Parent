package com.infitronics.www.School_Parent.models;

import android.util.Log;

import java.util.Date;

/**
 * Created by Priyanka on 6/13/2017.
 */

public class Add_Leave
{
    String startDate;
    String endDate;
    String reason;
    int Student;
    int RID;

    public Add_Leave(String startDate, String endDate, int student ,String reason, int RID)
    {
        this.startDate=startDate;
        this.endDate=endDate;
        this.reason=reason;
        this.Student=student;
        this.RID=RID;

        Log.e("RESPONSE ASS  ","******");
    }
}
