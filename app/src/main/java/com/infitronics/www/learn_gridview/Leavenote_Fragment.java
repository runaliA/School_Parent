package com.infitronics.www.learn_gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Leavenote_Fragment extends Fragment {
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_leavenote,container,false);
        return myview;
    }
}
