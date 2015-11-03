package com.jeney.demojeney.network;

import retrofit.RestAdapter;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class NetWorkClient {
    public static StringNetWorkService stringNetWorkService;
    public static JsonNetWorkService jsonNetWorkService;

    public static void initNetWorkClient(String host) {
        RestAdapter stringRestAdapter = new RestAdapter.Builder().setEndpoint(host)
                .setConverter(new CustomStringConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        stringNetWorkService = stringRestAdapter.create(StringNetWorkService.class);

        RestAdapter jsonRestAdapter = new RestAdapter.Builder().setEndpoint(host)
                .setConverter(new CustomJsonConverter())
                .setRequestInterceptor(new RestRequestInterceptor())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        jsonNetWorkService = jsonRestAdapter.create(JsonNetWorkService.class);
    }
}
