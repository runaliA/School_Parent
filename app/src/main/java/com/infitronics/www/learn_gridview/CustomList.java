package com.infitronics.www.learn_gridview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Shashank on 29-01-2017.
 */

public class CustomList extends ArrayAdapter<String> {

    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private Activity context;

    public CustomList(Activity context, String[] names, String[] desc) {
        super(context, R.layout.remark_singleitem, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        //this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.remark_singleitem, null, true);
        TextView title = (TextView) listViewItem.findViewById(R.id.rtitle);
        TextView msg = (TextView) listViewItem.findViewById(R.id.rmsg);
        // ImageView image = (ImageView) listViewItem.findViewById(R.id.messageicon);

        title.setText(names[position]);
        msg.setText(desc[position]);
        // image.setImageResource(imageid[position]);
        return listViewItem;
    }
}
