package com.jeney.uicomponent.progressbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jeney.uicomponent.R;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/10/10
 */
public class CustomProgressButton extends LinearLayout {
    private ProgressBar progressBar;
    private TextView textView;
    private boolean isLoading;

    public CustomProgressButton(Context context) {
        this(context, null);
    }

    public CustomProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_progress_button, this, true);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        textView = (TextView) findViewById(R.id.textView);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomProgressButton);
        String text = typedArray.getString(R.styleable.CustomProgressButton_text);
        ColorStateList color = typedArray.getColorStateList(R.styleable.CustomProgressButton_textColor);
        float size = typedArray.getDimensionPixelSize(R.styleable.CustomProgressButton_textSize, 16);
        textView.setText(text);
        if (null != color)
            textView.setTextColor(color);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        typedArray.recycle();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (null != progressBar) {
            progressBar.setVisibility(isLoading ? VISIBLE : INVISIBLE);
            setClickable(!isLoading);
        }
    }
}
