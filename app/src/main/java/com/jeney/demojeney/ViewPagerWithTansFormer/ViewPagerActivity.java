package com.jeney.demojeney.ViewPagerWithTansFormer;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.jeney.demojeney.R;
import com.jeney.demojeney.util.ViewPagerTransFormers.AccordionTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.CubeTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.DefaultTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.DepthPageTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.InRightDownTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.InRightUpTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.PageTurningTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.RotateTransformer;
import com.jeney.demojeney.util.ViewPagerTransFormers.ZoomOutPageTransformer;


public class ViewPagerActivity extends Activity {

    ViewPager vPage = null;
    MyAdapter mAdapter = null;
    List<ImageView> mData = new ArrayList<ImageView>();
    private ArrayAdapter<String> adapter = null;
    int[] ids = new int[]{R.mipmap.p1, R.mipmap.p2,
            R.mipmap.p3, R.mipmap.p4, R.mipmap.p5,
            R.mipmap.p6, R.mipmap.p7, R.mipmap.p8,
            R.mipmap.p9, R.mipmap.p10,};

    String[] effectType = {"默认", "深入浅出", "立方体", "旋转", "左右折叠", "右上角进入", "右下角进入", "淡入淡出", "翻书"};

    Spinner mSpinner;
    ListView g = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, effectType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(ISelectedListener);

        vPage = (ViewPager) findViewById(R.id.main_page);
        resetView();
    }

    private void getData(List<ImageView> data) {
        data.clear();
        for (int i = 0; i < ids.length; i++) {
            ImageView image = new ImageView(this);
            image.setImageResource(ids[i]);
            data.add(image);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    OnItemSelectedListener ISelectedListener = new OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            resetView();
            switch (position) {
                case 0:
                    vPage.setPageTransformer(true, new DefaultTransformer());
                    break;
                case 1:
                    vPage.setPageTransformer(true, new DepthPageTransformer());
                    break;
                case 2:
                    vPage.setPageTransformer(true, new CubeTransformer());
                    break;
                case 3:
                    vPage.setPageTransformer(true, new RotateTransformer());
                    break;
                case 4:
                    vPage.setPageTransformer(true, new AccordionTransformer());
                    break;
                case 5:
                    vPage.setPageTransformer(true, new InRightUpTransformer());
                    break;
                case 6:
                    vPage.setPageTransformer(true, new InRightDownTransformer());
                    break;
                case 7:
                    vPage.setPageTransformer(true, new ZoomOutPageTransformer());
                    break;
                case 8:
                    vPage.setPageTransformer(true, new PageTurningTransformer());
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    };

    private void resetView() {
        vPage.removeAllViews();
        getData(mData);
        mAdapter = new MyAdapter(mData, vPage);
        vPage.setAdapter(mAdapter);
        vPage.setCurrentItem(mData.size() / 2);
        vPage.setPageTransformer(true, new DefaultTransformer());
    }
}
