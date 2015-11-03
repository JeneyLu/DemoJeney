package com.jeney.demojeney.util;

import android.content.Context;
import android.widget.Toast;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/9/6
 */
public class ToastUtil {
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
