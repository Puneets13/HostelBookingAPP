package com.example.hostelappnitj;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //    connect laptop wwith the Hostpot of the phone  and on wifi clicking change it to provate discoverable mode
//    use the current ip address and start the server before and the set the ip address in the mongodb and
//    use CMD ipconfig to get the ip address and then set the WIFI LAN ADAPTER Wifi IPV$ address
    private static String BASE_URL = "https://api-hostelmanagement-nitjhostels.onrender.com/nitj_hostels/";
    private static Retrofit retrofit;
    private static RetrofitClient retrofitClient;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
