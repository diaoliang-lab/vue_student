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
import com.entity.Works;
import com.github.pagehelper.Page;
import com.service.WorksService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/works", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class WorksController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private WorksService worksService;

	// 预处理 获取基础参数
	@GetMapping(value = "createWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createWorks() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增勤工俭学
	@PostMapping(value = "insertWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertWorks(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Works works = new Works();
		works.setWorksname(obj.getString("worksname")); // 为工作名称赋值
		works.setCateid(obj.getString("cateid")); // 为工作类型赋值
		works.setSalary(obj.getString("salary")); // 为薪资赋值
		works.setThestart(obj.getString("thestart")); // 为开始日期赋值
		works.setTheend(obj.getString("theend")); // 为结束日期赋值
		works.setAddress(obj.getString("address")); // 为工作地点赋值
		works.setHits("0"); // 为点击数赋值
		works.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值
		works.setContents(obj.getString("contents")); // 为工作介绍赋值
		int num = this.worksService.insertWorks(works);
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

	// 按主键删除一个勤工俭学
	@GetMapping(value = "deleteWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteWorks(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.worksService.deleteWorks(id);
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

	// 按主键批量删除勤工俭学
	@PostMapping(value = "deleteWorksByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteWorksByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String worksid : ids) {
			num += this.worksService.deleteWorks(worksid);
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

	// 修改勤工俭学
	@PostMapping(value = "updateWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateWorks(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Works works = this.worksService.getWorksById(obj.getString("worksid")); // 获取object中worksid字段
		works.setWorksname(obj.getString("worksname")); // 为工作名称赋值
		works.setCateid(obj.getString("cateid")); // 为工作类型赋值
		works.setSalary(obj.getString("salary")); // 为薪资赋值
		works.setThestart(obj.getString("thestart")); // 为开始日期赋值
		works.setTheend(obj.getString("theend")); // 为结束日期赋值
		works.setAddress(obj.getString("address")); // 为工作地点赋值
		works.setContents(obj.getString("contents")); // 为工作介绍赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.worksService.updateWorks(works);
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

	// 查询全部勤工俭学数据 在下拉菜单中显示
	@GetMapping(value = "getAllWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Works> getAllWorks() {
		return this.worksService.getAllWorks();
	}

	// 通过AJAX在表格中显示勤工俭学数据
	@GetMapping(value = "getWorksByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getWorksByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Works> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Works> list = this.worksService.getAllWorks();
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

	// 通过AJAX在表格中显示勤工俭学数据
	@GetMapping(value = "getWorks.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> queryWorks(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Works> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Works works = new Works();
		works.setWorksname(keywords);
		List<Works> list = this.worksService.getWorksByLike(works);
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

	// 按主键查询勤工俭学数据
	@GetMapping(value = "getWorksById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Works getWorksById(String id) {
		Works works = this.worksService.getWorksById(id);
		return works;
	}

	// TODO Auto-generated method stub
}
