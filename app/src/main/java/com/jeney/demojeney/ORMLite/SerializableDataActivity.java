package com.jeney.demojeney.ORMLite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jeney.demojeney.ORMLite.bean.Account;
import com.jeney.demojeney.ORMLite.db.AccountDao;
import com.jeney.demojeney.R;
import com.jeney.demojeney.comm.actvity.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SerializableDataActivity extends BaseActivity {
    @Bind(R.id.data_tv)
    TextView dataTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable_data);
        ButterKnife.bind(this);

        StringBuilder stringBuilder = new StringBuilder();
        List<Account> accounts = (List<Account>) getIntent().getSerializableExtra("data");
        for (Account account : accounts) {
            //Serializable后的数据，必须调用此方法数据库框架才不会报错
            new AccountDao(this).refresh(account);
            stringBuilder.append(account.toString() + "\r\n");
        }
        dataTv.setText(stringBuilder.toString());
    }

}
