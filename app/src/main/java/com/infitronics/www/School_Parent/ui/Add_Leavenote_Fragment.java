package com.infitronics.www.School_Parent.ui;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Add_Leave;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add_Leavenote_Fragment extends Fragment {
        View myview;
EditText edtFromDate,edtEndDate;

    private Calendar calendar;
    private int curYear, curMonth, curDay, year, month, day, age;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            myview= inflater.inflate(R.layout.layout_add_leavenote,container,false);


            try {
            initializeView(myview);
            initializeCalender();


                CallRetrofitWeb();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return myview;
        }

    private void initializeCalender() {
        calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH);
        curDay = calendar.get(Calendar.DAY_OF_MONTH);

        edtFromDate.setText(new StringBuilder().append(curYear).append("-").append(curMonth + 1).append("-").append(curDay).append(" "));
        edtEndDate.setText(new StringBuilder().append(curYear).append("-").append(curMonth + 1).append("-").append(curDay).append(" "));




        final DatePickerDialog.OnDateSetListener datePickerListener
                = new DatePickerDialog.OnDateSetListener() {
            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                age = curYear - year;
                if (curMonth < month || (month == curMonth && curDay < day)) {
                    age--;
                }

//                input_dob.setText(new StringBuilder().append(year)
//                        .append("-").append(month + 1).append("-").append(day)
//                        .append(" "));
//                if (dobFlag.equalsIgnoreCase("1")) {
                edtEndDate.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
//                } else if (dobFlag.equalsIgnoreCase("2")) {
//                    viewdob.setText(new StringBuilder().append(day).append("-").append(month + 1).append("-").append(year).append(" "));
//                }
            }
        };


        edtEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), datePickerListener, curYear, curMonth,
                        curDay).show();
            }
        });
    }
    public void initializeView(View rootview)
    {
        edtFromDate=(EditText)rootview.findViewById(R.id.et_from_date);
        edtEndDate=(EditText)rootview.findViewById(R.id.et_to_date);

    }

    public void CallRetrofitWeb() throws ParseException {
        Date date4 = null;
//        Date str1=dateconv("21-Jun-2017");
//        Date str2=dateconv("25-Jun-2017");

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

//        Log.e("RESPONSE DAte ",date1+" "+date2  );
        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
//        Add_Leave addleave=new Add_Leave("23-Jun-2017","23-Jun-2017",1087,"TP",0);
        Call call1=apiservice.insertLeave("09-Jun-2017","19-Jun-2017",1087,"Test Runa",0);

        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response)
            {
                String str1=response.toString();
                Log.e("RESPONSE FROM LEAVE ",response.toString());


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
        DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");
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
