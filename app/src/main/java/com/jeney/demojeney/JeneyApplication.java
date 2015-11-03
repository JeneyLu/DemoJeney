package com.jeney.demojeney;

import android.app.Application;

import com.jeney.demojeney.ORMLite.db.DatabaseHelper;
import com.jeney.demojeney.network.NetWorkClient;
import com.jeney.demojeney.util.imageloader.JeneyImageLoader;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class JeneyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络请求框架
        NetWorkClient.initNetWorkClient("http://www.baidu.com");
        //初始化数据库框架
        DatabaseHelper.getInstance(this);
        //初始化ImageLoad而
        JeneyImageLoader.init(getApplicationContext());
    }
}
