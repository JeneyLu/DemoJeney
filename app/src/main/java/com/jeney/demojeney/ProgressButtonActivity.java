package com.jeney.demojeney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeney.demojeney.util.ToastUtil;
import com.jeney.uicomponent.progressbutton.CustomProgressButton;
import com.jeney.uicomponent.progressbutton.ProgressButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/10/10
 */
public class ProgressButtonActivity extends AppCompatActivity {
    @Bind(R.id.progressButton)
    ProgressButton progressButton;

    @Bind(R.id.customProgressButton)
    CustomProgressButton customProgressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_progreass_button);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.progressButton)
    void progressButtonOnClick() {
        ToastUtil.showToast(this, "progressButtonOnClick");
    }

    @OnClick(R.id.startBtn)
    void startBtnOnClick() {
        progressButton.startRotate();
    }

    @OnClick(R.id.finishBtn)
    void finishBtnOnClick() {
        progressButton.animFinish();
    }

    @OnClick(R.id.removeBtn)
    void removeBtnOnClick() {
        progressButton.removeDrawable();
    }

    @OnClick(R.id.errorBtn)
    void errorBtnOnClick() {
        progressButton.animError();
    }

    @OnClick(R.id.customProgressButton)
    void customProgressButtonOnClick() {
        if (customProgressButton.isLoading()) {
            customProgressButton.setIsLoading(false);
        } else {
            customProgressButton.setIsLoading(true);
        }
    }
}
