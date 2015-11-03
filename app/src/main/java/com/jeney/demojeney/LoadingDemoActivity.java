package com.jeney.demojeney;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jeney.uicomponent.LoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoadingDemoActivity extends ActionBarActivity {
    @Bind(R.id.loading_container)
    FrameLayout loadingContainer;

    LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_demo);
        ButterKnife.bind(this);
        loadingView = new LoadingView(this);
    }

    @OnClick(R.id.load)
    void load() {
        loadingView.load(loadingContainer);
    }

    @OnClick(R.id.stop)
    void stop() {
        loadingView.stop(loadingContainer);
    }
}
