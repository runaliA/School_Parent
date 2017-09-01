package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_LeaveList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 28/4/17.
 */

public class Leave_Adapter extends RecyclerView.Adapter<Leave_Adapter.Leavenote_ViewHolder> {
    private List<Get_LeaveList.Data> dataList =new ArrayList<>();
    private int rowLayout;
    Context context;

    public Leave_Adapter(List<Get_LeaveList.Data> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Leavenote_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new Leavenote_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Leavenote_ViewHolder holder, int position)
    {
        holder.ntTitle.setText(dataList.get(position).getLv_Description());
        holder.ntDescription.setText(holder.ntDescription.getText()+" "+dataList.get(position).getStartDate());
        holder.ntDate.setText(dataList.get(position).getSubmittedDate());
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    class Leavenote_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout noticeLayout;
        TextView ntTitle;
        TextView ntDescription;
        TextView ntDate;
        boolean attachment_present;

        Leavenote_ViewHolder(View v) {
            super(v);
            noticeLayout = (LinearLayout) v.findViewById(R.id.leavenote_layout);
            ntTitle = (TextView)v.findViewById(R.id.nt_title);
            ntDescription = (TextView) v.findViewById(R.id.nt_description);
            ntDate= (TextView) v.findViewById(R.id.nt_date);

        }
    }

}
