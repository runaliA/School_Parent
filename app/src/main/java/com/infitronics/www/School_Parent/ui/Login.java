package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.infitronics.www.School_Parent.MainActivity;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.GetStudentDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    Button btnLogin;
    Call<GetStudentDetails> call;
    String Name=null,Class,AdmNo=null;



    EditText edtlogin,edtPass;

    private GetStudentDetails.Data[] detailData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        edtlogin=(EditText)findViewById(R.id.loginID);
        edtPass=(EditText)findViewById(R.id.passw);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*******************************************************CHECK EDITTEXT*************************************************************************************************************************/
Log.e(" USER PASS",edtlogin.getText().toString()+":"+edtPass.getText().toString());
//                if (edtlogin.getText().toString().equalsIgnoreCase("123") && edtPass.getText().toString().equalsIgnoreCase("pass@123"))
//                {


                    boolean flaginternet = false;
                    ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    if (connectivity != null) {
                        NetworkInfo[] info = connectivity.getAllNetworkInfo();
                        if (info != null)
                            for (int i = 0; i < info.length; i++)
                                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                                {
                                    flaginternet = true;
                                    break;
                                }

                    }

                    if (flaginternet) {
                        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                        call = apiInterface.getStuDetails(edtlogin.getText().toString(), edtPass.getText().toString());
                        getretrofitResponse();
                    }
                    else {
                        Toast.makeText(Login.this, "Plz check Internet Connectivity", Toast.LENGTH_SHORT).show();;
                        edtlogin.setText("");
                        edtPass.setText("");
                    }
//                }
//                else
//                    {
//                        Toast.makeText(Login.this, "Login?password Incoorect ", Toast.LENGTH_SHORT).show();
//                        edtlogin.setText("");
//                        edtPass.setText("");
//                }
            }
        });

    }

    public void getretrofitResponse()
    {
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(Login.this);
        progressDoalog.setMessage("Loading Data....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();


        call.enqueue(new Callback<GetStudentDetails>() {
            @Override
            public void onResponse(Call<GetStudentDetails> call, Response<GetStudentDetails> response)
            {
                progressDoalog.dismiss();
                Log.e("RESPONSE ",response.toString());
                String StrrespMsg=response.body().getMessage();
                Log.e("RESPONSE MSG LOGIN",StrrespMsg);
                if(StrrespMsg.contains("Invalid"))
                {
                    Toast.makeText(Login.this, "Login?password Incoorect ", Toast.LENGTH_SHORT).show();
                        edtlogin.setText("");
                        edtPass.setText("");
                }
                else {

                    detailData = response.body().getData();
                    Log.e("Data", detailData[0].toString());
                    Name = detailData[0].getName();
                    AdmNo=detailData[0].getAdmNo();
                    String studID=detailData[0].getId();
// Class=detailData[0].getClass();

                    SharedPreferences sp = getSharedPreferences("UserName", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("UserN", Name);
                    editor.putString("AdmissionNo",AdmNo);
                    editor.putString("StudentID",studID);
                    editor.commit();


                    Toast.makeText(Login.this, "Welcome  " + Name+studID+AdmNo, Toast.LENGTH_SHORT).show();
/*     AdmDate=detailData[0].getAdmissionDate();
       AdmNo=detailData[0].getAdmNo();
       Contact1=detailData[0].getContact1();
       Contact2=detailData[0].getContact2();
*/
              /*txtname.setText(Name);
                txtadmDate.setText(AdmDate);
                txtadmNo.setText(AdmNo);
                edtcontact.setText(Contact1);
                edtcontact2.setText(Contact2);*/

                    SharedPreferences settings = getSharedPreferences(Splash.PREFS_NAME, 0); // 0 - for private mode
                    SharedPreferences.Editor editor1 = settings.edit();

//Set "hasLoggedIn" to true
                    editor1.putBoolean("hasLoggedIn", true);

// Commit the edits!
                    editor1.commit();

                    Intent i = new Intent(Login.this, MainActivity.class);
                    i.putExtra("Nav_Name", Name);
                    startActivity(i);
                    finish();


                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }
}
