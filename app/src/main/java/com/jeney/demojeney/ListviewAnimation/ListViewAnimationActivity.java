package com.jeney.demojeney.ListviewAnimation;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jeney.demojeney.R;
import com.jeney.demojeney.comm.actvity.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/11/5
 */
public class ListViewAnimationActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;

    private MyAdapter myAdapter;

    private ScaleInAnimationAdapter scaleInAnimationAdapter;

    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_animation);
        ButterKnife.bind(this);
        data = new ArrayList<>();
        myAdapter = new MyAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, data);
        setAnimationController();
        listView.setAdapter(myAdapter);
        //setScaleInAnimationAdapter();
        setOnScrollListener();
    }

    private void setOnScrollListener() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    loadData();
                }
            }
        });
    }

    private void setScaleInAnimationAdapter() {
        scaleInAnimationAdapter = new ScaleInAnimationAdapter(myAdapter, 0.5f);
        scaleInAnimationAdapter.setAbsListView(listView);
        listView.setAdapter(scaleInAnimationAdapter);
    }

    private void setAnimationController() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        LayoutAnimationController controller = new LayoutAnimationController(animation, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
    }

    int i = 0;

    private void loadData() {
        int j = 0;
        while (j < 10) {
            data.add(String.valueOf(i));
            i++;
            j++;
        }
        myAdapter.notifyDataSetChanged();
    }

    class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }
    }
}
