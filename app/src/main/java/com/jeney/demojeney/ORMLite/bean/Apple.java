package com.jeney.demojeney.ORMLite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/10
 */
@DatabaseTable(tableName = "apple")
public class Apple {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nickName;
    @DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true)
    private Account account;

    public Apple(String nickName) {
        this.nickName = nickName;
    }

    public Apple() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return nickName;
    }
}
