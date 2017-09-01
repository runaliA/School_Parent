package com.infitronics.www.School_Parent.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Notice;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shashank on 16/3/17.
 */

public class Notice_Adapter extends RecyclerView.Adapter<Notice_Adapter.Notice_ViewHolder> {

    private List<Get_Notice.Data> dataList =new ArrayList<>();
    private int rowLayout;
    Context context;

    public Notice_Adapter(List<Get_Notice.Data> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Notice_Adapter.Notice_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new Notice_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Notice_ViewHolder holder, final int position) {
        holder.ntTitle.setText(dataList.get(position).getNt_Title());
        holder.ntDescription.setText(dataList.get(position).getNt_Description());
        holder.ntDate.setText(dataList.get(position).getNt_NoticeDate());
        final String str=dataList.get(position).getNt_Filepath();
        Log.e("LENGTH_SHORT ",str.length()+"****");
if(str.length()>0)
{
    holder.noticeAttached.setVisibility(View.VISIBLE);

    holder.noticeAttached.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Toast.makeText(context, "Homework "+str, Toast.LENGTH_SHORT).show();
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);

                URL url = new URL(str);
                Bitmap bmp = null;

                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                /****************Convert this bitmap tp imageview in dialog box
                 *
                 * first dailog box  then add image view to diaolg box *****************************/
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.fragment_homework_image);
                dialog.setTitle("Notice");
                dialog.setCancelable(true);
                ImageView img = (ImageView) dialog.findViewById(R.id.imgHomework);
                img.setImageBitmap(bmp);
                dialog.show();
            } catch (Exception e)
            {
                e.printStackTrace();
                Log.e("EXCEPTION Imge" ,e.toString());
            }

        }
    });
}

            }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

     class Notice_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout noticeLayout;
        TextView ntTitle;
        TextView ntDescription;
        TextView ntDate;
        boolean attachment_present;
         Button noticeAttached;

        Notice_ViewHolder(View v) {
            super(v);
            noticeLayout = (LinearLayout) v.findViewById(R.id.notice_layout);
            ntTitle = (TextView)v.findViewById(R.id.nt_title);
            ntDescription = (TextView) v.findViewById(R.id.nt_description);
            ntDate= (TextView) v.findViewById(R.id.nt_date);
noticeAttached=(Button)v.findViewById(R.id.btn_nt_attachment);
        }
    }
}
