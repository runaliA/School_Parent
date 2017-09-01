package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Notice;
import com.infitronics.www.School_Parent.models.Get_Remark;
import com.infitronics.www.School_Parent.models.RemarkSQL;
import com.infitronics.www.School_Parent.utils.SQLDBHandler;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by shashank on 20/3/17.
 */

public class Remark_Adapter extends RecyclerView.Adapter<Remark_Adapter.ViewHolder>  {

    private List<Get_Remark.Data> dataList =new ArrayList<>();
    private List<RemarkSQL> dataList1 =new ArrayList<>();
    private int rowLayout;
    Context context;
    String param;

//    public Remark_Adapter(List<Get_Remark.Data> dataList, int rowLayout, Context context,String paramter) {
//        this.dataList = dataList;
//        this.rowLayout = rowLayout;
//        this.context = context;
//        param=paramter;
//    }
    public Remark_Adapter(List<RemarkSQL> dataList1, int rowLayout, Context context,String paramter)
    {

        Log.e("Param","Param PUTTAr");
        this.dataList1 = dataList1;
        this.rowLayout = rowLayout;
        this.context = context;
        param=paramter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Log.e("Param",param);
        if(param.equalsIgnoreCase("inNetwork" ))
        {

            SQLDBHandler sql = new SQLDBHandler(context);
            holder.Title.setText(dataList1.get(position).getRemarkTitle());
            holder.Description.setText(dataList1.get(position).getRemarkData());
            holder.Date.setText(dataList1.get(position).getStrDate());
            sql.addRemark(new RemarkSQL(dataList1.get(position).getRemarkTitle(), dataList1.get(position).getRemarkData(), dataList1.get(position).getStrDate()));
        }
        else if(param.equalsIgnoreCase("notinNetwork"))
        {
            holder.Title.setText(dataList1.get(position).getRemarkTitle());
            holder.Description.setText(dataList1.get(position).getRemarkData());
            holder.Date.setText(dataList1.get(position).getStrDate());
        }
    }

    @Override
    public int getItemCount()
    {
        return dataList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout Layout;
        TextView Title;
        TextView Description;
        TextView Date;
        boolean attachment_present;

            ViewHolder(View v)
            {
            super(v);
            Layout = (LinearLayout) v.findViewById(R.id.remark_layout);
            Title = (TextView)v.findViewById(R.id.rm_title);
            Description = (TextView) v.findViewById(R.id.rm_description);
            Date= (TextView) v.findViewById(R.id.rm_date);
           }
    }
}
