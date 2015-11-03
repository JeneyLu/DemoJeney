package com.jeney.uicomponent;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author:jeney
 * @date:2015/10/18
 * @todo:
 */
public class LoadingView extends RelativeLayout {

    ImageView loadingImageView;
    AnimationDrawable animationDrawable;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        loadingImageView = new ImageView(context);
        loadingImageView.setImageResource(R.drawable.loading_anim);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        Button button = new Button(context);
        button.setBackgroundResource(android.R.color.transparent);
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        addView(button, buttonLayoutParams);
        addView(loadingImageView, layoutParams);
        animationDrawable = (AnimationDrawable) loadingImageView.getDrawable();
    }

    public void load(ViewGroup viewGroup) {
        viewGroup.addView(this);
        animationDrawable.start();
    }

    public void stop(ViewGroup viewGroup) {
        animationDrawable.stop();
        viewGroup.removeView(this);
    }
}
