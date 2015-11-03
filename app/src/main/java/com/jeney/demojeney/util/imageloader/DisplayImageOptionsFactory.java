package com.jeney.demojeney.util.imageloader;

import android.graphics.Bitmap;
import com.jeney.demojeney.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * DisplayImageOptions工厂类
 * 
 * @author jeney
 */
public class DisplayImageOptionsFactory {

    public static DisplayImageOptions createBasicImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        return options;
    }
    
    public static DisplayImageOptions createBasicImageOptionsByImageResource(int imageRes) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createBasicImageOptions())
                .showImageOnLoading(imageRes)
                .showImageOnFail(imageRes)
                .showImageForEmptyUri(imageRes)
                .build();

        return options;
    }

    /**
     * 创建普通的DisplayImageOptions，用于列表、大图等模块的图片显示
     * 
     * @return
     */
    public static DisplayImageOptions createOrdinaryImageOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createBasicImageOptions())
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .build();

        return options;
    }

    public static DisplayImageOptions createCustomImageOptions(int imageId) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createBasicImageOptions())
                .showImageOnLoading(imageId)
                .showImageOnFail(imageId)
                .showImageForEmptyUri(imageId)
                .build();

        return options;
    }

    /**
     * 创建普通的DisplayImageOptions，用于列表、大图等模块的图片显示
     * 
     * @param delayInMillis 延迟加载时间
     * @return
     */
    public static DisplayImageOptions createOrdinaryImageOptions(int delayInMillis) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createOrdinaryImageOptions())
                .delayBeforeLoading(delayInMillis)
                .build();
        return options;
    }


    /**
     * 创建经纪人头像的DisplayImageOptions，用于经纪人头像显示
     * 
     * @return
     */
    public static DisplayImageOptions createBrokerImageOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createBasicImageOptions())
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .build();

        return options;
    }

    /**
     * 创建经纪人头像的DisplayImageOptions，用于经纪人头像显示
     * 
     * @return
     */
    public static DisplayImageOptions createBrokerImageOptions(int delayInMillis) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cloneFrom(createBrokerImageOptions())
                .delayBeforeLoading(delayInMillis)
                .build();
        return options;
    }

}
