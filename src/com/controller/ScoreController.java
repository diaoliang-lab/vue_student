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
import com.entity.Score;
import com.github.pagehelper.Page;
import com.service.ScoreService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/score", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class ScoreController extends BaseController {
	// TODO Auto-generated method stub

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ScoreService scoreService;

	// 预处理 获取基础参数
	@GetMapping(value = "createScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createScore() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增学生成绩
	@PostMapping(value = "insertScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertScore(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Score score = new Score();
		score.setUsersid(obj.getString("usersid")); // 为学生赋值
		score.setCourseid(obj.getString("courseid")); // 为课程赋值
		score.setNum(obj.getString("num")); // 为成绩赋值
		score.setAddtime(VeDate.getStringDateShort()); // 为日期赋值
		score.setMemo(obj.getString("memo")); // 为备注赋值
		int num = this.scoreService.insertScore(score);
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

	// 按主键删除一个学生成绩
	@GetMapping(value = "deleteScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteScore(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.scoreService.deleteScore(id);
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

	// 按主键批量删除学生成绩
	@PostMapping(value = "deleteScoreByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteScoreByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String scoreid : ids) {
			num += this.scoreService.deleteScore(scoreid);
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

	// 修改学生成绩
	@PostMapping(value = "updateScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateScore(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Score score = this.scoreService.getScoreById(obj.getString("scoreid")); // 获取object中scoreid字段
		score.setUsersid(obj.getString("usersid")); // 为学生赋值
		score.setCourseid(obj.getString("courseid")); // 为课程赋值
		score.setNum(obj.getString("num")); // 为成绩赋值
		score.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.scoreService.updateScore(score);
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

	// 查询全部学生成绩数据 在下拉菜单中显示
	@GetMapping(value = "getAllScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Score> getAllScore() {
		return this.scoreService.getAllScore();
	}

	// 通过AJAX在表格中显示学生成绩数据
	@GetMapping(value = "getScoreByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getScoreByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Score> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Score> list = this.scoreService.getAllScore();
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

	// 通过AJAX在表格中显示学生成绩数据
	@GetMapping(value = "getScore.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> queryScore(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String keywords) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Score> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Score score = new Score();
		score.setUsersid(keywords);
		List<Score> list = this.scoreService.getScoreByLike(score);
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

	// 按主键查询学生成绩数据
	@GetMapping(value = "getScoreById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Score getScoreById(String id) {
		Score score = this.scoreService.getScoreById(id);
		return score;
	}

	// TODO Auto-generated method stub
}
