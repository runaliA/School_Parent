package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.GetStudentDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyAccount extends Fragment {

    EditText edtcontact,edtcontact2;
    TextView txtname,txtadmNo,txtadmDate,txtclassNo;


    private GetStudentDetails.Data[] detailData;

    String Name=null,AdmDate=null,AdmNo=null,Contact1=null,Contact2=null;

    Call<GetStudentDetails> call;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View accountView=inflater.inflate(R.layout.fragment_my_account, container, false);
        initializeview(accountView);

        ApiInterface apiInterface=ApiClient.getClient().create(ApiInterface.class);
        call=apiInterface.getStuDetails("123","pass@123");
        getretrofitResponse();
        return accountView;
    }

    public void initializeview(View viewID)
    {
        edtcontact=(EditText)viewID.findViewById(R.id.contact1);
        edtcontact2=(EditText)viewID.findViewById(R.id.contact2);
        txtname=(TextView)viewID.findViewById(R.id.txtName);
        txtadmNo=(TextView)viewID.findViewById(R.id.admNo);
        txtadmDate=(TextView)viewID.findViewById(R.id.admDt);
        txtclassNo=(TextView)viewID.findViewById(R.id.classno);
    }

    public void getretrofitResponse()
    {
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();


        call.enqueue(new Callback<GetStudentDetails>() {
            @Override
            public void onResponse(Call<GetStudentDetails> call, Response<GetStudentDetails> response)
            {
                progressDoalog.dismiss();
                Log.e("RESPONSE ",response.toString());

                detailData=response.body().getData();

                Name=detailData[0].getName();
                AdmDate=detailData[0].getAdmissionDate();
                AdmNo=detailData[0].getAdmNo();
                Contact1=detailData[0].getContact1();
                Contact2=detailData[0].getContact2();


                txtname.setText(Name);
                txtadmDate.setText(AdmDate);
                txtadmNo.setText(AdmNo);
                edtcontact.setText(Contact1);
                edtcontact2.setText(Contact2);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }
}
