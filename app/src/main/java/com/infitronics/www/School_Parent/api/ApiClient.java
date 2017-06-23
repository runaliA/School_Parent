package com.infitronics.www.School_Parent.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shashank on 27/2/17.
 */

public class ApiClient {
    /*public static final String BASE_URL = "http://2bnamo.com/radio_admin/api/";*/
    public static final String BASE_URL = "http://ewole.in/eSchool-WebService/eSchoolWS.asmx/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
