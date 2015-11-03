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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loading_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
