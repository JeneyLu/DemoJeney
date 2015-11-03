package com.jeney.demojeney.ORMLite.bean;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * desc:
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/8/10
 */
@DatabaseTable(tableName = "accounts")
public class Account implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String password;
    @ForeignCollectionField
    private Collection<Apple> apples;

    public Account(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Apple> getApples() {
        return apples;
    }

    public void setApples(Collection<Apple> apples) {
        this.apples = apples;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Apple apple : apples) {
            stringBuilder.append(apple.toString()).append(",");
        }
        return name + "[" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "]";
    }
}
