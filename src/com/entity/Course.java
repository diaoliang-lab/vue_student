package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 课程表的实体类
public class Course {
	private String courseid = "C"+VeDate.getStringId(); // 生成主键编号
	private String cno; // 课程号
	private String coursename; // 课程名
	private String teachername; // 教师
	private String clazzid; // 上课班级
	private String num; // 学分
	private String addtime; // 创建日期
	private String memo; // 备注
	private String clazzname; // 映射数据
	private Clazz clazz;// 多对一映射类
	public String getCourseid() {
		return this.courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getClazzid() {
		return this.clazzid;
	}

	public void setClazzid(String clazzid) {
		this.clazzid = clazzid;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
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
		jsonString.put("courseid", this.courseid); // 主键编号
		jsonString.put("cno", this.cno); // 课程号
		jsonString.put("coursename", this.coursename); // 课程名
		jsonString.put("teachername", this.teachername); // 教师
		jsonString.put("clazzid", this.clazzid); // 上课班级
		jsonString.put("num", this.num); // 学分
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Clazz", this.clazz); // 多对一映射类
		jsonString.put("clazzname", this.clazzname); // 映射数据
		return jsonString.toString();
	}




}




