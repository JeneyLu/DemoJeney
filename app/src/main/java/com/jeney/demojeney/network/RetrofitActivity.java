package com.jeney.demojeney.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeney.demojeney.R;
import com.jeney.demojeney.model.RequestBody;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    //get方法请求网络，StringCustomConverter
    @OnClick(R.id.requestNetworkGetString_Btn)
    public void requestNetworkGetString() {
        NetWorkClient.stringNetWorkService.requestBaidu("hello,baidu", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
    }

    //post方法请求网络，StringCustomConverter
    @OnClick(R.id.requestNetworkPostString_Btn)
    public void requestNetworkPostString() {
        //body为param=hello%2Cbaidu
        NetWorkClient.stringNetWorkService.requestBaiduByPost("你好，百度", "hello,baidu", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        });
    }

    //post方法请求网络，StringCustomConverter
    @OnClick(R.id.requestNetworkPostBodyString_Btn)
    public void requestNetworkPostBodyString() {
        //body为{param=你好，BaiDu}
        HashMap requestBody = new HashMap();
        requestBody.put("param", "你好，BaiDu");
        NetWorkClient.stringNetWorkService.requestBaiduByPostBody(requestBody, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                //Log.d(TAG, s);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //Log.d(TAG, retrofitError.getMessage());
            }
        });
    }

    //get方法请求网络,CustomJsonConverter
    @OnClick(R.id.requestNetworkGetJson_Btn)
    public void requestGetJson() {
        NetWorkClient.jsonNetWorkService.requestBaidu("hello,baidu", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                //Log.d(TAG, s);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //Log.d(TAG, retrofitError.getMessage());
            }

        });
    }

    //post方法请求网络,CustomJsonConverter
    @OnClick(R.id.requestNetworkPostJson_Btn)
    public void requestPostJson() {
        NetWorkClient.jsonNetWorkService.requestBaiduByPost("你好，百度", "hello,baidu", new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                //Log.d(TAG, s);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //Log.d(TAG, retrofitError.getMessage());
            }
        });
    }

    //post方法请求网络,CustomJsonConverter
    @OnClick(R.id.requestNetworkPostJsonBody_Btn)
    public void requestPostJsonBody() {
        NetWorkClient.jsonNetWorkService.requestBaiduByPostBody(new RequestBody("你好,baidu"), new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                //Log.d(TAG, s);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //Log.d(TAG, retrofitError.getMessage());
            }
        });
    }

}
