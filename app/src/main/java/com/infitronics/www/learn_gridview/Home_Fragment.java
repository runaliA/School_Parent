package com.infitronics.www.learn_gridview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.fragment;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Shashank on 28-01-2017.
 */

public class Home_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    View myview;
    private boolean viewIsAtHome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        myview = inflater.inflate(R.layout.fragment_home, container, false);

        GridView myGrid = (GridView) myview.findViewById(R.id.gridView);
        myGrid.setAdapter(new GridAdapter(getActivity()));
        myGrid.setOnItemClickListener(this);

        return myview;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ViewHolder holder= (ViewHolder) view.getTag();
        Homeicon temp= (Homeicon) holder.mylabel.getTag();
        String label_clicked = temp.label;
        Toast.makeText(getActivity(), temp.label, Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (label_clicked){
            case "Attendance":
                fragment = new Attendance_Fragment();
                title  = "Attendance";
                viewIsAtHome = false;
                break;
            case "Home-Work":
                fragment = new Attendance_Fragment();
                title  = "Home Work";
                viewIsAtHome = false;
                break;
            case "Notice-Board":
                fragment = new Attendance_Fragment();
                title  = "Notice Board";
                viewIsAtHome = false;
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        

    }


    class Homeicon
    {
        int imageId;
        String label;

        Homeicon(int imageId, String label) {
            this.imageId = imageId;
            this.label = label;
        }
    }

    class ViewHolder
    {
        ImageView myicon;
        TextView mylabel;

        ViewHolder(View v) {
            myicon = (ImageView) v.findViewById(R.id.imageView);
            mylabel = (TextView) v.findViewById(R.id.textView);
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
            String[] Labels = res.getStringArray(R.array.Homescreen_labels);
            int[] Icons = {R.drawable.attendance, R.drawable.homework, R.drawable.notice, R.drawable.timetable, R.drawable.result, R.drawable.remark, R.drawable.leavenote, R.drawable.projectdemo, R.drawable.gallery};

            for (int i = 0; i < 9; i++) {
                Homeicon myhomeicons = new Homeicon(Icons[i], Labels[i]);
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
                row = inflater.inflate(R.layout.single_item, viewGroup, false);
                holder = new ViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }
            Homeicon temp = list.get(i);
            holder.myicon.setImageResource(temp.imageId);
            holder.mylabel.setText(temp.label);
            holder.mylabel.setTag(temp);
            return row;
        }
    }
}
