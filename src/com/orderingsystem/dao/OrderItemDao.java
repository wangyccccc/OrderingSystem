package com.orderingsystem.dao;

import com.orderingsystem.entity.OrderItem;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    private PreparedStatement ps;
    private List<OrderItem> orderItemList;
    private Connection con;
    private ResultSet rs;
    private String sql;
    private OrderItem orderItem;
    private boolean flag;

    public boolean insertOrderItem(OrderItem orderItem) throws SQLException {
        con = DBHelper.getCon();
        sql = "insert into tb_orderitem(ooid,gid,gcount,orderitemprice)" +
                " values(?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setObject(1, new OrderDao().getMaxOoid());// 获取订单表里面最大订单编号
        ps.setObject(2, orderItem.getGoods().getGid());
        ps.setObject(3, orderItem.getGcount());
        ps.setObject(4, orderItem.getOrderitemprice());
        if (ps.executeUpdate() > 0) flag = true;
        return flag;
    }
}






















