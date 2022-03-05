package com.controller;


import com.entity.Course;
import com.entity.TeachingInfo;
import com.entity.Users;
import com.github.pagehelper.Page;
import com.service.CourseService;
import com.service.TeachingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/teachingInfo", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class TeachingInfoController {
    @Autowired
    TeachingInfoService teachingInfoService;
    @Autowired
    CourseService courseService;

    // 通过AJAX在表格中显示学生用户数据
    @GetMapping(value = "getTeachingInfosByPage.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> getTeachingInfoByPage(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer limit) {
        // 定义一个Map对象 用来返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        Page<TeachingInfo> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
        List<TeachingInfo> list = this.teachingInfoService.getAllTeachingInfo();
        // 返回的map中定义数据格式
        map.put("count", pager.getTotal());
        map.put("total", list.size());
        map.put("data", list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("page", page);
        map.put("limit", limit);
        return map;
    }



    // 按主键删除一个学生用户
    @GetMapping(value = "deleteTeachingInfo.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> deleteTeachingInfo(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int num = this.teachingInfoService.deleteTeachingInfo(id);
        if (num > 0) {
            map.put("success", true);
            map.put("code", num);
            map.put("message", "删除成功");
        } else {
            map.put("success", false);
            map.put("code", num);
            map.put("message", "删除失败");
        }
        return map;
    }

    // 按主键批量删除学生用户
    @PostMapping(value = "deleteUsersByIds.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> deleteUsersByIds(@RequestBody String[] ids) {
        int num = 0;
        for (String id : ids) {
            num += this.teachingInfoService.deleteTeachingInfo(id);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (num > 0) {
            map.put("success", true);
            map.put("code", num);
            map.put("message", "删除成功");
        } else {
            map.put("success", false);
            map.put("code", num);
            map.put("message", "删除失败");
        }
        return map;
    }


    // 按主键查询学生用户数据
    @GetMapping(value = "getTeachingInfoById.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public TeachingInfo getTeachingInfoById(String id) {
        TeachingInfo teachingInfo = this.teachingInfoService.getTeachingInfoById(id);
        return teachingInfo;
    }

    // 通过AJAX在表格中显示学生用户数据
    @GetMapping(value = "getTeachingInfos.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> queryTeachingInfos(@RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer limit, String keywords) {
        // 定义一个Map对象 用来返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        Page<TeachingInfo> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
        Course course = new Course();
        course.setCoursename(keywords);
        List<Course> courseByLike = this.courseService.getCourseByLike(course);
        List<TeachingInfo> result = new ArrayList<>();
        for (Course course1 : courseByLike) {
            List<TeachingInfo> teachingInfosByCourseId = teachingInfoService.getTeachingInfosByCourseId(course1.getCourseid());
            result.addAll(teachingInfosByCourseId);
        }
        // 返回的map中定义数据格式
        map.put("count", pager.getTotal());
        map.put("total", result.size());
        map.put("data", result);
        map.put("code", 0);
        map.put("msg", "");
        map.put("page", page);
        map.put("limit", limit);
        return map;
    }

}
