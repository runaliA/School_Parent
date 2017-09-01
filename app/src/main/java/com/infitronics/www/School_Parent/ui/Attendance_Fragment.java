package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.infitronics.www.School_Parent.Country;
import com.infitronics.www.School_Parent.MainActivity;
import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.api.ApiClient;
import com.infitronics.www.School_Parent.api.ApiInterface;
import com.infitronics.www.School_Parent.models.Get_Attendance;
import com.infitronics.www.School_Parent.utils.DialogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Shashank on 28-01-2017.
 */

public class Attendance_Fragment extends Fragment {
    View myview;
    Spinner spnMonth;
    Button btnCheck;
    TextView txtpresentday,txtmonth,txtpercentday;
    private Get_Attendance.Data[] monthlydata;
    public ProgressDialog mProgressDialog;
    String selectedMonth;
    ArrayList<String> arr_presentDay=new ArrayList<>();
    PieChart pie;
    String str1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_attendance, container, false);

        spnMonth=(Spinner)myview.findViewById(R.id.spinnerMonth);
        btnCheck=(Button)myview.findViewById(R.id.btnCheckMonth);
        txtpresentday=(TextView)myview.findViewById(R.id.txtpresentday);
        txtmonth=(TextView)myview.findViewById(R.id.txtmonthName);
        txtpercentday=(TextView)myview.findViewById(R.id.txtpercentday);
        pie=(PieChart)myview.findViewById(R.id.chart);

        SharedPreferences prefs = getActivity().getSharedPreferences("UserName", MODE_PRIVATE);
        String username = prefs.getString("UserN", "");
        str1=prefs.getString("StudentID","");
//        strAdmNo=prefs.getString("AdmissionNo","");

        Toast.makeText(getActivity(), "STUD ID "+str1, Toast.LENGTH_SHORT).show();
        /***************************************************************************/
        Calendar cal =  Calendar.getInstance();
        cal.add(Calendar.MONTH ,-1);
//format it to MMM-yyyy // January-2012
        String previousMonthYear  = new SimpleDateFormat("MMM-yyyy").format(cal.getTime());
        selectedMonth=previousMonthYear;

        /**************************************************************************/
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

        if (flaginternet)
        {
//              selectedMonth = spnMonth.getSelectedItem().toString();
                Log.e("Selected MONTH", selectedMonth);
                String strmont = selectedMonth.substring(0, 3);
                Log.e("Selected MONTH", strmont);
                CallRetrofitMonthlyData(strmont);
        }
        else
        {
            Toast.makeText(getActivity(), "Plz check Internet Connectivity", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(),Login.class);
            startActivity(i);
            getActivity().finish();


        }


        /**************************************************************************/



        btnCheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                arr_presentDay.clear();
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
                    selectedMonth = spnMonth.getSelectedItem().toString();
                    if (selectedMonth.equalsIgnoreCase("All Months")) {
                        Toast.makeText(getActivity(), "Please Select proper Month ", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Selected MONTH", selectedMonth);
                        String strmont = selectedMonth.substring(0, 3);
                        Log.e("Selected MONTH", strmont);
                        CallRetrofitMonthlyData(strmont);
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Plz check Internet Connectivity", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getActivity(),Login.class);
                    startActivity(i);
                    getActivity().finish();


                }
            }
        });


        return myview;
    }

    public void CalculateNDisplay()
    {
        Log.e("Monthly days",monthlydata.length+"");

        txtmonth.setText(selectedMonth);
        txtpresentday.setText((monthlydata.length-1)+"/"+arr_presentDay.size());
        float percentDays=arr_presentDay.size()/(monthlydata.length-1)*100;
        Log.e("PERCENT ",percentDays+"%");
        txtpercentday.setText(percentDays+"%");

        piechart();

    }

    public void piechart()
    {
        pie.setDrawHoleEnabled(true);

        pie.setHoleRadius(30f);
        pie.setTransparentCircleRadius(25f);//inner circle radius
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(arr_presentDay.size(), "Present days ->"+arr_presentDay.size()));//red
        entries.add(new PieEntry(((monthlydata.length-1)-arr_presentDay.size()), "Absent Days -> "+(monthlydata.length-1)));//yellow

        ArrayList<Integer>  colors =  new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.red));
        colors.add(getResources().getColor(R.color.blue));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(colors);
        set.setDrawValues(false);

//        set.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);  ///*******************to draw label aoutside peichart with arrow
//        set.setValueLinePart1OffsetPercentage(80.f);
//        set.setValueLinePart1Length(0.2f);
//        set.setValueLinePart2Length(0.4f);


        PieData data = new PieData(set);


        Legend l = pie.getLegend();
        l.setFormSize(8f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.SQUARE); // set what type of form/shape should be used
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        l.setXEntrySpace(2f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(7f); // set the space between the legend entries on the y-axis


        Description des = pie.getDescription();//used to remove description labels
        des.setEnabled(false);

        pie.setData(data);
        pie.setDrawSliceText(false);
//        pie.setEntryLabelColor(Color.BLACK);
//        pie.setEntryLabelTextSize(12f);
        pie.invalidate();
    }

    public void CallRetrofitMonthlyData(String MonthName)
    {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Get_Attendance> call = apiInterface.getAttendance(MonthName, Integer.parseInt(str1));
        mProgressDialog = DialogUtils.showProgressDialog(getActivity(), "Loading Data...");

        call.enqueue(new Callback<Get_Attendance>() {
            @Override
            public void onResponse(Call<Get_Attendance> call, Response<Get_Attendance> response) {

                mProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e("RESPONSE ", response.code() + "");
                    Log.e("RESPONSE FROM Attend ", response.message());
                    monthlydata = response.body().getData();
                    Log.e("Size of Array ", monthlydata.length + "");
                    for (int i = 0; i < monthlydata.length ; i++) 
		    {
                        String Date1 = monthlydata[i].getDate();
                        String Att = monthlydata[i].getAttendance();
     //                 Toast.makeText(getActivity(), "Attendance "+Date1+":"+Att, Toast.LENGTH_SHORT).show();

                        if (Att.equalsIgnoreCase("P")) 
			{
                            arr_presentDay.add(Date1);
                        }
                    }
                    CalculateNDisplay();
                }
            }


            @Override
            public void onFailure(Call<Get_Attendance> call, Throwable t) 
            {

                mProgressDialog.dismiss();
                Toast.makeText(getActivity(), "Unable to fetch Response ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

