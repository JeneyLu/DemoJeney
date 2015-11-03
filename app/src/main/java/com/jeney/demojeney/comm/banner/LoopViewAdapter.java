package com.jeney.demojeney.comm.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

public class LoopViewAdapter extends PagerAdapter {

    public enum ViewType {
        Normal, Loop
    }

    private ViewType type;
    private List<View> viewlist;

    public LoopViewAdapter(List<View> viewlist, ViewType type) {
        this.viewlist = viewlist;
        this.type = type;
    }

    @Override
    public int getCount() {
        switch (type) {
            case Loop:
                return Integer.MAX_VALUE;
            case Normal:
                if (viewlist == null) return 0;
                else  return viewlist.size();
            default:
                return 0;
        }
    }

    @Override
    public int getItemPosition(Object object) { //不设置此值可能会导致ANR
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
            Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= viewlist.size();
        if (position < 0) {
            position = viewlist.size() + position;
        }
        View view = viewlist.get(position);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

}
