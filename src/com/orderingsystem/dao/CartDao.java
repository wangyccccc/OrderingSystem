package com.orderingsystem.dao;

import com.orderingsystem.entity.Cart;
import com.orderingsystem.entity.User;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private PreparedStatement ps;
    private List<Cart> cartList;
    private Connection con;
    private ResultSet rs;
    private String sql;
    private Cart cart;
    private boolean flag;

    public boolean insertCart(Cart cart) throws SQLException {
        con = DBHelper.getCon();
        sql = "insert into tb_cart(gid,uuid,gcount,cprice) values(?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setObject(1,cart.getGoods().getGid());
        ps.setObject(2,cart.getUuid());
        ps.setObject(3,cart.getGcount());
        ps.setObject(4,cart.getCprice());
        if(ps.executeUpdate() > 0) flag = true;
        return flag;
    }

    public Cart queryCartByGidUuid(Integer gid,Integer uuid) throws SQLException {
        con = DBHelper.getCon();
        sql = "select * from tb_cart where gid = ? and uuid = ?";
        ps = con.prepareStatement(sql);
        //
        ps.setObject(1,gid);
        ps.setObject(2,uuid);
        rs = ps.executeQuery();
        if(rs.next()){
            cart = new Cart();
            cart.setGcount(rs.getInt("gcount"));
        }
        return cart;
    }

    public boolean updateCartByGidUuid(Integer gid,Integer uuid,Integer gcount,Double gprice) throws SQLException{
        DecimalFormat df = new DecimalFormat("#.00");
        con = DBHelper.getCon();
        sql = "update tb_cart set gcount = ?,cprice = ? where gid = ? and uuid = ?";
        ps = con.prepareStatement(sql);
        ps.setObject(1,gcount);
        ps.setObject(2,df.format(gcount * gprice));// 价格小计=商品价格单价*商品数量
        ps.setObject(3,gid);
        ps.setObject(4,uuid);
        if(ps.executeUpdate() > 0) flag = true;
        return flag;
    }

    public List<Cart> queryCartByUuid(Integer uuid) throws SQLException {
        GoodsDao goodsDao = new GoodsDao();
        cartList = new ArrayList<>();
        con = DBHelper.getCon();
        sql = "select * from tb_cart c where uuid = " + uuid;
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            cart = new Cart();
            cart.setCid(rs.getInt(1));
            cart.setGoods(goodsDao.queryByGid(rs.getInt(2)));
            cart.setUuid(rs.getInt(3));
            cart.setGcount(rs.getInt(4));
            cart.setCprice(rs.getDouble(5));
            cart.setCtime(rs.getString(6));
            cart.setGname(goodsDao.queryByGid(rs.getInt(2)).getGname());
            cart.setGprice(goodsDao.queryByGid(rs.getInt(2)).getGprice());
            cartList.add(cart);
        }
        return cartList;
    }

    public boolean clearCart(User user) throws SQLException{
        con = DBHelper.getCon();
        sql = "delete from tb_cart where uuid = " + user.getUuid();
        ps = con.prepareStatement(sql);
        if(ps.executeUpdate() > 0) flag = true;
        return flag;
    }

    public static void main(String[] args) throws Exception{
        new CartDao().queryCartByUuid(1).forEach(System.out::println);
    }
}
