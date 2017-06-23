package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.infitronics.www.School_Parent.CustomList;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Remark_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Remark;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Remark_Fragment extends Fragment {
    View myview;
    private Remark_Adapter mAdapter;
    private List<Get_Remark.Data> data;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_remark,container,false);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_remark);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<Get_Remark> call = apiservice.getRemark(1,3);

        mProgressDialog = DialogUtils.showProgressDialog(getActivity(),"Loading Data...");
        call.enqueue(new Callback<Get_Remark>() {
            @Override
            public void onResponse(Call<Get_Remark> call, Response<Get_Remark> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    data = response.body().getData();
                    mAdapter = new Remark_Adapter(data, R.layout.list_item_remark, getActivity());
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
            }
        });

        return myview;
    }
}

