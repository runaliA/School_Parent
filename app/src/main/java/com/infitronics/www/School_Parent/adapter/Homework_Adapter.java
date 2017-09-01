package com.infitronics.www.School_Parent.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.models.Get_Homework;
import com.infitronics.www.School_Parent.ui.HomeworkImageFragment;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.infitronics.www.School_Parent.R.id.imageView;


/**
 * Created by shashank on 27/2/17.
 */

public class Homework_Adapter extends RecyclerView.Adapter<Homework_Adapter.Homework_ViewHolder> {

    private List<Get_Homework.Data> dataList =new ArrayList<>();
    private int rowLayout;
    Context context;

     class Homework_ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout homeworkLayout;
        TextView hwTitle;
        TextView hwDescription;
        TextView hwDate;
        View divider;
        Button attachment_present;

        Homework_ViewHolder(View v) {
            super(v);
            homeworkLayout = (LinearLayout) v.findViewById(R.id.homework_layout);
            hwTitle = (TextView)v.findViewById(R.id.hw_title);
            hwDescription = (TextView) v.findViewById(R.id.hw_description);
            hwDate= (TextView) v.findViewById(R.id.hw_date);
            attachment_present=(Button)v.findViewById(R.id.btn_hw_attachment);
        }
    }

    public Homework_Adapter(List<Get_Homework.Data> dataList, int rowLayout, Context context) {
        this.dataList = dataList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Homework_Adapter.Homework_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new Homework_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Homework_ViewHolder holder,final int position) {
       if (dataList.size() > 0){
           Log.d(TAG, "new Homework: hey");
           holder.hwTitle.setText(dataList.get(position).getTitle());
           holder.hwDescription.setText(dataList.get(position).getDescription());
           holder.hwDate.setText(dataList.get(position).getDate());
           holder.attachment_present.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   String str=dataList.get(position).getFilePath();
//                   Toast.makeText(context, "Homework "+str, Toast.LENGTH_SHORT).show();
                   try {
                       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                       StrictMode.setThreadPolicy(policy);

                       URL url = new URL("http://"+str);
                         Bitmap bmp = null;

                       bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                  /****************Convert this bitmap tp imageview in dialog box
                   *
                   * first dailog box  then add image view to diaolg box *****************************/
                       Dialog dialog = new Dialog(context);
                       dialog.setContentView(R.layout.fragment_homework_image);
                       dialog.setTitle("Homework Image");
                       dialog.setCancelable(true);
                       ImageView img = (ImageView) dialog.findViewById(R.id.imgHomework);
                       img.setImageBitmap(bmp);
                       dialog.show();
                   } catch (Exception e) {
                       e.printStackTrace();
                       Log.e("EXCEPTION Imge" ,e.toString());
                   }

               }
           });

       }
       else{
           TextView tv= new TextView(context);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
           params.setMargins(2,2,2,2);
           tv.setLayoutParams(params);
           tv.setText("No Homework");
           tv.setAllCaps(true);
           tv.setTextSize(20);
           Log.d(TAG, "no Home: hello");
       }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
