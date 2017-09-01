package com.infitronics.www.School_Parent.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Add_Leave;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Add_Leavenote_Fragment extends Fragment {
        View myview;
EditText edtFromDate,edtEndDate,edt_desc;
    Button btnSubmit;
    private FragmentActivity myContext;

    private Calendar calendar;
    private int curYear, curMonth, curDay, year, month, day, age;
    Date datefrom = null;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            myview= inflater.inflate(R.layout.layout_add_leavenote,container,false);



            initializeView(myview);
            initializeCalender();
btnSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
        CallRetrofitWeb();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
});




            return myview;
        }

    private void initializeCalender() {
        calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH);
        curDay = calendar.get(Calendar.DAY_OF_MONTH);

        edtFromDate.setText(new StringBuilder().append(curYear).append("-").append(curMonth + 1).append("-").append(curDay).append(" "));
        edtEndDate.setText(new StringBuilder().append(curYear).append("-").append(curMonth + 1).append("-").append(curDay).append(" "));




        final DatePickerDialog.OnDateSetListener datePickerListenerfromDate
                = new DatePickerDialog.OnDateSetListener() {
            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                calendar.set(Calendar.YEAR, selectedYear);
                calendar.set(Calendar.MONTH, selectedMonth);
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                String myFormat = "dd-MMM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                edtFromDate.setText(sdf.format(calendar.getTime()));

            }
        };
        edtFromDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), datePickerListenerfromDate, curYear, curMonth,
                        curDay).show();
            }
        });




        final DatePickerDialog.OnDateSetListener datePickerListenerEndDate
                = new DatePickerDialog.OnDateSetListener() {
            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                calendar.set(Calendar.YEAR, selectedYear);
                calendar.set(Calendar.MONTH, selectedMonth);
                calendar.set(Calendar.DAY_OF_MONTH, selectedDay);
                String myFormat = "dd-MMM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

                edtEndDate.setText(sdf.format(calendar.getTime()));



            }
        };

        edtEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), datePickerListenerEndDate, curYear, curMonth,
                        curDay).show();
            }
        });
    }
    public void initializeView(View rootview)
    {
        edtFromDate=(EditText)rootview.findViewById(R.id.et_from_date);
        edtEndDate=(EditText)rootview.findViewById(R.id.et_to_date);
        edt_desc=(EditText)rootview.findViewById(R.id.et_description);
        btnSubmit=(Button)rootview.findViewById(R.id.btn_submit_leavenote);
    }

    public void CallRetrofitWeb() throws ParseException {

       /* Date str1=dateconv(edtFromDate.getText().toString());

        DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
        datefrom = df3.parse(edtFromDate.getText().toString());

        Date str2=dateconv(edtEndDate.getText().toString());
        Log.e("STRING DATE ",edtFromDate.getText().toString()+":"+edtEndDate.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            Log.e("Date Display ",formatter.format(str1));
        String from=formatter.format(datefrom);
        Log.e("Actual Date",from);
        String to=formatter.format(str2);*/




      /*  Date date1 = parseDate("21-Jun-2017", "dd-MMM-yyyy");

        Date date2 = parseDate("23-Jun-2017", "dd-MMM-yyyy");*/

      /*  try{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            date4 = format.parse("21-Jun-2017");
            Log.e("date value",""+date4);
        }
        catch (ParseException e)
        {
            Log.e("Exception " , e.toString());
            e.printStackTrace();
        }*/

//        Log.e("RESPONSE DAte ",formatter.format(str1)+" "+to  );


        SharedPreferences prefs = getActivity().getSharedPreferences("UserName", MODE_PRIVATE);
        String username = prefs.getString("UserN", "");
       String  str1=prefs.getString("StudentID","");
//        strAdmNo=prefs.getString("AdmissionNo","");

        Toast.makeText(getActivity(), "STUD ID "+str1, Toast.LENGTH_SHORT).show();

       ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
//        Add_Leave addleave=new Add_Leave("23-Jun-2017","23-Jun-2017",1087,"TP",0);
        Call call1=apiservice.insertLeave(edtFromDate.getText().toString(),edtEndDate.getText().toString(),Integer.parseInt(str1),edt_desc.getText().toString(),0);

        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response)
            {
                String str1=response.toString();
                Log.e("RESPONSE FROM LEAVE ",response.toString());
                Toast.makeText(getActivity(), "Leave has been Requested", Toast.LENGTH_SHORT).show();


//                Fragment fragment = new Leavenote_Fragment();
//                FragmentTransaction ft = myContext.getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, fragment);
//                ft.addToBackStack("tag");
//                ft.commit();
            }

            @Override
            public void onFailure(Call call, Throwable t)
            {
                Log.e("RESPONSE FROM FAILURE  ",t.toString());
            }
        });
    }

public Date dateconv(String strDate)
{
    Date date = null;
    Date convertedDate=null;



    try {
        DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
         date = df3.parse(strDate);


        System.out.println("Date in dd-MMM-yyyy format is: "+date+" **");
        Log.e("DATE STYLE ",date+" ");

    } catch (ParseException e) {
        e.printStackTrace();
    }
    return date;
}




    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
}
