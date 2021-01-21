package com.hy.hyspirit;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "user2")
public class Sldata {
    @Column(name = "id", isId = true)
    public int id;
    @Column(name = "right")
    public  int right;

    @Column(name = "shili")
    public double shili;

    public void setShili2(double shili2) {
        this.shili2 = shili2;
    }

    @Column(name = "shili2")
    public double shili2;

    @Override
    public String toString() {
        return "Sldata{" +
                "id=" + id +
                ", right=" + right +
                ", shili=" + shili +
                ", shili2=" + shili2 +
                ", data='" + data + '\'' +
                ", right2=" + right2 +
                '}';
    }

    public double getShili() {
        return shili;
    }

    public void setShili(double shili) {
        this.shili = shili;
    }

    public double getShili2() {
        return shili2;
    }

    public void setShili2(int shili2) {
        this.shili2 = shili2;
    }

    @Column(name = "data")
    public String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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


    @Column(name = "right2")
    public  int right2;

    public int getRight2() {
        return right2;
    }

    public void setRight2(int right2) {
        this.right2 = right2;
    }



}
