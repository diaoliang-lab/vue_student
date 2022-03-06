package com.service.impl;

import com.dao.TeachingInfoDAO;
import com.entity.TeachingInfo;
import com.service.TeachingInfoService;
import com.util.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingInfoServiceImpl implements TeachingInfoService {
    @Autowired
    TeachingInfoDAO teachingInfoDAO;

    @Override
    public List<TeachingInfo> getAllTeachingInfo() {
        List<TeachingInfo> allTeachingInfo = teachingInfoDAO.getAllTeachingInfo();
        for (TeachingInfo teachingInfo : allTeachingInfo) {
            teachingInfo.setBeginTimeStr(VeDate.getTimeStr(teachingInfo.getBeginTime()));
            teachingInfo.setEndTimeStr(VeDate.getTimeStr(teachingInfo.getEndTime()));
        }
        return allTeachingInfo;
    }

    @Override
    public int deleteTeachingInfo(String id) {
        return teachingInfoDAO.deleteTeachingInfo(id);
    }

    @Override
    public TeachingInfo getTeachingInfoById(String id) {
        return teachingInfoDAO.getTeachingInfoById(id);
    }

    @Override
    public List<TeachingInfo> getTeachingInfosByCourseId(String courseId) {
        return teachingInfoDAO.getTeachingInfosByCourseId(courseId);
    }

    @Override
    public int insertTeachingInfo(TeachingInfo teachingInfo) {
        return teachingInfoDAO.insertTeachingInfo(teachingInfo);
    }

    @Override
    public int updateTeachingInfo(TeachingInfo teachingInfo) {
        return teachingInfoDAO.updateTeachingInfo(teachingInfo);
    }
}
