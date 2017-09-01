package com.infitronics.www.School_Parent.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.infitronics.www.School_Parent.MainActivity;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.utils.BackHandledFragment;

import java.util.ArrayList;



public class Home_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    View myview;
    private boolean viewIsAtHome;
    private FragmentActivity myContext;


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
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ViewHolder holder= (ViewHolder) view.getTag();
        Homeicon temp= (Homeicon) holder.mylabel.getTag();
        int myid = temp.imageId;
        displayView(myid);
    }

    private void displayView(int viewId) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        Log.d("TAG", "displayView: "+viewId);
        switch (viewId) {

            case R.drawable.homework:
                fragment = new Homework_Fragment();
                title  = "HomeWork";
                viewIsAtHome = false;
                break;
            case R.drawable.noticeboard:
                fragment = new Notice_Fragment();
                title  = "Notice Board";
                viewIsAtHome = false;
                break;
//            case R.drawable.timetable:
//                fragment = new Timetable_Fragment();
//                title  = "Time Table";
//                viewIsAtHome = false;
//                break;
//            case R.drawable.result:
//                fragment = new Result_Fragment();
//                title  = "Result";
//                viewIsAtHome = true;
//                break;
            case R.drawable.remark:
                fragment = new Remark_Fragment();
                title  = "Remark";
                viewIsAtHome = false;
                break;
            case R.drawable.attendance:
                fragment = new Attendance_Fragment();
                title  = "Attendance";
                viewIsAtHome = false;
                break;
//            case R.drawable.rewards:
//                fragment = new Projectdemo_Fragment();
//                title  = "Project Demo";
//                viewIsAtHome = true;
//                break;
//            case R.drawable.gallery:
//                fragment = new Gallery_Fragment();
//                title  = "Gallery";
//                viewIsAtHome = false;
//                break;
            case R.drawable.leavenote:
                fragment = new Leavenote_Fragment();
                title  = "Leave Note";
                viewIsAtHome = false;
                break;

        }


        if (fragment != null) {
            FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.addToBackStack("tag");
            ft.commit();
        }

        // set the toolbar title
        ((MainActivity) getActivity())
                .setActionBarTitle(title);

    }

    class Homeicon
    {
        int imageId;
        String label;

        Homeicon(int imageId, String label)
        {
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
            int[] Icons = {R.drawable.attendance, R.drawable.homework, R.drawable.noticeboard, R.drawable.remark, R.drawable.leavenote};
//            int[] Icons = {R.drawable.attendance, R.drawable.homework, R.drawable.noticeboard, R.drawable.timetable, R.drawable.result, R.drawable.remark, R.drawable.leavenote, R.drawable.rewards, R.drawable.gallery};

            for (int i = 0; i < 5 ; i++) {
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





