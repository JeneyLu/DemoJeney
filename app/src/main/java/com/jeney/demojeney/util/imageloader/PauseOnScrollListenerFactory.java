
package com.jeney.demojeney.util.imageloader;

import android.widget.AbsListView.OnScrollListener;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

/**
 * PauseOnScrollListener工厂类 PauseOnScrollListener主要监听滑动和滚动操作
 * ，控制图片的加载过程，例如暂停图片加载，重新开始加载图片，
 * 一般使用在ListView,GridView中，滑动过程中暂停加载图片，停止滑动的时候去加载图片
 * 
 * @author jeney
 */
public class PauseOnScrollListenerFactory {

    /**
     * 创建PauseOnScrollListener，默认pauseOnScroll和pauseOnFling为true，
     * 也就是滚动列表或者手指没离开屏幕时不加载图片
     */
    public static PauseOnScrollListener createDefaultScrollListener() {
        return new PauseOnScrollListener(ImageLoader.getInstance(), true, true);
    }

    /**
     * 需要自己监听ListView,GridView等控件的OnScrollListener事件时使用
     * 
     * @param customListener
     * @return
     */
    public static PauseOnScrollListener createDefaultScrollListener(OnScrollListener customListener) {
        return new PauseOnScrollListener(ImageLoader.getInstance(), true, true, customListener);
    }

    public static PauseOnScrollListener createDefaultScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
        return new PauseOnScrollListener(ImageLoader.getInstance(), pauseOnScroll, pauseOnFling);
    }

}
