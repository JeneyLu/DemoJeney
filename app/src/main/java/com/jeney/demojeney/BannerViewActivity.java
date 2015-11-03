package com.jeney.demojeney;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.jeney.demojeney.comm.banner.Banner;
import com.jeney.demojeney.comm.banner.BannerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BannerViewActivity extends ActionBarActivity {
    @Bind(R.id.banner_container)
    FrameLayout bannerContainer;

    BannerView bannerView;

    private ArrayList<Banner> banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_view);
        ButterKnife.bind(this);

        Banner banner1 = new Banner();
        banner1.setImage("http://img.hb.aicdn.com/8ef474c76bf03b55b070e74aa4080a687ce6fd20b6275-K8xDMZ_fw658");
        Banner banner2 = new Banner();
        banner2.setImage("http://img.hb.aicdn.com/f945875d262da13b8ff3936648de559c0e2f86ec10c4b5-ioAZAO_fw658");
        Banner banner3 = new Banner();
        banner3.setImage("http://img.hb.aicdn.com/a8612f09dfb6bc12b1827cf182e13181a0a36d0314a423-HoTALI_fw658");
        Banner banner4 = new Banner();
        banner4.setImage("http://img.hb.aicdn.com/bb4dbd9e09fa6708aa3b4e22a612a93f0da12ae94c551-4YuWdG_fw658");
        banners = new ArrayList<>();
        banners.add(banner1);
        banners.add(banner2);
        banners.add(banner3);
        banners.add(banner4);

        bannerView = new BannerView(this, null, banners);
        bannerContainer.addView(bannerView.getBannerLayout());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_banner_view, menu);
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
}
