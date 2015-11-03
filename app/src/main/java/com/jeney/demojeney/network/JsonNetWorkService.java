package com.jeney.demojeney.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.jeney.demojeney.model.RequestBody;

/**
 * desc:用CustomJsonConverter的请求
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public interface JsonNetWorkService {
    //请求参数会被ncode
    @GET("/")
    void requestBaidu(@Query("param") String param, Callback<String> callback);

    //请求参数会被encode
    //@Query传的参数跟在URL后 @Field在body中
    @FormUrlEncoded
    @POST("/")
    void requestBaiduByPost(@Field("param1") String param1,@Field("param2") String param2, Callback<String> callback);

    //请求参数不会被encode
    @POST("/")
    void requestBaiduByPostBody(@Body RequestBody requestBody, Callback<String> callback);
}
