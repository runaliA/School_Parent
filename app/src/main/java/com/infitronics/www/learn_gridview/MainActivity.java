package com.infitronics.www.learn_gridview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView myGrid = (GridView) findViewById(R.id.gridView);
        myGrid.setAdapter(new GridAdapter(this));

    }
}

class Homeicon
{
    int imageId;
    String label;

    Homeicon(int imageId,String label){
        this.imageId=imageId;
        this.label=label;
    }
}
class GridAdapter extends BaseAdapter
{

    ArrayList <Homeicon> list;
    Context context;

    GridAdapter(Context context)
    {
        this.context=context;
        list= new ArrayList<Homeicon>();
        Resources res =context.getResources();
        String[] Labels =res.getStringArray(R.array.Homescreen_labels);
        int[] Icons = {R.drawable.attendance,R.drawable.homework,R.drawable.notice,R.drawable.timetable,R.drawable.result,R.drawable.remark,R.drawable.leavenote,R.drawable.projectdemo,R.drawable.gallery};

        for (int i=0;i<9;i++)
        {
        Homeicon myhomeicons = new Homeicon(Icons[i],Labels[i]);
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

    class ViewHolder
    {
        ImageView myicon;
        ViewHolder(View v)
        {
            myicon= (ImageView) v.findViewById(R.id.imageView);
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
    ViewHolder holder=null;
        View row=view;
        if (row == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item,viewGroup,false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) row.getTag();
        }
    Homeicon temp =list.get(i);
    holder.myicon.setImageResource(temp.imageId);
    return row;
    }
}