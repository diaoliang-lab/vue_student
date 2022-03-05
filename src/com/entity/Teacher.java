package com.entity;

import com.util.VeDate;

import java.util.Objects;

public class Teacher {
    private String id = "T"+ VeDate.getStringId(); // 生成主键编号
    private String username; // 用户名
    private String password; // 密码
    private String realname; // 姓名


    private String sex; // 性别
    private String contact; // 联系方式
    private String age; //年齡

    public Teacher() {
    }

    public Teacher(String id, String username, String password, String realname, String sex, String contact, String age) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.sex = sex;
        this.contact = contact;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(username, teacher.username) && Objects.equals(password, teacher.password) && Objects.equals(realname, teacher.realname) && Objects.equals(sex, teacher.sex) && Objects.equals(contact, teacher.contact) && Objects.equals(age, teacher.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, realname, sex, contact, age);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
