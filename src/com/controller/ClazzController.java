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
import com.entity.Clazz;
import com.github.pagehelper.Page;
import com.service.ClazzService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/clazz", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ClazzController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ClazzService clazzService;

	// 预处理 获取基础参数
	@GetMapping(value = "createClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createClazz() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增班级
	@PostMapping(value = "insertClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertClazz(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Clazz clazz = new Clazz();
		clazz.setClazzname(obj.getString("clazzname")); // 为班级名称赋值
		clazz.setManager(obj.getString("manager")); // 为负责人赋值
		clazz.setContact(obj.getString("contact")); // 为联系方式赋值
		clazz.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值
		clazz.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.clazzService.insertClazz(clazz);
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

	// 按主键删除一个班级
	@GetMapping(value = "deleteClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteClazz(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.clazzService.deleteClazz(id);
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

	// 按主键批量删除班级
	@PostMapping(value = "deleteClazzByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteClazzByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String clazzid : ids) {
			num += this.clazzService.deleteClazz(clazzid);
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

	// 修改班级
	@PostMapping(value = "updateClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateClazz(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Clazz clazz = this.clazzService.getClazzById(obj.getString("clazzid")); // 获取object中clazzid字段
		clazz.setClazzname(obj.getString("clazzname")); // 为班级名称赋值
		clazz.setManager(obj.getString("manager")); // 为负责人赋值
		clazz.setContact(obj.getString("contact")); // 为联系方式赋值
		clazz.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.clazzService.updateClazz(clazz);
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

	// 查询全部班级数据 在下拉菜单中显示
	@GetMapping(value = "getAllClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Clazz> getAllClazz() {
		return this.clazzService.getAllClazz();
	}

	// 通过AJAX在表格中显示班级数据
	@GetMapping(value = "getClazzByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getClazzByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Clazz> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Clazz> list = this.clazzService.getAllClazz();
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

	// 通过AJAX在表格中显示班级数据
	@GetMapping(value = "getClazz.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> queryClazz(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Clazz> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Clazz clazz = new Clazz();
		clazz.setClazzname(keywords);
		List<Clazz> list = this.clazzService.getClazzByLike(clazz);
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

	// 按主键查询班级数据
	@GetMapping(value = "getClazzById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Clazz getClazzById(String id) {
		Clazz clazz = this.clazzService.getClazzById(id);
		return clazz;
	}

	// TODO Auto-generated method stub
}
