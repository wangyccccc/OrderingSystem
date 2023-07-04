package com.orderingsystem.entity;

public class OrderItem {
    private int otid;
    private Order order;
    private Goods goods;
    private int gcount;
    private Double orderitemprice;
    private String ottime;

    public OrderItem(int otid, Order order, Goods goods, int gcount, Double orderitemprice, String ottime) {
        this.otid = otid;
        this.order = order;
        this.goods = goods;
        this.gcount = gcount;
        this.orderitemprice = orderitemprice;
        this.ottime = ottime;
    }

    public OrderItem(Order order, Goods goods, int gcount, Double orderitemprice, String ottime) {
        this.order = order;
        this.goods = goods;
        this.gcount = gcount;
        this.orderitemprice = orderitemprice;
        this.ottime = ottime;
    }

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "otid=" + otid +
                ", order=" + order +
                ", goods=" + goods +
                ", gcount=" + gcount +
                ", orderitemprice=" + orderitemprice +
                ", ottime='" + ottime + '\'' +
                '}';
    }

    public int getOtid() {
        return otid;
    }

    public void setOtid(int otid) {
        this.otid = otid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getGcount() {
        return gcount;
    }

    public void setGcount(int gcount) {
        this.gcount = gcount;
    }

    public Double getOrderitemprice() {
        return orderitemprice;
    }

    public void setOrderitemprice(Double orderitemprice) {
        this.orderitemprice = orderitemprice;
    }

    public String getOttime() {
        return ottime;
    }

    public void setOttime(String ottime) {
        this.ottime = ottime;
    }
}
