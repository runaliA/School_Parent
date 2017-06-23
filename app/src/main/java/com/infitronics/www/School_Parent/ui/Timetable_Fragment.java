package com.infitronics.www.School_Parent.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.greasemonk.timetable.TimeTable;
import com.infitronics.www.School_Parent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Timetable_Fragment extends Fragment {
    View myview;
    private static final int GENERATED_AMOUNT = 6;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_timetable,container,false);
       /* TimeTable timeTable = (TimeTable) myview.findViewById(R.id.time_table);
        timeTable.setItems(generateSamplePlanData(getActivity()));*/



        GridView myGrid = (GridView) myview.findViewById(R.id.timetable_grid);
        myGrid.setAdapter(new GridAdapter(getActivity()));

        /*************************/

        return myview;
    }

   /* private static List<EmployeePlanItem> generateSamplePlanData(Context context)
    {
        List<EmployeePlanItem> planItems = new ArrayList<>();
        for(int i = 0; i < GENERATED_AMOUNT; i++)
            planItems.add(EmployeePlanItem.generateSample(context, i));

        return planItems;
    }*/


class Homeicon
{
    String label;

    Homeicon( String label) {
        this.label = label;
    }
}

class ViewHolder
{
    TextView mylabel;

    ViewHolder(View v)
    {
        mylabel = (TextView) v.findViewById(R.id.sub_timetable);
    }
}

class GridAdapter extends BaseAdapter
{

    ArrayList<Homeicon> list;
    Context context;

    GridAdapter(FragmentActivity context) {
        this.context = context;
        list = new ArrayList<>();
        Resources res = context.getResources();
        String[] Labels = res.getStringArray(R.array.timetable_header);

        for (int i = 0; i < 49; i++) {
            Homeicon myhomeicons = new Homeicon(Labels[i]);
            list.add(myhomeicons);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        View row = view;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_timetable, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        Homeicon temp = list.get(i);
        holder.mylabel.setText(temp.label);
        holder.mylabel.setTag(temp);
        return row;
    }
}}