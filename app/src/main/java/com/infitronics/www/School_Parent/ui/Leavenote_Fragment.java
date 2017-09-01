package com.infitronics.www.School_Parent.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.infitronics.www.School_Parent.CustomList;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.adapter.Homework_Adapter;
import com.infitronics.www.School_Parent.adapter.Leave_Adapter;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_LeaveList;
import com.infitronics.www.School_Parent.models.Model_Leavenote;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Shashank on 29-01-2017.
 */

public class Leavenote_Fragment extends Fragment {
    View myview;
    private Leave_Adapter mAdapter;
    private List<Get_LeaveList.Data> data;
    public RecyclerView mRecyclerView;
    public ProgressDialog mProgressDialog;
    FloatingActionButton add_note;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_leavenote,container,false);

        add_note= (FloatingActionButton) myview.findViewById(R.id.floatbtnAddLeave);


        SharedPreferences prefs = getActivity().getSharedPreferences("UserName", MODE_PRIVATE);
        String username = prefs.getString("UserN", "");
        String  str1=prefs.getString("StudentID","");

        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new Add_Leavenote_Fragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("tag");
                ft.commit();

            }
        });

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView = (RecyclerView) myview.findViewById(R.id.recycler_leave);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<Get_LeaveList> call = apiservice.getLeaveList(Integer.parseInt(str1),2);

        mProgressDialog = DialogUtils.showProgressDialog(getActivity(),"Loading Data...");
        call.enqueue(new Callback<Get_LeaveList>() {
            @Override
            public void onResponse(Call<Get_LeaveList> call, Response<Get_LeaveList> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    data = response.body().getData();


                    for(int i=0;i<data.size();i++)
                    {
                        Log.e("DATA  ",data.get(i).toString());
                    }
                    mAdapter = new Leave_Adapter(data, R.layout.list_item_leavenote, getActivity());
                    mRecyclerView.setAdapter(mAdapter);
                }
                else
                {
                    Log.d(TAG, "random error: ");
                }

            }

            @Override
            public void onFailure(Call<Get_LeaveList> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        return myview;
    }

}

