package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 勤工俭学表的实体类
public class Works {
	private String worksid = "W"+VeDate.getStringId(); // 生成主键编号
	private String worksname; // 工作名称
	private String cateid; // 工作类型
	private String salary; // 薪资
	private String thestart; // 开始日期
	private String theend; // 结束日期
	private String address; // 工作地点
	private String hits; // 点击数
	private String addtime; // 创建日期
	private String contents; // 工作介绍
	private String catename; // 映射数据
	private Cate cate;// 多对一映射类
	public String getWorksid() {
		return this.worksid;
	}

	public void setWorksid(String worksid) {
		this.worksid = worksid;
	}

	public String getWorksname() {
		return this.worksname;
	}

	public void setWorksname(String worksname) {
		this.worksname = worksname;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getThestart() {
		return this.thestart;
	}

	public void setThestart(String thestart) {
		this.thestart = thestart;
	}

	public String getTheend() {
		return this.theend;
	}

	public void setTheend(String theend) {
		this.theend = theend;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHits() {
		return this.hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Cate getCate() {
		return this.cate;
	}

	public void setCate(Cate cate) {
		this.cate = cate;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("worksid", this.worksid); // 主键编号
		jsonString.put("worksname", this.worksname); // 工作名称
		jsonString.put("cateid", this.cateid); // 工作类型
		jsonString.put("salary", this.salary); // 薪资
		jsonString.put("thestart", this.thestart); // 开始日期
		jsonString.put("theend", this.theend); // 结束日期
		jsonString.put("address", this.address); // 工作地点
		jsonString.put("hits", this.hits); // 点击数
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("contents", this.contents); // 工作介绍
		jsonString.put("Cate", this.cate); // 多对一映射类
		jsonString.put("catename", this.catename); // 映射数据
		return jsonString.toString();
	}




}




