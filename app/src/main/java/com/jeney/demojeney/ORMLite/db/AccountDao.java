package com.jeney.demojeney.ORMLite.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jeney.demojeney.ORMLite.bean.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * desc:Account表操作的一个封装
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/10
 */
public class AccountDao {
    private DatabaseHelper helper;
    private Dao<Account, Integer> accountDao;

    public AccountDao(Context context){
        helper = DatabaseHelper.getInstance(context);
        try {
            accountDao = helper.getDao(Account.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Account account){
        try {
            accountDao.createOrUpdate(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleAccount(Account account){
        try {
            accountDao.delete(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll(){
        try {
            accountDao.delete(accountDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> queryAll(){
        try {
            return accountDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void refresh(Account account){
        try {
            accountDao.refresh(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
