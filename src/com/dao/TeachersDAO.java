package com.dao;


import com.entity.Teacher;
import com.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teachersDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到UsersServiceImpl中
public interface TeachersDAO {


    public List<Teacher> getAllTeachers();


    public int insertTeacher(Teacher teacher);


    public int updateTeacher(Teacher teacher);


    public int deleteTeacher(String id);

    public Teacher getTeacherById(String id);

    public List<Teacher> getTeachersByLike(Teacher teacher);
}
