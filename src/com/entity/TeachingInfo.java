package com.entity;

import com.util.VeDate;

//授课信息实体表
public class TeachingInfo {
    private String id ="TI"+ VeDate.getStringId();
    private String clazzId;
    private String clazzName;
    private String teacherId;
    private String username;//教师名
    private String courseId;
    private String courseName;
    private String beginTimeStr;
    private Integer beginTime;
    private Integer endTime;
    private String endTimeStr;

    @Override
    public String toString() {
        return "TeachingInfo{" +
                "id='" + id + '\'' +
                ", clazzId='" + clazzId + '\'' +
                ", clazzname='" + clazzName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + username + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", beginTimeStr='" + beginTimeStr + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", endTimeStr='" + endTimeStr + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazzId() {
        return clazzId;
    }

    public void setClazzId(String clazzId) {
        this.clazzId = clazzId;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
