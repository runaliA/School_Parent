package com.infitronics.www.School_Parent.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.greasemonk.timetable.TimeTable;
import com.infitronics.www.School_Parent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 28/2/17.
 */

public class Timetable_Activity extends AppCompatActivity{
    private static final int GENERATED_AMOUNT = 20;
    private TimeTable timeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*timeTable = (TimeTable) findViewById(R.id.time_table);
        timeTable.setItems(generateSamplePlanData(this));*/
    }

    private static List<EmployeePlanItem> generateSamplePlanData(Context context)
    {
        List<EmployeePlanItem> planItems = new ArrayList<>();
        for(int i = 0; i < GENERATED_AMOUNT; i++)
            planItems.add(EmployeePlanItem.generateSample(context, i));

        return planItems;
    }
}
