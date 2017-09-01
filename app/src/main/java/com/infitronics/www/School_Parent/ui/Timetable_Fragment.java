package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.greasemonk.timetable.TimeTable;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.GetTimeTable;
import com.infitronics.www.School_Parent.models.Get_Notice;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Timetable_Fragment extends Fragment {

    private static final int GENERATED_AMOUNT = 6;

    TableLayout tblData;
    public ProgressDialog mProgressDialog;
    private GetTimeTable.Data[] get_TimetableData;
    private GetTimeTable.Data.Timetable[] get_columnData;

    View myview = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) 
    {
        myview= inflater.inflate(R.layout.fragment_timetable,container,false);
        tblData=(TableLayout)myview.findViewById(R.id.tblTimetable);


        ApiInterface apiIterfaceTimetable= ApiClient.getClient().create(ApiInterface.class);
        Call<GetTimeTable> call=apiIterfaceTimetable.getTimetable(15);
        mProgressDialog = DialogUtils.showProgressDialog(getActivity(),"Loading Data...");


        call.enqueue(new Callback<GetTimeTable>() {
            @Override
            public void onResponse(Call<GetTimeTable> call, Response<GetTimeTable> response) {
                if(response.isSuccessful()) {
                    Log.e("Respoanse Table ", response.toString() + "");
                    mProgressDialog.dismiss();
                    get_TimetableData = response.body().getData();
                    if (get_TimetableData.length > 0) {
                        for (int i = 0; i < 1; i++) {
                            get_columnData = get_TimetableData[i].getTimetable();
//                        TableRow header=new TableRow(getActivity());
//                        TextView tv1 = new TextView(getActivity());
//
//                        tv1.setText("Date/Time");
//                        header.addView(tv1);


                            TableRow row = new TableRow(getActivity());
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            row.setPadding(5,7,5,7);
                            row.setLayoutParams(lp);

                            TextView tv1 = new TextView(getActivity());

                            tv1.setText("Day/Time");
                            tv1.setPadding(4, 3, 3, 3);
                            row.addView(tv1);
                            for (int j = 0; j < get_columnData.length; j++) {

                                TextView tv = new TextView(getActivity());

                                tv.setText(get_columnData[j].getSTime());
                                tv.setPadding(4, 4, 4, 4);
                                row.addView(tv);

                            }
//                        tblData.addView(header);
                            tblData.addView(row);
                        }


/*
                  for(int tbData=0;tbData < get_TimetableData.length;tbData++) {
                        TableRow row2 = new TableRow(getActivity());
                      row2.removeAllViews();
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                        row2.setLayoutParams(lp);
                      TextView tv3 = new TextView(getActivity());

                      tv3.setText(get_TimetableData[tbData].getDay());
                      row2.addView(tv3);

                      tblData.addView(row2);



                      for (int z = 0; z < get_columnData.length; z++) {

                          TextView tv2 = new TextView(getActivity());

                            tv2.setText(get_columnData[z].getSubject()+"12");
                            row2.addView(tv2);
                            tblData.addView(row2);
                        }
                  }

*/


                        for (int i = 0; i < get_TimetableData.length; i++) {
                            get_columnData = get_TimetableData[i].getTimetable();


                            TableRow row = new TableRow(getActivity());
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            row.setLayoutParams(lp);
                            TextView tv1 = new TextView(getActivity());

                            tv1.setText(get_TimetableData[i].getDay());
                            tv1.setPadding(4, 4, 4, 4);
                            row.addView(tv1);
                            for (int j = 0; j < get_columnData.length; j++) {

                                TextView tv = new TextView(getActivity());

                                tv.setText(get_columnData[j].getSubject() + "*");
                                tv.setPadding(4, 4, 4, 4);
                                row.addView(tv);

                            }
                            if (i % 2 == 0) {
                                row.setBackgroundColor(Color.GRAY);
                            } else {
                                row.setBackgroundColor(Color.LTGRAY);
                            }
//                        tblData.addView(header);
                            tblData.addView(row);
                        }


                    }
                    else
                    {
                        Toast.makeText(getActivity(), "No Record Updated for Selected Class", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
            }

            @Override
            public void onFailure(Call<GetTimeTable> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Unable to fetch Response ", Toast.LENGTH_SHORT).show();
            }
        });
        return myview;
    }


}