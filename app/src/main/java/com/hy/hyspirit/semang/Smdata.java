package com.hy.hyspirit.semang;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


@Table(name = "user")
public class Smdata {
    @Column(name = "id", isId = true)
    public int id;
    @Column(name = "right")
    public int right;

    @Column(name = "fault")
    public int fault;

    public String getData() {
        return data;
    }

    @Column(name = "unlook")
    public int unlook;

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Smdata{" +
                "id=" + id +
                ", right=" + right +
                ", fault=" + fault +
                ", unlook=" + unlook +
                ", data=" + data +
                '}';
    }

    @Column(name = "data")
    public String data;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getFault() {
        return fault;
    }

    public void setFault(int fault) {
        this.fault = fault;
    }

    public int getUnlook() {
        return unlook;
    }

    public void setUnlook(int unlook) {
        this.unlook = unlook;
    }

}
