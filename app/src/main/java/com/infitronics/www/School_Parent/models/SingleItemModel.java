package com.infitronics.www.School_Parent.models;

/**
 * Created by Priyanka on 7/6/2017.
 */

public class SingleItemModel
{
    private String url;

    public SingleItemModel(String url)
    {
        this.url = url;
    }

    public SingleItemModel()
    {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
