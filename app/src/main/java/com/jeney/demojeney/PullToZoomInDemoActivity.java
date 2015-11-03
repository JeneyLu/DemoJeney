package com.jeney.demojeney;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.jeney.uicomponent.PullToZoomListView;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/10/15
 */
public class PullToZoomInDemoActivity extends ActionBarActivity {
    PullToZoomListView listView;
    private String[] adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_in);
        listView = (PullToZoomListView) findViewById(R.id.listview);
        adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3",
                "HttpClient", "DDMS", "Android Studio", "Fragment", "Loader"};
        listView.setAdapter(new ArrayAdapter<String>(PullToZoomInDemoActivity.this,
                android.R.layout.simple_list_item_1, adapterData));
        listView.getHeaderView().setImageResource(R.drawable.splash01);
        listView.getHeaderView().setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
