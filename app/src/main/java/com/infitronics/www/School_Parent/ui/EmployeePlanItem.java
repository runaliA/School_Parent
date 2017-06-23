package com.infitronics.www.School_Parent.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.greasemonk.timetable.GridItem;
import com.greasemonk.timetable.IGridItem;
import com.greasemonk.timetable.TimeRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static android.content.ContentValues.TAG;
import static java.util.Calendar.AM;
import static java.util.Calendar.PM;

/**
 * Created by shashank on 28/2/17.
 */

public class EmployeePlanItem implements IGridItem {

    private String employeeName, projectName;
    private TimeRange timeRange;

    public EmployeePlanItem() {}
    public EmployeePlanItem(Context context, String employeeName, String projectName, Date planStart, Date planEnd)
    {
        this.employeeName = employeeName;
        this.projectName = projectName;
       this.timeRange = new TimeRange(planStart, planEnd);
    }
    @Override
    public TimeRange getTimeRange()
    {
        return timeRange;
    }

    @Override
    public String getName()
    {
        return projectName;
    }

    @Override
    public String getPersonName()
    {
        return employeeName;
    }

    public static EmployeePlanItem generateSample(Context context, int i) {
        final String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday","Friday", "Saturday", "Sunday"};
        final String[] subjectNames = {"English", "Maths", "Marathi","Geometry", "History", "Science", "Hindi",};


        // Generate a date range between now and 30 days
      Random rand = new Random();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        int r1 = -rand.nextInt(12);
        int r2 = rand.nextInt(12);
        start.add(Calendar.DATE, r1);
        end.add(Calendar.DATE, i);

        return new EmployeePlanItem(context,weekdays[i] ,
                subjectNames[i],
             start.getTime(), end.getTime());
    }


}

