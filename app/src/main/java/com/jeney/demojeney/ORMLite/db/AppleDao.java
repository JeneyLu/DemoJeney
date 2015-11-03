package com.jeney.demojeney.ORMLite.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jeney.demojeney.ORMLite.bean.Account;
import com.jeney.demojeney.ORMLite.bean.Apple;

/**
 * desc:Apple表操作的一个封装
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/10
 */
public class AppleDao {
    private DatabaseHelper helper;
    private Dao<Apple, Integer> appleDao;

    public AppleDao(Context context) {
        helper = DatabaseHelper.getInstance(context);
        try {
            appleDao = helper.getDao(Apple.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(Apple apple) {
        try {
            appleDao.createOrUpdate(apple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Apple> queryAll() {
        try {
            return appleDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        try {
            appleDao.delete(appleDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refresh(Apple apple) {
        try {
            appleDao.refresh(apple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
