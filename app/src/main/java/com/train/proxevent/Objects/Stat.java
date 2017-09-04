package com.train.proxevent.Objects;

/**
 * Created by David on 04.09.2017.
 */

public class Stat {

    private String tableName;
    private long number;

    public Stat(String tableName,long number) {
        this.tableName = tableName;
        this.number = number;

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Stat() {


    }


}
