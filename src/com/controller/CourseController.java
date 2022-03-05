package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.entity.Course;
import com.github.pagehelper.Page;
import com.service.CourseService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/course", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class CourseController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private CourseService courseService;

	// 预处理 获取基础参数
	@GetMapping(value = "createCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createCourse() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("cno", "C" + VeDate.getStringDatex());
		return map;
	}

	// 新增课程
	@PostMapping(value = "insertCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertCourse(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Course course = new Course();
		course.setCno(obj.getString("cno")); // 为课程号赋值
		course.setCoursename(obj.getString("coursename")); // 为课程名赋值
		course.setTeachername(obj.getString("teachername")); // 为教师赋值
		course.setClazzid(obj.getString("clazzid")); // 为上课班级赋值
		course.setNum(obj.getString("num")); // 为学分赋值
		course.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值
		course.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.courseService.insertCourse(course);
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

	// 按主键删除一个课程
	@GetMapping(value = "deleteCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteCourse(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.courseService.deleteCourse(id);
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

	// 按主键批量删除课程
	@PostMapping(value = "deleteCourseByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteCourseByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String courseid : ids) {
			num += this.courseService.deleteCourse(courseid);
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

	// 修改课程
	@PostMapping(value = "updateCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateCourse(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Course course = this.courseService.getCourseById(obj.getString("courseid")); // 获取object中courseid字段
		course.setCno(obj.getString("cno")); // 为课程号赋值
		course.setCoursename(obj.getString("coursename")); // 为课程名赋值
		course.setTeachername(obj.getString("teachername")); // 为教师赋值
		course.setClazzid(obj.getString("clazzid")); // 为上课班级赋值
		course.setNum(obj.getString("num")); // 为学分赋值
		course.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.courseService.updateCourse(course);
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

	// 查询全部课程数据 在下拉菜单中显示
	@GetMapping(value = "getAllCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Course> getAllCourse() {
		return this.courseService.getAllCourse();
	}

	// 通过AJAX在表格中显示课程数据
	@GetMapping(value = "getCourseByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getCourseByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Course> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Course> list = this.courseService.getAllCourse();
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

	// 通过AJAX在表格中显示课程数据
	@GetMapping(value = "getCourse.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> queryCourse(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Course> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Course course = new Course();
		course.setClazzname(keywords);
		List<Course> list = this.courseService.getCourseByLike(course);
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

	// 按主键查询课程数据
	@GetMapping(value = "getCourseById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Course getCourseById(String id) {
		Course course = this.courseService.getCourseById(id);
		return course;
	}

	// TODO Auto-generated method stub
}
