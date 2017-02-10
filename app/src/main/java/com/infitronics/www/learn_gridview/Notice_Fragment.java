package com.infitronics.www.learn_gridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Shashank on 29-01-2017.
 */

public class Notice_Fragment extends Fragment {
    View myview;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_notice,container,false);

        String title[] =
                {
                        "Title",
                        "Title",
                        "Title",
                        "Title",
                        "Title",
                        "Title",
                };

        String msg[] = getResources().getStringArray(R.array.remark_list);
        CustomList customList = new CustomList(getActivity(), title, msg);

        listView = (ListView) myview.findViewById(R.id.notice_list);
        listView.setAdapter(customList);
        return myview;
    }
}
