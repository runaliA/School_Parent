package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.infitronics.www.School_Parent.CustomList;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Remark_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Remark;
import com.infitronics.www.School_Parent.models.RemarkSQL;
import com.infitronics.www.School_Parent.utils.DialogUtils;
import com.infitronics.www.School_Parent.utils.SQLDBHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Remark_Fragment extends Fragment  {
    View myview;
    private Remark_Adapter mAdapter;
    private List<Get_Remark.Data> data;
    private List<Get_Remark.Data> sqlData;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;
//    private SwipeRefreshLayout swipeRefreshLayout;
    boolean flagInternet=false;
    List<Get_Remark.Data> ArrSQL_allRemarkData;

    private List<RemarkSQL> dataList1 =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_remark,container,false);
//        swipeRefreshLayout = (SwipeRefreshLayout)  myview.findViewById(R.id.swipe_refresh_layout);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_remark);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        final SQLDBHandler db = new SQLDBHandler(getActivity());
//        swipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLDBHandler handler=new SQLDBHandler(getContext());
//                ArrSQL_allRemarkData=new ArrayList<>();
//                ArrSQL_allRemarkData=handler.allData();
//                for(int i=0;i<ArrSQL_allRemarkData.size();i++)
//                {
//                    Log.e("Detail Data00 ",ArrSQL_allRemarkData.get(i).getDescription());
//                }
//            }
//        });

/*************************
 *
 *
 *   First display data from sql table then on refresh data  check for network
 *   and display from server
 *
 *
 *
 * ******/

//        mRecyclerView.setAdapter(mAdapter);
       /* SQLDBHandler handler=new SQLDBHandler(getContext());
        ArrSQL_allRemarkData=new ArrayList<>();
        ArrSQL_allRemarkData=handler.allData();
        for(int i=0;i<ArrSQL_allRemarkData.size();i++)
        {
            Log.e("Detail Data00 ",ArrSQL_allRemarkData.get(i).getDescription());
        }*/

       ConnectivityManager conn=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if(conn != null)
        {
            NetworkInfo[] info=conn.getAllNetworkInfo();
            if(info != null)
            {
                for(int i=0;i<info.length;i++)
                {
                    if(info[i].getState()==NetworkInfo.State.CONNECTED)
                    {
                            flagInternet=true;
                            break;
                    }
                }
            }
        }
        if(flagInternet)
        {
            ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
            Call<Get_Remark> call = apiservice.getRemark(1, 3);

            mProgressDialog = DialogUtils.showProgressDialog(getActivity(), "Loading Data...");
            call.enqueue(new Callback<Get_Remark>() {
                @Override
                public void onResponse(Call<Get_Remark> call, Response<Get_Remark> response) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.isSuccessful())
                    {
                        db.deletetablerow();
                        data = response.body().getData();

                        for(int i=0;i<data.size();i++)
                        {
                            String title=data.get(i).getTitle();
                            String desc=data.get(i).getDescription();
                            String date=data.get(i).getStudent();
                            RemarkSQL sql1=new RemarkSQL(title,desc,date);
                            dataList1.add(sql1);
                            Log.e("Hiii","Hello");
                        }

                        mAdapter = new Remark_Adapter(dataList1, R.layout.list_item_remark, getActivity(),"inNetwork");

                        mRecyclerView.setAdapter(mAdapter);

                    }
                    else
                        {
                        Log.d(TAG, "random error: ");
                    }

                }

                @Override
                public void onFailure(Call<Get_Remark> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    mProgressDialog.dismiss();
                    Toast.makeText(getActivity(), "Unable to fetch Response ", Toast.LENGTH_SHORT).show();
                }
            });
        }

        else/**********      When no network        *******/
        {
            if(dataList1.size()<0)
            {
                Toast.makeText(getActivity(), "No Data to Display.." +
                        "Please Check internet Connection", Toast.LENGTH_SHORT).show();
            }
            else {

                dataList1.clear();
                try {
                    db.open();

                    Cursor c = db.getAllContacts();
                    if (c.moveToFirst()) {
                        do {


                            DisplayContact(c);
                        } while (c.moveToNext());

                        mAdapter = new Remark_Adapter(dataList1, R.layout.list_item_remark, getActivity(), "notinNetwork");
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return myview;
    }

//    @Override
//    public void onRefresh()
//    {
//        Toast.makeText(getActivity(), "On Reshresh", Toast.LENGTH_SHORT).show();
//
//    }

    private void DisplayContact(Cursor c)
    {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(),"id: " + c.getString(0) +": "+c.getString(1)+":"+c.getString(2),Toast.LENGTH_LONG).show();

        RemarkSQL sql1=new RemarkSQL(c.getString(0),c.getString(1),c.getString(2));
        dataList1.add(sql1);

//        mAdapter = new Remark_Adapter(dataList1, R.layout.list_item_remark,"notinNetwork");
//        mRecyclerView.setAdapter(mAdapter);
    }
}

