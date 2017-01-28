package com.infitronics.www.learn_gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by Shashank on 28-01-2017.
 */

public class Homework_Fragment extends Fragment {
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_homework,container,false);
        return myview;
    }
}
