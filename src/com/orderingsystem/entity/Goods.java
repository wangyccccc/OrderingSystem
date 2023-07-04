package com.orderingsystem.entity;

public class Goods {
    private int gid;
    private String gname;
    private String tname;
    private double gprice;
    private int gkc;
    private int gxl;
    private String gtime;
    private String gpath;
    private String ginfo;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGkc() {
        return gkc;
    }

    public void setGkc(int gkc) {
        this.gkc = gkc;
    }

    public int getGxl() {
        return gxl;
    }

    public void setGxl(int gxl) {
        this.gxl = gxl;
    }

    public String getGtime() {
        return gtime;
    }

    public void setGtime(String gtime) {
        this.gtime = gtime;
    }

    public String getGpath() {
        return gpath;
    }

    public void setGpath(String gpath) {
        this.gpath = gpath;
    }

    public String getGinfo() {
        return ginfo;
    }

    public void setGinfo(String ginfo) {
        this.ginfo = ginfo;
    }

    public Goods(int gid, String gname, String tname, double gprice, int gkc, int gxl, String gtime, String gpath, String ginfo) {
        this.gid = gid;
        this.gname = gname;
        this.tname = tname;
        this.gprice = gprice;
        this.gkc = gkc;
        this.gxl = gxl;
        this.gtime = gtime;
        this.gpath = gpath;
        this.ginfo = ginfo;
    }
    public Goods(String gname, String tname, double gprice, int gkc, int gxl, String gtime, String gpath, String ginfo) {
        this.gname = gname;
        this.tname = tname;
        this.gprice = gprice;
        this.gkc = gkc;
        this.gxl = gxl;
        this.gtime = gtime;
        this.gpath = gpath;
        this.ginfo = ginfo;
    }
    public Goods(String gname, String tname, double gprice, int gkc, int gxl, String gpath, String ginfo) {
        this.gname = gname;
        this.tname = tname;
        this.gprice = gprice;
        this.gkc = gkc;
        this.gxl = gxl;
        this.gpath = gpath;
        this.ginfo = ginfo;
    }

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", tname=" + tname +
                ", gprice=" + gprice +
                ", gkc=" + gkc +
                ", gxl=" + gxl +
                ", gtime='" + gtime + '\'' +
                ", gpath='" + gpath + '\'' +
                ", ginfo='" + ginfo + '\'' +
                '}';
    }
}