package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.entity.Teacher;
import com.entity.TeachingInfo;
import com.entity.Users;
import com.github.pagehelper.Page;
import com.service.TeachersService;
import com.util.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/teachers", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class TeachersController {
    @Autowired
    TeachersService teachersService;

    // 通过AJAX在表格中显示教師用户数据
    @GetMapping(value = "getUsersByPage.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> getUsersByPage(@RequestParam(defaultValue = "1") Integer page,
                                              @RequestParam(defaultValue = "10") Integer limit) {
        // 定义一个Map对象 用来返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Teacher> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
        List<Teacher> list = this.teachersService.getAllTeachers();
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


    // 新增学生用户
    @PostMapping(value = "insertTeacher.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> insertTeacher(@RequestBody String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
        Teacher teacher = new Teacher();
        teacher.setUsername(obj.getString("username")); // 为用户名赋值
        teacher.setPassword(obj.getString("password")); // 为密码赋值
        teacher.setRealname(obj.getString("realname")); // 为姓名赋值
        teacher.setSex(obj.getString("sex")); // 为性别赋值
        teacher.setAge(obj.getString("age")); // 为出生日期赋值
        teacher.setContact(obj.getString("contact")); // 为联系方式赋值
        int num = this.teachersService.insertTeacher(teacher);
        if (num > 0) {
            map.put("success", true);
            map.put("code", num);
            map.put("message", "保存成功");
        } else {
            map.put("success", false);
            map.put("code", num);
            map.put("message", "保存失败");
        }
        return map;
    }

    // 按主键删除一个学生用户
    @GetMapping(value = "deleteTeacher.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> deleteTeacher(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        int num = this.teachersService.deleteTeacher(id);
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
    @PostMapping(value = "deleteTeacherByIds.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> deleteUsersByIds(@RequestBody String[] ids) {
        int num = 0;
        for (String id : ids) {
            num += this.teachersService.deleteTeacher(id);
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

    // 修改学生用户
    @PostMapping(value = "updateTeacher.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> updateUsers(@RequestBody String jsonStr) {
        JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
        Teacher teacher = this.teachersService.getTeacherById(obj.getString("id")); //
        teacher.setUsername(obj.getString("username")); // 为用户名赋值
        teacher.setRealname(obj.getString("realname")); // 为姓名赋值
        teacher.setSex(obj.getString("sex")); // 为性别赋值
        teacher.setAge(obj.getString("age")); // 为年龄
        teacher.setContact(obj.getString("contact")); // 为联系方式赋值

        Map<String, Object> map = new HashMap<String, Object>();
        int num = this.teachersService.updateTeacher(teacher);
        if (num > 0) {
            map.put("success", true);
            map.put("code", num);
            map.put("message", "修改成功");
        } else {
            map.put("success", false);
            map.put("code", num);
            map.put("message", "修改失败");
        }
        return map;
    }


    @GetMapping(value = "getTeacherById.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Teacher getTeacherById(String id) {
        Teacher teacher = this.teachersService.getTeacherById(id);
        return teacher;
    }

    // 通过AJAX在表格中显示学生用户数据
    @GetMapping(value = "getTeachers.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public Map<String, Object> queryTeachers(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit, String keywords) {
        // 定义一个Map对象 用来返回数据
        Map<String, Object> map = new HashMap<String, Object>();
        Page<Teacher> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
        Teacher teacher = new Teacher();
        teacher.setUsername(keywords);
        List<Teacher> list = this.teachersService.getTeachersByLike(teacher);
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


    // 查询全部学生用户数据 在下拉菜单中显示
    @GetMapping(value = "getAllTeachers.action")
    @ResponseBody // 将java对象转为json格式的数据返回
    public List<Teacher> getAllTeachers() {
        return teachersService.getAllTeachers();
    }


}
