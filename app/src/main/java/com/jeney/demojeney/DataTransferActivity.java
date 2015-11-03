package com.jeney.demojeney;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jeney.demojeney.model.ParcelableBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DataTransferActivity extends AppCompatActivity {
    @Bind(R.id.display_data_tv)
    TextView displayDataTv;

    private ArrayList<ParcelableBean> parcelableBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_transfer);
        ButterKnife.bind(this);
        parcelableBeans = getIntent().getParcelableArrayListExtra("data");
        initUI();
        bindEvent();
    }

    private void bindEvent() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        StringBuffer text = new StringBuffer();
        for (ParcelableBean parcelableBean : parcelableBeans) {
            text.append(parcelableBean.getId() + parcelableBean.getName() + "\n");
            text.append(parcelableBean.getFeatures().toString() + "\n");
            text.append(parcelableBean.getParcelableModels().get(0).getName() + "\n");
        }
        displayDataTv.setText(text.toString());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
