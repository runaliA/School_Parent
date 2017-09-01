package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.support.v7.widget.LinearLayoutManager;
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
    final int THUMBSIZE = 64;

    Context context;



    class Gallery_ViewHolder extends RecyclerView.ViewHolder {


        TextView gaTitle;
        TextView gaDescription;
        TextView gaDate;
        protected RecyclerView recycler_view_list;


        Gallery_ViewHolder(View v) {
            super(v);

            gaDate= (TextView) v.findViewById(R.id.ga_date);
            gaTitle= (TextView) v.findViewById(R.id.ga_title);
            gaDescription= (TextView) v.findViewById(R.id.ga_description);
            this.recycler_view_list = (RecyclerView) v.findViewById(R.id.recycler_view_list);

        }
    }


    public Gallery_Adapter(List<Get_Gallery.Data> dataList, int rowLayout, Context context)
    {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Gallery_Adapter.Gallery_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new Gallery_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Gallery_ViewHolder holder,final int position) {
        holder.gaTitle.setText(dataList.get(position).getTitle());
        holder.gaDescription.setText(dataList.get(position).getDescription());
        int image_size=dataList.get(position).getLstGallery().size();
        Log.e("Image Size "," +" + image_size);
Img_list=dataList.get(position).getLstGallery();
//       xyz= dataList.get(position).getLstGallery();

        GalleryDataItemList itemListDataAdapter = new GalleryDataItemList(Img_list ,R.layout.single_gallery_image,context);
//
        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);


//        holder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }

    @Override
    public int getItemViewType(int position) {


        return dataList.size();
    }
}
