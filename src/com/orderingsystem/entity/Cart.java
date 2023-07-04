package com.orderingsystem.entity;

public class Cart {
    private Integer cid;
    private Goods goods;
    private Integer uuid;
    private Integer gcount;
    private Double cprice;
    private String ctime;
    private String gname;
    private Double gprice;

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Cart() {
    }

    public Cart(Integer cid, Goods goods, Integer uuid, Integer gcount, Double cprice, String ctime) {
        this.cid = cid;
        this.goods = goods;
        this.uuid = uuid;
        this.gcount = gcount;
        this.cprice = cprice;
        this.ctime = ctime;
    }
    public Cart(Goods goods, Integer uuid, Integer gcount, Double cprice, String ctime) {
        this.goods = goods;
        this.uuid = uuid;
        this.gcount = gcount;
        this.cprice = cprice;
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", goods=" + goods +
                ", uuid=" + uuid +
                ", gcount=" + gcount +
                ", cprice=" + cprice +
                ", ctime='" + ctime + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getGcount() {
        return gcount;
    }

    public void setGcount(Integer gcount) {
        this.gcount = gcount;
    }

    public Double getCprice() {
        return cprice;
    }

    public void setCprice(Double cprice) {
        this.cprice = cprice;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
