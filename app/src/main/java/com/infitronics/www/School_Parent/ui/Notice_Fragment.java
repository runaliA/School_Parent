package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.google.gson.reflect.TypeToken;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Notice_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Notice;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Notice_Fragment extends Fragment {
    View myview;
    private Notice_Adapter mAdapter;
    private List<Get_Notice.Data> data;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_notice, container, false);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_notice);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);

        boolean flaginternet = false;
        ConnectivityManager connectivity = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        flaginternet = true;
                        break;
                    }

        }

        if (flaginternet) {
            ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
            Call<Get_Notice> call = apiservice.getNotice();

            mProgressDialog = DialogUtils.showProgressDialog(getActivity(), "Loading Data...");
            call.enqueue(new Callback<Get_Notice>() {
                @Override
                public void onResponse(Call<Get_Notice> call, Response<Get_Notice> response) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    if (response.isSuccessful()) {
                        data = response.body().getData();
                        for (int i = 0; i < data.size(); i++) {
                            Log.e("Notice Data ", data.get(i).toString());
                        }
                        mAdapter = new Notice_Adapter(data, R.layout.list_item_notice, getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        Log.d(TAG, "random error: ");
                    }

                }

                @Override
                public void onFailure(Call<Get_Notice> call, Throwable t) {
                    Log.e(TAG, t.toString());
                    mProgressDialog.dismiss();
                    Toast.makeText(getActivity(), "Unable to fetch Response ", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(getActivity(), "Plz check Internet Connectivity", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(),Login.class);
            getActivity().finish();


        }
        return myview;
    }
}
