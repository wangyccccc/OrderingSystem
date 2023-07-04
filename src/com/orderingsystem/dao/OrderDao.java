package com.orderingsystem.dao;

import com.orderingsystem.entity.Order;
import com.orderingsystem.entity.OrderItem;
import com.orderingsystem.entity.User;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private PreparedStatement ps;
    private List<Order> orderList;
    private Connection con;
    private ResultSet rs;
    private String sql;
    private Order order;
    private boolean flag;

    public boolean insertOrder(Order order) throws SQLException {
        con = DBHelper.getCon();
        sql = "insert into tb_order(address,orderprice,uuid) values(?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setObject(1,order.getUser().getAddress());
        ps.setObject(2,order.getOrderprice());
        ps.setObject(3,order.getUser().getUuid());
        if(ps.executeUpdate() > 0) {
            flag = true;
            OrderItemDao orderItemDao = new OrderItemDao();
            List<OrderItem> orderItemList = order.getOrderItemList();
            for(OrderItem orderItem : orderItemList){
                orderItemDao.insertOrderItem(orderItem);
            }
        }
        return flag;
    }

    public int getMaxOoid() throws SQLException{
        Integer maxOoid = 0;
        con = DBHelper.getCon();
        sql = "select max(ooid) from tb_order";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next())  maxOoid = rs.getInt(1);
        return maxOoid;
    }
    public List<Order> queryOrderByUuid(User user) throws SQLException{
        orderList = new ArrayList<>();
        con = DBHelper.getCon();
        sql = "select * from tb_order where uuid = " + user.getUuid();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            order = new Order();
            order.setName(user.getName());
            order.setAddress(user.getAddress());
            order.setOoid(rs.getInt(1));
            order.setAddress(rs.getString(2));
            order.setOtime(rs.getString(5));
            order.setOrderprice(rs.getDouble(3));
            orderList.add(order);
        }
        return orderList;
    }
    public List<Order> queryAllOrder() throws SQLException{
        UserDao userDao = new UserDao();
        orderList = new ArrayList<>();
        con = DBHelper.getCon();
        sql = "select * from tb_order";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            User user = userDao.queryUserByUuid(rs.getInt(4));
            order = new Order();
            order.setName(user.getName());
            order.setAddress(user.getAddress());
            order.setOoid(rs.getInt(1));
            order.setAddress(rs.getString(2));
            order.setOtime(rs.getString(5));
            order.setOrderprice(rs.getDouble(3));
            orderList.add(order);
        }
        return orderList;
    }
    public static void main(String[] args) throws Exception{
        User user = new User();
        user.setUuid(1,1);
        new OrderDao().queryOrderByUuid(user).forEach(System.out::println);
    }
}
