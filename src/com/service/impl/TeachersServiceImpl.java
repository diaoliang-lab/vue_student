package com.service.impl;

import com.dao.TeachersDAO;
import com.entity.Teacher;
import com.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teachersService")
public class TeachersServiceImpl implements TeachersService {
    @Autowired
    TeachersDAO teachersDAO;
    @Override
    public List<Teacher> getAllTeachers() {
        return teachersDAO.getAllTeachers();
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        return teachersDAO.insertTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teachersDAO.updateTeacher(teacher);
    }

    @Override
    public int deleteTeacher(String id) {
        return teachersDAO.deleteTeacher(id);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teachersDAO.getTeacherById(id);
    }

    @Override
    public List<Teacher> getTeachersByLike(Teacher teacher) {
        return teachersDAO.getTeachersByLike(teacher);
    }
}
