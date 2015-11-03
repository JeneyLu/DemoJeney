package com.jeney.demojeney.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class NetWorkClient {
    public static StringNetWorkService stringNetWorkService;
    public static JsonNetWorkService jsonNetWorkService;

    public static void initNetWorkClient(String host){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(host)
                .setConverter(new StringCustomConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        stringNetWorkService = restAdapter.create(StringNetWorkService.class);

        RestAdapter jsonRestAdapter = new RestAdapter.Builder().setEndpoint(host)
                .setConverter(new CustomJsonConverter())
                .setRequestInterceptor(new RestRequestInterceptor())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        jsonNetWorkService = jsonRestAdapter.create(JsonNetWorkService.class);
    }
}
