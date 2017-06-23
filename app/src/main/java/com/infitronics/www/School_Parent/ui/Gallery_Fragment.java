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

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Gallery_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Gallery;
import com.infitronics.www.School_Parent.models.Get_Homework;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Gallery_Fragment extends Fragment {
    View myview;
    private Gallery_Adapter mGalleryAdapter;
    private List<Get_Gallery.Data> mygallery;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_gallery,container,false);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_gallery);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<Get_Gallery> call =apiservice.getGallery();
        mProgressDialog = DialogUtils.showProgressDialog(getActivity(),"Loading Data...");

        call.enqueue(new Callback<Get_Gallery>() {
            @Override
            public void onResponse(Call<Get_Gallery> call, Response<Get_Gallery> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    mygallery = response.body().getData();
                    mGalleryAdapter = new Gallery_Adapter(mygallery ,R.layout.list_item_gallery, getActivity());
                    mRecyclerView.setAdapter(mGalleryAdapter);
                    Log.e(TAG, "Number of Data received: " + mygallery);
                }
                else
                {
                    Log.d(TAG, "random error: "+response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Get_Gallery> call, Throwable t) {
                Log.e(TAG, t.toString());
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
        return myview;
    }
}
