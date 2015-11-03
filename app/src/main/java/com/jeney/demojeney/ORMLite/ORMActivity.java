package com.jeney.demojeney.ORMLite;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jeney.demojeney.ORMLite.SerializableDataActivity;
import com.jeney.demojeney.ORMLite.bean.Account;
import com.jeney.demojeney.ORMLite.bean.Apple;
import com.jeney.demojeney.ORMLite.db.AccountDao;
import com.jeney.demojeney.ORMLite.db.AppleDao;
import com.jeney.demojeney.R;
import com.jeney.demojeney.comm.actvity.BaseActivity;
import com.jeney.demojeney.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ORMActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm_lite);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_data_btn)
    void addDataBtnOnclick() {
        addData();
    }

    @OnClick(R.id.query_data_btn)
    void queryDataBtnOnclick() {
        queryData();
    }

    @OnClick(R.id.delete_data_btn)
    void deleteDataBtn() {
        deleteData();
    }

    private void addData() {
        //必须先存account再存apple才能拿到数据
        Account account1 = new Account("jeney", "123456");
        Account account2 = new Account("tuotuo", "123456789");
        AccountDao accountDao = new AccountDao(this);
        accountDao.add(account1);
        accountDao.add(account2);

        Apple apple1 = new Apple("apple_red");
        Apple apple2 = new Apple("apple_green");
        Apple apple3 = new Apple("apple_yellow");
        //关联Account外键
        apple1.setAccount(account1);
        apple2.setAccount(account2);
        apple3.setAccount(account2);

        AppleDao appleDao = new AppleDao(this);
        appleDao.add(apple1);
        appleDao.add(apple2);
        appleDao.add(apple3);
        ToastUtil.showToast(this, "数据插入成功！");
    }

    private void queryData() {
        AccountDao accountDao = new AccountDao(this);
        List<Account> accounts = accountDao.queryAll();
        for (Account account : accounts) {
            Log.d("jeney", "data:" + account.toString());
        }
    }

    private void deleteData() {
        AccountDao accountDao = new AccountDao(this);
        AppleDao appleDao = new AppleDao(this);
        accountDao.deleteAll();
        appleDao.deleteAll();
        ToastUtil.showToast(this, "数据已全部删除");
    }

    @OnClick(R.id.test_serialize_btn)
    void testSerializeBtnOnclick() {
        AccountDao accountDao = new AccountDao(this);
        List<Account> accounts = accountDao.queryAll();
        //SerializableDataActivity没拿到数据之前不能调用下边一行代码，否则会报错
        //accountDao.deleAccount(accounts.get(0));
        Intent intent = new Intent(this, SerializableDataActivity.class);
        intent.putExtra("data", (Serializable) accounts);
        startActivity(intent);
    }
}


