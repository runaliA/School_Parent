package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Homework_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Homework;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Shashank on 28-01-2017.
 */

public class Homework_Fragment extends Fragment {
    View myview;
    private Homework_Adapter mAdapter;
    private List<Get_Homework.Data> data;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;
    String str1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_homework,container,false);


        SharedPreferences prefs = getActivity().getSharedPreferences("UserName", MODE_PRIVATE);
        String username = prefs.getString("UserN", "");
        str1=prefs.getString("StudentID","");
//        strAdmNo=prefs.getString("AdmissionNo","");

        Toast.makeText(getActivity(), "STUD ID "+str1, Toast.LENGTH_SHORT).show();


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_homework);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<Get_Homework> call =apiservice.getHomework(Integer.parseInt(str1));


        mProgressDialog = DialogUtils.showProgressDialog(getActivity(),"Loading Data...");
        call.enqueue(new Callback<Get_Homework>()
        {
            @Override
            public void onResponse(Call<Get_Homework> call, Response<Get_Homework> response)
            {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.isSuccessful()){
                    Log.e("Data ",response.body().toString());
                    data = response.body().getData();
                    if(data.size()>0) {
                        mAdapter = new Homework_Adapter(data,R.layout.list_item_homework,getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "No Homework to Display", Toast.LENGTH_SHORT).show();
                       /* TextView tv= new TextView(getApplicationContext());
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
                        params.setMargins(2,2,2,2);
                        tv.setLayoutParams(params);
                        tv.setText("NoDATA");
                        tv.setAllCaps(true);
                        tv.setTextSize(20);*/
                        Log.d(TAG, "NoDATA");
                    }

                }
                else{
                    Log.d(TAG, "random error: ");
                }
            }

            @Override
            public void onFailure(Call<Get_Homework> call, Throwable t) {
                Log.e(TAG, t.toString());
                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Unable to fetch Response ", Toast.LENGTH_SHORT).show();
            }
        });

        return myview;
    }
}
