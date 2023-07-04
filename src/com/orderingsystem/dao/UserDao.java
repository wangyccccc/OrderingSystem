package com.orderingsystem.dao;

import com.orderingsystem.entity.User;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class UserDao{
    private List<User> userList;
    private PreparedStatement ps;
    private Connection con;
    private ResultSet rs;
    private String sql;
    private User user;
    private boolean flag;
    public User login(User myUser) throws SQLException {
        con = DBHelper.getCon();
        sql = "select * from tb_user where account = ? and password = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1,myUser.getAccount());
        ps.setString(2,myUser.getPassword());
        rs = ps.executeQuery();
        if(rs.next()) {
            user = new User();
            user.setUuid(rs.getInt(1),rs.getInt(1));
            user.setName(rs.getString(4));
            user.setTel(rs.getString(7));
            user.setAddress(rs.getString(8));
            user.setImagepath(rs.getString(10));
            user.setPower(rs.getInt(11));
        }
        DBHelper.closeObj(con,ps,rs);
        return user;
    }
    public boolean deleteUser(String account) throws SQLException{
        con = DBHelper.getCon();
        sql = "delete from tb_user where account = '"+account+"'";
        ps = con.prepareStatement(sql);
        if(ps.executeUpdate() > 0 ) flag = true;
        return flag;
    }
    public User queryUserByUuid(Integer uuid) throws SQLException {
        con = DBHelper.getCon();
        sql = "select * from tb_user where uuid = " + uuid;
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if(rs.next()) {
            user = new User();
            user.setUuid(1,rs.getInt(1));
            user.setAccount(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setName(rs.getString(4));
            user.setSex(rs.getString(5));
            user.setAge(rs.getInt(6));
            user.setTel(rs.getString(7));
            user.setAddress(rs.getString(8));
            user.setHobby(rs.getString(9));
            user.setImagepath(rs.getString(10));
            user.setPower(rs.getInt(11));
            user.setRegistertime(rs.getString(12));
            user.setInfo(rs.getString(13));
        }
        DBHelper.closeObj(con,ps,rs);
        return user;
    }
    public List<User> queryUserByPower(Integer power,String name) throws SQLException {
        userList = new ArrayList<>();
        con = DBHelper.getCon();
        sql = "select * from tb_user where name like '%"+name+"%' and power = " + power;
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()) {
            user = new User();
            user.setUuid(1,rs.getInt(1));
            user.setAccount(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setName(rs.getString(4));
            user.setSex(rs.getString(5));
            user.setAge(rs.getInt(6));
            user.setTel(rs.getString(7));
            user.setAddress(rs.getString(8));
            user.setHobby(rs.getString(9));
            user.setImagepath(rs.getString(10));
            user.setPower(rs.getInt(11));
            user.setRegistertime(rs.getString(12));
            user.setInfo(rs.getString(13));
            userList.add(user);
        }
        DBHelper.closeObj(con,ps,rs);
        return userList;
    }
    public boolean register(User user) throws SQLException {
        con = DBHelper.getCon();
        if(Objects.isNull(user.getPower())) sql = "INSERT INTO tb_user (account, password, name, sex, age, tel, address, hobby, imagepath, info) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        else sql = "INSERT INTO tb_user (account, password, name, sex, age, tel, address, hobby, imagepath, info,power) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, user.getAccount());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getSex());
        ps.setInt(5, user.getAge());
        ps.setString(6, user.getTel());
        ps.setString(7, user.getAddress());
        ps.setString(8, user.getHobby());
        ps.setString(9, user.getImagepath());
        ps.setString(10, user.getInfo());
        if(Objects.nonNull(user.getPower())) ps.setInt(11, user.getPower());
        if(ps.executeUpdate() > 0) flag = true;
        DBHelper.closeObj(con,ps,rs);
        return flag;
    }
    public boolean updateUserByAccount(User user) throws SQLException {
        con = DBHelper.getCon();
        String sql = "UPDATE tb_user "
                + "SET password = ?, name = ?, sex = ?, age = ?, tel = ?, address = ?, hobby = ?, info = ? "
                + "WHERE account = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getName());
        ps.setString(3, user.getSex());
        ps.setInt(4, user.getAge());
        ps.setString(5, user.getTel());
        ps.setString(6, user.getAddress());
        ps.setString(7, user.getHobby());
        ps.setString(8, user.getInfo());
        ps.setString(9, user.getAccount());
        if(ps.executeUpdate() > 0) flag = true;
        return flag;
    }
    public static void main(String[] args) throws Exception{
    }
}
