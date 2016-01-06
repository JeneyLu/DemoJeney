package com.jeney.demojeney;

import android.os.Bundle;
import android.widget.ImageView;

import com.jeney.demojeney.comm.actvity.BaseActivity;
import com.jeney.uicomponent.BrushView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2016/1/6
 */
public class DrawViewActivity extends BaseActivity {
    @Bind(R.id.brushView)
    BrushView brushView;
    @Bind(R.id.iv)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.getBitmap)
    void getBitmap() {
        imageView.setImageBitmap(brushView.getBitmap());
    }

    @OnClick(R.id.clearView)
    void clearView(){
        brushView.resetView();
    }
}
