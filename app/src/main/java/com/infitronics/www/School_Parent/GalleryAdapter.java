package com.infitronics.www.School_Parent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Shashank on 10-02-2017.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context mContext;

    public GalleryAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(image[position]);
        return imageView;
    }

    int image[] = {R.drawable.attendance, R.drawable.homework, R.drawable.noticeboard, R.drawable.timetable, R.drawable.result, R.drawable.remark};
}
