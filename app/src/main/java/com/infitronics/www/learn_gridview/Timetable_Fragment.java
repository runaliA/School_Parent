package com.infitronics.www.learn_gridview;

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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Timetable_Fragment extends Fragment {
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_timetable,container,false);

        GridView myGrid = (GridView) myview.findViewById(R.id.timetable_grid);
        myGrid.setAdapter(new GridAdapter(getActivity()));

        return myview;
    }
}

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
}