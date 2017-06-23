package com.infitronics.www.School_Parent.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by shashank on 27/2/17.
 */

public class DialogUtils {
    public static ProgressDialog showProgressDialog(Context context, String text){
        ProgressDialog m_Dialog = new ProgressDialog(context);
        m_Dialog.setMessage(text);
        m_Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        m_Dialog.setCancelable(false);
        m_Dialog.show();
        return m_Dialog;
    }

}
