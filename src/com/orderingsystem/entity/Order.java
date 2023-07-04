package com.orderingsystem.entity;

import java.util.List;
import java.util.Objects;

public class Order {
    private int ooid;
    private String address;
    private User user;
    private String otime;
    private String name;// 下单用户
    private double orderprice;// 订单总价
    private List<OrderItem> orderItemList;// 订单项集合
    public void calcOrderPrice(){
        if(Objects.nonNull(orderItemList)) {
            for(OrderItem orderItem : orderItemList){
                this.orderprice += orderItem.getOrderitemprice();
            }
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "ooid=" + ooid +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", orderprice=" + orderprice +
                ", otime='" + otime + '\'' +
                ", orderItemList=" + orderItemList +
                '}';
    }

    public Order() {
    }

    public Order(int ooid, String address, User user, double orderprice, String otime, List<OrderItem> orderItemList) {
        this.ooid = ooid;
        this.address = address;
        this.user = user;
        this.orderprice = orderprice;
        this.otime = otime;
        this.orderItemList = orderItemList;
    }
    public Order(String address, User user, double orderprice, String otime, List<OrderItem> orderItemList) {
        this.address = address;
        this.user = user;
        this.orderprice = orderprice;
        this.otime = otime;
        this.orderItemList = orderItemList;
    }

    public int getOoid() {
        return ooid;
    }

    public void setOoid(int ooid) {
        this.ooid = ooid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(double orderprice) {
        this.orderprice = orderprice;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
