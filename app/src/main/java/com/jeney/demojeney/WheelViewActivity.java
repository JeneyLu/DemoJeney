package com.jeney.demojeney;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeney.uicomponent.wheel.AbstractWheel;
import com.jeney.uicomponent.wheel.OnWheelScrollListener;
import com.jeney.uicomponent.wheel.WheelVerticalView;
import com.jeney.uicomponent.wheel.adapter.AbstractWheelTextAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WheelViewActivity extends AppCompatActivity {
    @Bind(R.id.wheel_vertical_view)
    WheelVerticalView wheelVerticalView;

    private SimpleTextAdapter simpleTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        simpleTextAdapter = new SimpleTextAdapter(this, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        wheelVerticalView.setViewAdapter(simpleTextAdapter);
        wheelVerticalView.setCurrentItem(2);
        wheelVerticalView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(AbstractWheel wheel) {

            }

            @Override
            public void onScrollingFinished(AbstractWheel wheel) {
               simpleTextAdapter.notifyDataChangedEvent();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private class SimpleTextAdapter extends AbstractWheelTextAdapter {

        String[] strings;

        public SimpleTextAdapter(Context context, String[] strings) {
            super(context, R.layout.item_wheel_text, NO_RESOURCE);
            this.strings = strings;
        }

        @Override
        public View getItem(int index, View convertView, ViewGroup parent) {
            View view = super.getItem(index, convertView, parent);
            TextView textView = (TextView) view.findViewById(R.id.wheel_text);
            textView.setText(getItemText(index));
            if(wheelVerticalView.getCurrentItem() == index){
                textView.setTextSize(18);
            }else {
                textView.setTextSize(12);
            }
            return view;
        }

        @Override
        public int getItemsCount() {
            return strings.length;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return strings[index];
        }
    }
}
