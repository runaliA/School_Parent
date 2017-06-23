package com.infitronics.www.School_Parent.ui;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.infitronics.www.School_Parent.Country;
import com.infitronics.www.School_Parent.R;

import java.util.ArrayList;

/**
 * Created by Shashank on 28-01-2017.
 */

public class Attendance_Fragment extends Fragment {
    View myview;

       @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fragment_attendance, container, false);

        return myview;
    }



}