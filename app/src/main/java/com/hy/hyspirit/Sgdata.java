package com.hy.hyspirit;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "user3")
public class Sgdata {
    @Column(name = "id", isId = true)
    public int id;
    @Column(name = "right")
    public String right;

    @Column(name = "shili")
    public String shili;
    @Column(name = "data")
    public String data;

    public String getReport() {
        return report;
    }

    @Override
    public String toString() {
        return "Sgdata{" +
                "id=" + id +
                ", right='" + right + '\'' +
                ", shili='" + shili + '\'' +
                ", data='" + data + '\'' +
                ", report='" + report + '\'' +
                '}';
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Column(name = "report")
    public String report;

    public String getShili() {
        return shili;
    }

    public void setShili(String shili) {
        this.shili = shili;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}