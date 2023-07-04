package com.orderingsystem.dao;

import com.orderingsystem.entity.Goods;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsDao{
	
	private PreparedStatement ps;
	private List<Goods> goodsList;
	private Connection con;
	private ResultSet rs;
	private String sql;
	private Goods goods;
	private boolean f;
	private Integer rows;
	public List<Goods> queryAll(String gname,String tname) throws SQLException{
		goodsList = new ArrayList<>();
		con = DBHelper.getCon();
		if(Objects.isNull(tname)) sql = "select * from tb_goods where gname like '%"+gname+"%' order by gid";
		else sql = "select * from tb_goods where gname like '%"+gname+"%' and tname ='"+tname+"'  order by gid" ;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			goods = new Goods();
			goods.setGid(rs.getInt(1));
			goods.setGname(rs.getString(2));
			goods.setTname(rs.getString(3));
			goods.setGprice(rs.getInt(4));
			goods.setGkc(rs.getInt(5));
			goods.setGxl(rs.getInt(6));
			goods.setGtime(rs.getString(7));
			goods.setGpath(rs.getString(8));
			goods.setGinfo(rs.getString(9));
			goodsList.add(goods);
		}
		DBHelper.closeObj(con, ps, rs);
		return goodsList;
	}
	public boolean deleteGoods(Integer gid) throws SQLException {
		con = DBHelper.getCon();
		sql = "delete from tb_goods where gid = " + gid;
		ps = con.prepareStatement(sql);
		if(ps.executeUpdate()>0) {
			f = true;
		}
		DBHelper.closeObj(con, ps, rs);
		return f;
	}
	public boolean insertGoods(Goods goods) throws SQLException {
		con = DBHelper.getCon();
		sql = "insert into tb_goods(gname,tname,gprice,gkc,gpath,ginfo) values(?,?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setObject(1, goods.getGname());
		ps.setObject(2, goods.getTname());
		ps.setObject(3, goods.getGprice());
		ps.setObject(4, goods.getGkc());
		ps.setObject(5, goods.getGpath());
		ps.setObject(6, goods.getGinfo());
		if(ps.executeUpdate() > 0) f = true;
		DBHelper.closeObj(con, ps, rs);
		return f;
	}

	public Goods queryByGid(Integer gid) throws SQLException{
		con = DBHelper.getCon();
		sql = "select * from tb_goods t where gid = " + gid;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) {
			goods = new Goods();
			goods.setGid(rs.getInt(1));
			goods.setGname(rs.getString(2));
			goods.setTname(rs.getString(3));
			goods.setGprice(rs.getDouble(4));
			goods.setGxl(rs.getInt(5));
			goods.setGkc(rs.getInt(6));
			goods.setGtime(rs.getString(7));
			goods.setGpath(rs.getString(8));
			goods.setGinfo(rs.getString(9));
		}
		DBHelper.closeObj(con, ps, rs);
		return goods;
	}
	public boolean updateGoods(Goods goods) throws SQLException {
		con = DBHelper.getCon();
		sql = "update tb_goods set gname=?,tname=?,gprice=?,gkc=?,gpath=?,ginfo=? where gid = " + goods.getGid();
		ps = con.prepareStatement(sql);
		System.out.println("sql = " + sql);
		ps.setObject(1, goods.getGname());
		ps.setObject(2, goods.getTname());
		ps.setObject(3, goods.getGprice());
		ps.setObject(4, goods.getGkc());
		ps.setObject(5, goods.getGpath());
		ps.setObject(6, goods.getGinfo());
		if(ps.executeUpdate() > 0) f = true;
		DBHelper.closeObj(con, ps, rs);
		return f;
	}

	public boolean updateGoodsGkc(Integer gid,Integer num) throws SQLException {
		con = DBHelper.getCon();
		sql = "update tb_goods set gkc=gkc-"+num+",gxl=gxl+"+num+" where gid = " + gid;
		ps = con.prepareStatement(sql);
		if(ps.executeUpdate() > 0) f = true;
		DBHelper.closeObj(con, ps, rs);
		return f;
	}
	public Integer getRows(String gname) throws SQLException{
		con = DBHelper.getCon();
		sql = "select count(*) from tb_goods where gname like '%"+gname+"%'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) rows = rs.getInt(1);
		DBHelper.closeObj(con,ps,rs);
		return rows;
	}

	public static void main(String[] args) throws Exception{
		GoodsDao goodsDao = new GoodsDao();
		goodsDao.queryAll("",null).forEach(System.out::println);
	}

}
