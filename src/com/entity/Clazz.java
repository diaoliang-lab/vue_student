package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 班级表的实体类
public class Clazz {
	private String clazzid = "C"+VeDate.getStringId(); // 生成主键编号
	private String clazzname; // 班级名称
	private String manager; // 负责人
	private String contact; // 联系方式
	private String addtime; // 创建日期
	private String memo; // 备注
	public String getClazzid() {
		return this.clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}

	public String getClazzname() {
		return this.clazzname;
	}

	public void setClazzname(String clazzname) {
		this.clazzname = clazzname;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("clazzid", this.clazzid); // 主键编号
		jsonString.put("clazzname", this.clazzname); // 班级名称
		jsonString.put("manager", this.manager); // 负责人
		jsonString.put("contact", this.contact); // 联系方式
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		return jsonString.toString();
	}




}




