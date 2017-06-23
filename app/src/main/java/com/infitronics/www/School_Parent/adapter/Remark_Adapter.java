package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Notice;
import com.infitronics.www.School_Parent.models.Get_Remark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 20/3/17.
 */

public class Remark_Adapter extends RecyclerView.Adapter<Remark_Adapter.ViewHolder>  {

    private List<Get_Remark.Data> dataList =new ArrayList<>();
    private int rowLayout;
    Context context;

    public Remark_Adapter(List<Get_Remark.Data> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Title.setText(dataList.get(position).getTitle());
        holder.Description.setText(dataList.get(position).getDescription());
        holder.Date.setText(dataList.get(position).getStudent());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout Layout;
        TextView Title;
        TextView Description;
        TextView Date;
        boolean attachment_present;

            ViewHolder(View v) {
            super(v);
            Layout = (LinearLayout) v.findViewById(R.id.remark_layout);
            Title = (TextView)v.findViewById(R.id.rm_title);
            Description = (TextView) v.findViewById(R.id.rm_description);
            Date= (TextView) v.findViewById(R.id.rm_date);

        }
    }
}
