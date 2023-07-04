package com.orderingsystem.entity;

import java.io.Serializable;

public class GoodsType implements Serializable{
	private Integer tid;// 类别编号
	private String tname;// 类别名称
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public GoodsType(Integer tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}
	public GoodsType(String tname) {
		this.tname = tname;
	}
	public GoodsType() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GoodsType [tid=" + tid + ", tname=" + tname + "]";
	}
	
}
