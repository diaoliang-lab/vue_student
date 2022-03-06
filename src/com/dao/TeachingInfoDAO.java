package com.dao;

import com.entity.TeachingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("teachingInfoDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到UsersServiceImpl中
public interface TeachingInfoDAO {
    List<TeachingInfo> getAllTeachingInfo();

    int deleteTeachingInfo(String id);

    TeachingInfo getTeachingInfoById(String id);

    List<TeachingInfo> getTeachingInfosByCourseId(String courseId);

    int insertTeachingInfo(TeachingInfo teachingInfo);

    int updateTeachingInfo(TeachingInfo teachingInfo);


}
