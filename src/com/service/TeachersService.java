package com.service;

import com.entity.Teacher;
import com.entity.Users;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("teachersService")
public interface TeachersService {

    public List<Teacher> getAllTeachers();


    public int insertTeacher(Teacher teacher);


    public int updateTeacher(Teacher teacher);


    public int deleteTeacher(String id);

    public Teacher getTeacherById(String id);

    public List<Teacher> getTeachersByLike(Teacher teacher);
}
