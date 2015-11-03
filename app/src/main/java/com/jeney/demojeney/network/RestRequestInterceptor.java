package com.jeney.demojeney.network;

import retrofit.RequestInterceptor;

/**
 * desc:用于给请求统一地加上一些header或参数
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/9/6
 */
public class RestRequestInterceptor implements RequestInterceptor{
    @Override
    public void intercept(RequestFacade requestFacade) {
        requestFacade.addHeader("headerFrom","RestRequestInterceptor");
        requestFacade.addQueryParam("queryParamFrom","RestRequestInterceptor中文");
    }
}
