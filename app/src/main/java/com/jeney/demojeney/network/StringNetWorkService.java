package com.jeney.demojeney.network;

import com.jeney.demojeney.model.RequestBody;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * desc:用StringCustomConverter的请求
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public interface StringNetWorkService {
    //请求参数会被encode
    //@Query传的参数跟在URL后
    @GET("/")
    void requestBaidu(@Query("param") String param, Callback<String> callback);

    //请求参数会被encode
    //@Query传的参数跟在URL后 @Field在body中
    @FormUrlEncoded
    @POST("/")
    void requestBaiduByPost(@Query("param") String param1,@Field("param") String param2, Callback<String> callback);

    //请求参数不会被encode
    @POST("/")
    void requestBaiduByPostBody(@Body Map<String,String> requestBody, Callback<String> callback);
}
