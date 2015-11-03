package com.jeney.demojeney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jeney.demojeney.comm.actvity.BaseActivity;
import com.jeney.demojeney.model.ParcelableBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DataTransferActivity extends BaseActivity {
    @Bind(R.id.display_data_tv)
    TextView displayDataTv;

    private ArrayList<ParcelableBean> parcelableBeans;

    public static Intent newIntent(Context context) {
        //利用Intent传递ArrayList，ArrayList中的对象必须是Parcelable的
        Intent transferDataIntent = new Intent(context, DataTransferActivity.class);
        ArrayList<ParcelableBean> parcelableBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<String> features = new ArrayList<>();
            ArrayList<ParcelableBean.ParcelableModel> parcelableModels = new ArrayList<>();
            features.add("feature1");
            features.add("feature2");
            features.add("feature3");
            parcelableModels.add(new ParcelableBean.ParcelableModel(i, "ParcelableModel"));
            ParcelableBean parcelableBean = new ParcelableBean(i, "name" + i, features, parcelableModels);
            parcelableBeans.add(parcelableBean);
        }
        transferDataIntent.putParcelableArrayListExtra("data", parcelableBeans);
        return transferDataIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_transfer);
        ButterKnife.bind(this);
        parcelableBeans = getIntent().getParcelableArrayListExtra("data");
        initUI();
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

}
