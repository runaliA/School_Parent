package com.infitronics.www.School_Parent.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Gallery;
import com.infitronics.www.School_Parent.models.SingleItemModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyanka on 7/6/2017.
 */

public class GalleryDataItemList extends RecyclerView.Adapter<GalleryDataItemList.SingleItemHolder>
{
    private List<Get_Gallery.Data.LstGallery> itemsList;
    private int rowLayout;
    private Context mContext;


    public GalleryDataItemList(List<Get_Gallery.Data.LstGallery> dataList, int rowLayout,Context mContext)
    {
        this.itemsList = dataList;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
        Log.e("SINGLE ITEM","ITEM");
    }

    @Override
    public GalleryDataItemList.SingleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        SingleItemHolder mh = new SingleItemHolder(v);
        Log.e("SINGLE ITEM11","ITEM11");
        return mh;
    }

    @Override
    public void onBindViewHolder(GalleryDataItemList.SingleItemHolder holder, int position)
    {
//        SingleItemModel single=itemsList.get(position);

        final String str=itemsList.get(position).getImageUrl();

        Log.e("URL ",str+"****");

       if(str.length()>0)
        {
            try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            URL url = null;

                url = new URL(str);

            Bitmap bmp = null;

            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.itemImage.setImageBitmap(bmp);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {

        }

    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemHolder extends RecyclerView.ViewHolder {

        protected ImageView itemImage;

        public SingleItemHolder(View itemView) {
            super(itemView);
            Log.e("SINGLE ITEM 222","ITEM222");
            this.itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
        }
    }
}
