package com.jeney.demojeney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jeney.demojeney.FlippableStackView.FlippableStackViewActivity;
import com.jeney.demojeney.ListviewAnimation.ListViewAnimationActivity;
import com.jeney.demojeney.ORMLite.ORMActivity;
import com.jeney.demojeney.ViewPagerWithTansFormer.ViewPagerActivity;
import com.jeney.demojeney.androidLtry.RecyclerViewActivity;
import com.jeney.demojeney.comm.banner.BannerView;
import com.jeney.demojeney.model.ParcelableBean;
import com.jeney.demojeney.network.RetrofitActivity;
import com.jeney.demojeney.toolbar.DynamicToolBarActivity;
import com.jeney.demojeney.toolbar.ToolBarActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quick_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quick_test:
                Intent intent = new Intent(MainActivity.this, QuickTestActivity.class);
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.transfer_data_btn)
    void transferDataBtnOnclick() {
        startActivity(DataTransferActivity.newIntent(this));
    }

    @OnClick(R.id.retrofit_demo_btn)
    void retrofitDemoBtnOnclick() {
        //retrofit框架的使用demo
        Intent retrofitDemoIntent = new Intent(this, RetrofitActivity.class);
        startActivity(retrofitDemoIntent);
    }

    @OnClick(R.id.ormlite_demo_btn)
    void ormliteDemoBtnOnclick() {
        //ORMLite数据库使用的demo
        Intent ormLiteDemoIntent = new Intent(this, ORMActivity.class);
        startActivity(ormLiteDemoIntent);
    }

    @OnClick(R.id.transparent_activity_btn)
    void transparentActivityBtnOnclick() {
        Intent intent = new Intent(this, TransparentActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.recycler_view_demo_btn)
    void recyclerViewDemoBtnOnclick() {
        Intent recyclerViewDemoIntent = new Intent(this, RecyclerViewActivity.class);
        startActivity(recyclerViewDemoIntent);
    }

    @OnClick(R.id.tool_bar_activity_btn)
    void toolBarActivityBtnOnclick() {
        Intent toolBarActivityDemo = new Intent(this, ToolBarActivity.class);
        startActivity(toolBarActivityDemo);
    }

    @OnClick(R.id.dynamic_tool_bar_activity_btn)
    void dynamicTollBarActivityBtnClick() {
        Intent dynamicTollBarActivityIntent = new Intent(this, DynamicToolBarActivity.class);
        startActivity(dynamicTollBarActivityIntent);
    }

    @OnClick(R.id.wheel_view_activity_btn)
    void wheelViewActivityBtnOnclick() {
        Intent wheelViewActivityIntent = new Intent(this, WheelViewActivity.class);
        startActivity(wheelViewActivityIntent);
    }

    @OnClick(R.id.progress_button_activity_btn)
    void progressButtonActivityBtnOnclick() {
        Intent progressButtonActivityIntent = new Intent(this, ProgressButtonActivity.class);
        startActivity(progressButtonActivityIntent);
    }

    @OnClick(R.id.canvas_demo_activity_btn)
    void canvasDemoBtnOnClick() {
        Intent canvasDemoActivityIntent = new Intent(this, CanvasDemoActivity.class);
        startActivity(canvasDemoActivityIntent);
    }

    @OnClick(R.id.pull_to_zoom_in_demo_activity_btn)
    void pullToZoomInBtnOnClick() {
        Intent pullToZoomInActivityIntent = new Intent(this, PullToZoomInDemoActivity.class);
        startActivity(pullToZoomInActivityIntent);
    }

    @OnClick(R.id.sougou_browser_loading_demo_activity_btn)
    void sougouBrowserLoadingDemoBtnOnClick() {
        Intent sougouBrowserLoadingDemoIntent = new Intent(this, SoGouBrowserLoadingActivity.class);
        startActivity(sougouBrowserLoadingDemoIntent);
    }

    @OnClick(R.id.explosion_field_demo_activity_btn)
    void explosionFieldDemoActivityBtnOnClick() {
        Intent explosionFieldDemoIntent = new Intent(this, ExplosionFieldDemoActivity.class);
        startActivity(explosionFieldDemoIntent);
    }

    @OnClick(R.id.swipe_card_demo_activity_btn)
    void swipeCardDemoActivityBynOnClick() {
        Intent swipeCardDemoIntent = new Intent(this, SwipeCardsDemoActivity.class);
        startActivity(swipeCardDemoIntent);
    }

    @OnClick(R.id.swipeable_card_demo_activity_btn)
    void swipeableCardDemoActivityBynOnClick() {
        Intent swipeCardDemoIntent = new Intent(this, SwipeableCardDemoActivity.class);
        startActivity(swipeCardDemoIntent);
    }

    @OnClick(R.id.viewpager_demo_activity_btn)
    void viewPagerActivityBtnOnClick() {
        Intent viewPagerIntent = new Intent(this, ViewPagerActivity.class);
        startActivity(viewPagerIntent);
    }

    @OnClick(R.id.loading_demo_activity_btn)
    void loadigDemoActivityBtnOnClick() {
        Intent loadigDemoIntent = new Intent(this, LoadingDemoActivity.class);
        startActivity(loadigDemoIntent);
    }

    @OnClick(R.id.stackview_demo_activity_btn)
    void stackViewBtnOnClick() {
        Intent intent = new Intent(this, StackViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.flippable_stackview_demo_activity_btn)
    void flippableStackViewOnclick() {
        Intent intent = new Intent(this, FlippableStackViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.banner_view_demo_activity_btn)
    void bannerViewBtnOnclick() {
        Intent intent = new Intent(this, BannerViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.listview_animation_demo_activity_btn)
    void listviewAnimationBtnOnclick() {
        Intent intent = new Intent(this, ListViewAnimationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.draw_view_demo_activity_btn)
    void drawViewDemoBtnOnclick(){
        Intent intent = new Intent(this,DrawViewActivity.class);
        startActivity(intent);
    }
}
