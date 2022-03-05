package com.controller;

import java.util.ArrayList;
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
import com.entity.Article;
import com.entity.Banner;
import com.entity.Course;
import com.entity.Score;
import com.entity.Users;
import com.entity.Works;
import com.github.pagehelper.Page;
import com.service.ArticleService;
import com.service.BannerService;
import com.service.CourseService;
import com.service.ScoreService;
import com.service.UsersService;
import com.service.WorksService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/index", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class IndexController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ArticleService articleService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BannerService bannerService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private CourseService courseService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private ScoreService scoreService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private UsersService usersService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private WorksService worksService;
	// TODO Auto-generated method stub

	@GetMapping(value = "front.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> front() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Banner> bannerList = this.bannerService.getAllBanner();
		map.put("bannerList", bannerList);
		return map;
	}

	// 前台首页
	@GetMapping(value = "index.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> index() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Banner> bannerList = this.bannerService.getAllBanner();
		List<Banner> frontList = new ArrayList<Banner>();
		for (Banner banner : bannerList) {
			List<Article> articleList = this.articleService.getArticleByBanner(banner.getBannerid());
			banner.setArticleList(articleList);
			frontList.add(banner);
		}
		map.put("frontList", frontList);
		List<Article> topList = this.articleService.getTopArticle();
		map.put("topList", topList);
		List<Article> favList = this.articleService.getFlvArticle();
		map.put("favList", favList);
		return map;
	}

	@GetMapping(value = "article.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> article(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "20") Integer limit, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Article> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Banner banner = this.bannerService.getBannerById(id);
		Article article = new Article();
		article.setBannerid(id);
		List<Article> list = this.articleService.getArticleByCond(article);
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		map.put("banner", banner);
		return map;
	}

	@GetMapping(value = "read.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> read(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Article article = this.articleService.getArticleById(id);
		article.setHits("" + (Integer.parseInt(article.getHits()) + 1));
		this.articleService.updateArticle(article);
		map.put("article", article);
		return map;
	}

	// 用户登录
	@PostMapping(value = "login.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> login(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String username = obj.getString("username");
		String password = obj.getString("password");
		Users usersEntity = new Users();
		usersEntity.setUsername(username);
		List<Users> userslist = this.usersService.getUsersByCond(usersEntity);
		if (userslist.size() == 0) {
			map.put("success", false);
			map.put("message", "用户名不存在");
		} else {
			Users users = userslist.get(0);
			if (password.equals(users.getPassword())) {
				map.put("success", true);
				map.put("message", "登录成功");
				map.put("userid", users.getUsersid());
				map.put("username", users.getUsername());
				map.put("realname", users.getRealname());
				map.put("clazzid", users.getClazzid());
			} else {
				map.put("success", false);
				map.put("message", "密码错误");
			}
		}
		return map;
	}

	// 用户注册
	@PostMapping(value = "register.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> register(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = new Users();
		users.setUsername(obj.getString("username"));
		users.setPassword(obj.getString("password"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		users.setRegdate(VeDate.getStringDateShort());
		int num = this.usersService.insertUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "注册成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "注册失败");
		}
		return map;
	}

	// 修改密码
	@PostMapping(value = "editpwd.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> editpwd(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		String userid = obj.getString("userid");
		String password = obj.getString("password");
		String repassword = obj.getString("repassword");
		int num = 0;
		Users users = this.usersService.getUsersById(userid);
		if (password.equals(users.getPassword())) {
			users.setPassword(repassword);
			num = this.usersService.updateUsers(users);
			if (num > 0) {
				map.put("success", true);
				map.put("code", num);
				map.put("message", "修改成功");
			} else {
				map.put("success", false);
				map.put("code", num);
				map.put("message", "修改失败");
			}
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "旧密码错误");
		}
		return map;
	}

	// 查看个人信息
	@GetMapping(value = "userinfo.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> userinfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Users users = this.usersService.getUsersById(id);
		map.put("users", users);
		return map;
	}

	// 修改个人信息
	@PostMapping(value = "personal.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> personal(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Users users = this.usersService.getUsersById(obj.getString("usersid"));
		users.setUsername(obj.getString("username"));
		users.setSex(obj.getString("sex"));
		users.setBirthday(obj.getString("birthday"));
		users.setContact(obj.getString("contact"));
		users.setRealname(obj.getString("realname"));
		users.setAddress(obj.getString("address"));
		int num = this.usersService.updateUsers(users);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "注册成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "注册失败");
		}
		return map;
	}

	@GetMapping(value = "myCourse.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> myCourse(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String clazzid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Course> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Course course = new Course();
		course.setClazzid(clazzid);
		List<Course> list = this.courseService.getCourseByCond(course);
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

	@GetMapping(value = "myScore.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> myScore(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Score> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Score score = new Score();
		score.setUsersid(userid);
		List<Score> list = this.scoreService.getScoreByCond(score);
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

	@GetMapping(value = "works.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> works(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		System.out.println("aaa");
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

	@GetMapping(value = "cate.action")
	@ResponseBody // 将java对象转为json格式的数据
	public Map<String, Object> cate(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit, String cateid) {

		Map<String, Object> map = new HashMap<String, Object>();
		Page<Works> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		Works works = new Works();
		works.setCateid(cateid);
		List<Works> list = this.worksService.getWorksByCond(works);
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

	// //
	@GetMapping(value = "worksdetail.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> worksdetail(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Works works = this.worksService.getWorksById(id);
		works.setHits("" + (Integer.parseInt(works.getHits()) + 1));
		this.worksService.updateWorks(works);
		map.put("works", works);
		return map;
	}
	// TODO Auto-generated method stub
}
