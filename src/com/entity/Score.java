package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

// 学生成绩表的实体类
public class Score {
	private String scoreid = "S"+VeDate.getStringId(); // 生成主键编号
	private String usersid; // 学生
	private String courseid; // 课程
	private String num; // 成绩
	private String addtime; // 日期
	private String memo; // 备注
	private String realname; // 映射数据
	private String coursename; // 映射数据
	private Users users;// 多对一映射类
	private Course course;// 多对一映射类
	public String getScoreid() {
		return this.scoreid;
	}

	public void setScoreid(String scoreid) {
		this.scoreid = scoreid;
	}

	public String getUsersid() {
		return this.usersid;
	}

	public void setUsersid(String usersid) {
		this.usersid = usersid;
	}

	public String getCourseid() {
		return this.courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
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

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCoursename() {
		return this.coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}


	// 重载方法 生成JSON类型字符串 
	@Override
	public String toString() {
		return this.toJsonString();
	}

	//直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("scoreid", this.scoreid); // 主键编号
		jsonString.put("usersid", this.usersid); // 学生
		jsonString.put("courseid", this.courseid); // 课程
		jsonString.put("num", this.num); // 成绩
		jsonString.put("addtime", this.addtime); // 日期
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Users", this.users); // 多对一映射类
		jsonString.put("Course", this.course); // 多对一映射类
		jsonString.put("realname", this.realname); // 映射数据
		jsonString.put("coursename", this.coursename); // 映射数据
		return jsonString.toString();
	}




}




