package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Gallery;
import com.infitronics.www.School_Parent.models.Get_Homework;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by shashank on 27/2/17.
 */

public class Gallery_Adapter extends RecyclerView.Adapter<Gallery_Adapter.Gallery_ViewHolder> {

    private List<Get_Gallery.Data> dataList =new ArrayList<>();
    private List<Get_Gallery.Data.LstGallery> Img_list =new ArrayList<>();
    private int rowLayout;

    Context context;



    class Gallery_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout galleryLayout;
        LinearLayout row_1;
        LinearLayout row_2;
        TextView gaTitle;
        TextView gaDescription;
        TextView gaDate;

        Gallery_ViewHolder(View v) {
            super(v);
            galleryLayout= (LinearLayout) v.findViewById(R.id.gallery_layout);
            row_1= (LinearLayout) v.findViewById(R.id.gallerow_1);
            row_2= (LinearLayout) v.findViewById(R.id.gallerow_2);
            gaTitle= (TextView) v.findViewById(R.id.ga_title);
            gaDescription= (TextView) v.findViewById(R.id.ga_description);
            gaDate= (TextView) v.findViewById(R.id.ga_date);

        }
    }


    public Gallery_Adapter(List<Get_Gallery.Data> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Gallery_Adapter.Gallery_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new Gallery_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Gallery_ViewHolder holder,final int position) {
        holder.gaTitle.setText(dataList.get(position).getTitle());
        holder.gaDescription.setText(dataList.get(position).getDescription());
        int image_size=dataList.get(position).getLstGallery().size();
        if (image_size > 3){
            for(int i=0 ;i < image_size;i++){
               if (i < 3)
               {
                   ImageView iv = new ImageView(context);
                   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                   params.setMargins(2,2,2,2);
                   iv.setLayoutParams(params);
                   iv.setScaleType(ImageView.ScaleType.FIT_XY);
                   holder.row_1.addView(iv);
                   Picasso.with(context).load(dataList.get(position).getLstGallery().get(i).getImageUrl())
                           .resize(280,250)
                           .placeholder(R.drawable.placeholder)
                           .error(R.drawable.no_image)
                           .into(iv);
               }
               else
               {
                   ImageView iv = new ImageView(context);
                   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                   params.setMargins(2,2,2,2);
                   iv.setLayoutParams(params);
                   iv.setScaleType(ImageView.ScaleType.FIT_XY);
                   holder.row_2.addView(iv);
                   Picasso.with(context).load(dataList.get(position).getLstGallery().get(i).getImageUrl())
                           .resize(280, 250)
                           .placeholder(R.drawable.placeholder)
                           .error(R.drawable.no_image)
                           .into(iv);
               }
            }
        }
        else
        {
            for(int i=0 ;i < image_size  ;i++){
                ImageView iv = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                params.setMargins(2,2,2,2);
                iv.setLayoutParams(params);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                holder.row_1.addView(iv);
                Picasso.with(context).load(dataList.get(position).getLstGallery().get(i).getImageUrl())
                        .resize(280, 250)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image)
                        .into(iv);
            }
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();

    }

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }
}
