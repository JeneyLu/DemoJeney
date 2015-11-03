package com.jeney.demojeney.comm.banner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jeney.demojeney.R;
import com.jeney.demojeney.util.ToastUtil;
import com.jeney.demojeney.util.UIUtils;
import com.jeney.demojeney.util.imageloader.DisplayImageOptionsFactory;
import com.jeney.demojeney.util.imageloader.JeneyImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc: 轮播的banner图
 *
 * @author Jeney
 */
public class BannerView implements ViewPager.OnPageChangeListener {

    private static final int TIME_DELAY = 4 * 1000;// 自动滚动的间隔时间

    private boolean isAutoScroll = true;// 是否开启自动滚动
    private boolean isScrolling;// 是否正在自动滚动

    @Bind(R.id.banner_layout)
    RelativeLayout mBannerLayout;

    @Bind(R.id.banner_viewpager)
    ViewPager mBannerViewpager;

    @Bind(R.id.banner_dot_layout)
    LinearLayout mDotLayout;

    private List<Banner> mBannerList;// 数据集

    private List<View> mImageViews = new ArrayList<View>();// 图片
    private LoopViewAdapter mPageViewAdapter;
    private ImageView[] mDots;// 指示点

    private Handler mHandler = new Handler();

    private Context mContext;
    private BannerAction mAction;

    public BannerView(Context context, BannerAction action) {
        mContext = context;
        mAction = action;
        loadRoot(context);
        refreshBannerView();
    }

    public BannerView(Context context, BannerAction action, List<Banner> list) {
        mContext = context;
        mAction = action;
        mBannerList = list;
        loadRoot(context);
        refreshBannerView();
    }

    /**
     * 获取view
     *
     * @return
     */
    public RelativeLayout getBannerLayout() {
        return mBannerLayout;
    }

    /**
     * 更新数据集
     *
     * @param list
     */
    public void updateData(List<Banner> list) {
        mBannerList = list;
        refreshBannerView();
    }

    /**
     * 设置是否开启自动滚动,默认true
     *
     * @param isAutoScroll
     */
    public void setIsAutoScroll(boolean isAutoScroll) {
        this.isAutoScroll = isAutoScroll;
    }

    /**
     * 开始自动滚动,在onResume()调用
     */
    public void startAutoScroll() {
        if (mImageViews.size() == 1) return;

        if (!isScrolling && isAutoScroll && mHandler != null) {
            mHandler.postDelayed(autoScrollTask, TIME_DELAY);
            isScrolling = true;
        }
    }

    /**
     * 停止自动滚动,在onStop()调用
     */
    public void stopAutoScroll() {
        if (mHandler != null) {
            mHandler.removeCallbacks(autoScrollTask);
            isScrolling = false;
        }
    }

    private void loadRoot(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_banner, null);
        ButterKnife.bind(this, view);
    }

    private void showBanner() {
        mBannerLayout.setVisibility(View.VISIBLE);
        mBannerViewpager.setVisibility(View.VISIBLE);
        mDotLayout.setVisibility(View.VISIBLE);
    }

    private void hideBanner() {
        mBannerLayout.setVisibility(View.GONE);
        mBannerViewpager.setVisibility(View.GONE);
        mDotLayout.setVisibility(View.GONE);
    }

    private void refreshBannerView() {
        if (mBannerList != null && !mBannerList.isEmpty()) {
            mImageViews.clear();
            mDotLayout.removeAllViews();
            for (int i = 0; i < mBannerList.size(); i++) {
                final Banner banner = mBannerList.get(i);
                if (banner != null) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setBackgroundResource(android.R.color.white);
                    JeneyImageLoader.displayImageWithLoadingListener(banner.getImage(), imageView, R.drawable.default_image);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            click();
                            Log.d("jeney", "跳转吧，小Banner");
                        }
                    });
                    mImageViews.add(imageView);
                }
            }

            if (mImageViews.size() > 1) {// 当图片大于1张时才显示指示点
                mDots = new ImageView[mImageViews.size()];
                for (int i = 0; i < mDots.length; i++) {
                    mDots[i] = new ImageView(mContext);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    params.setMargins(UIUtils.dip2px(mContext, 5), 0, UIUtils.dip2px(mContext, 5), 0);
                    mDots[i].setLayoutParams(params);
                    mDots[i].setScaleType(ImageView.ScaleType.FIT_XY);
                    mDots[i].setImageResource(R.drawable.banner_selector);
                    //初始化第一个选中
                    mDots[i].setSelected(i == 0);

                    mDotLayout.addView(mDots[i]);
                }
            }

            showBanner();

            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(1000);
            mBannerLayout.startAnimation(alphaAnimation);

            if (mImageViews.size() == 1) {
                mPageViewAdapter = new LoopViewAdapter(mImageViews, LoopViewAdapter.ViewType.Normal);
                mBannerViewpager.setAdapter(mPageViewAdapter);
            } else {
                mPageViewAdapter = new LoopViewAdapter(mImageViews, LoopViewAdapter.ViewType.Loop);
                mBannerViewpager.setAdapter(mPageViewAdapter);
                mBannerViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mDots.length);
            }

            mBannerViewpager.addOnPageChangeListener(this);

            startAutoScroll();
        } else {
            stopAutoScroll();
            hideBanner();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mDots != null && mDots.length > 0) {
            int currentPosition = position % mImageViews.size();
            for (int i = 0; i < mImageViews.size(); i++) {
                mDots[i].setSelected(currentPosition == i);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                slide();
                stopAutoScroll();
                break;
            case ViewPager.SCROLL_STATE_IDLE:// 注:每次自动滚动后也会走这里
                startAutoScroll();
                break;
            default:
                break;
        }
    }

    private Runnable autoScrollTask = new Runnable() {
        @Override
        public void run() {
            int currentItem = mBannerViewpager.getCurrentItem();
            currentItem++;
            if (Integer.MAX_VALUE == currentItem) currentItem = 0;
            mBannerViewpager.setCurrentItem(currentItem);

            mHandler.postDelayed(this, TIME_DELAY);
        }
    };

    //点击
    private void click() {
        if (mAction != null) mAction.click();
    }

    //滑动
    private void slide() {
        if (mAction != null) mAction.slide();
    }

    public interface BannerAction {
        void click();

        void slide();
    }

    public static class DefaultAction implements BannerAction {

        @Override
        public void click() {
        }

        @Override
        public void slide() {
        }
    }
}
