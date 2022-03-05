package com.service;

import com.entity.TeachingInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeachingInfoService {
    List<TeachingInfo> getAllTeachingInfo();

    int deleteTeachingInfo(String id);

    TeachingInfo getTeachingInfoById(String id);

    List<TeachingInfo> getTeachingInfosByCourseId(String courseId);
}
