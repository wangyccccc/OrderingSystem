package com.orderingsystem.dao;

import com.orderingsystem.entity.GoodsType;
import com.orderingsystem.util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsTypeDao{
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	private String sql;
	private GoodsType goodsType;
	private List<GoodsType> goodsTypeList;
	private boolean f;

	public List<GoodsType> queryAll() throws SQLException{
		goodsTypeList = new ArrayList<>();
		con = DBHelper.getCon();
		sql = "select * from tb_type";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			goodsType = new GoodsType();
			goodsType.setTid(rs.getInt(1));
			goodsType.setTname(rs.getString(2));
			goodsTypeList.add(goodsType);
		}
		DBHelper.closeObj(con, ps, rs);
		return goodsTypeList;
	}
}
