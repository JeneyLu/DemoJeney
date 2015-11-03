package com.jeney.demojeney.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * desc:InputStream转换成String的工具类
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/6
 */
public class StreamUtils {

    public static String readInputStream(InputStream inputStream ,String charset) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer,0,len);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        inputStream.close();
        byteArrayOutputStream.close();
        return new String(byteArray,charset);
    }

}
