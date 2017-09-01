package com.infitronics.www.School_Parent.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.infitronics.www.School_Parent.R;
import com.infitronics.www.School_Parent.utils.DialogUtils;


/**
 * Created by Shashank on 29-01-2017.
 */

public class Projectdemo_Fragment extends Fragment {
    View myview;
    WebView webView;
    public ProgressDialog mProgressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview= inflater.inflate(R.layout.fragment_projectdemo,container,false);
        webView= (WebView) myview.findViewById(R.id.web_projectdemo);
        webView.loadUrl("https://www.youtube.com/");
        return myview;
    }
}
