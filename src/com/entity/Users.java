package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 学生用户表的实体类
public class Users {
	private String usersid = "U"+VeDate.getStringId(); // 生成主键编号
	private String username; // 用户名
	private String password; // 密码
	private String realname; // 姓名
	private String sex; // 性别
	private String birthday; // 出生日期
	private String clazzid; // 所在班级
	private String contact; // 联系方式
	private String address; // 家庭住址
	private String regdate; // 注册日期
	private String clazzname; // 映射数据
	private Clazz clazz;// 多对一映射类
	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getClazzid() {
		return this.clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegdate() {
		return this.regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public Clazz getClazz() {
		return this.clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getClazzname() {
		return this.clazzname;
	}

	public void setClazzname(String clazzname) {
		this.clazzname = clazzname;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("usersid", this.usersid); // 主键编号
		jsonString.put("username", this.username); // 用户名
		jsonString.put("password", this.password); // 密码
		jsonString.put("realname", this.realname); // 姓名
		jsonString.put("sex", this.sex); // 性别
		jsonString.put("birthday", this.birthday); // 出生日期
		jsonString.put("clazzid", this.clazzid); // 所在班级
		jsonString.put("contact", this.contact); // 联系方式
		jsonString.put("address", this.address); // 家庭住址
		jsonString.put("regdate", this.regdate); // 注册日期
		jsonString.put("Clazz", this.clazz); // 多对一映射类
		jsonString.put("clazzname", this.clazzname); // 映射数据
		return jsonString.toString();
	}




}




