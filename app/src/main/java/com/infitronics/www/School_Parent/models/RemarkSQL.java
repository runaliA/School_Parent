package com.infitronics.www.School_Parent.models;

/**
 * Created by Priyanka on 7/13/2017.
 */

public class RemarkSQL
{
    int id;
    String RemarkTitle,strDate,RemarkData;

    public RemarkSQL(String remarkTitle, String remarkData, String strDate)
    {
        RemarkTitle = remarkTitle;
        this.strDate = strDate;
        RemarkData = remarkData;
    }


    public RemarkSQL()
    {
    }



    public String getRemarkTitle() {
        return RemarkTitle;
    }

    public void setRemarkTitle(String remarkTitle) {
        RemarkTitle = remarkTitle;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getRemarkData() {
        return RemarkData;
    }

    public void setRemarkData(String remarkData) {
        RemarkData = remarkData;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
