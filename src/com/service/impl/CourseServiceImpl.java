package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CourseDAO;
import com.entity.Course;
import com.service.CourseService;

@Service("courseService") //
public class CourseServiceImpl implements CourseService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private CourseDAO courseDAO;
	@Override // 继承接口的新增课程表数据 返回值0(失败),1(成功)
	public int insertCourse(Course course) {
		return this.courseDAO.insertCourse(course);
	}

	@Override // 继承接口的更新课程表数据 返回值0(失败),1(成功)
	public int updateCourse(Course course) {
		return this.courseDAO.updateCourse(course);
	}

	@Override // 继承接口的按主键删除课程表数据 返回值0(失败),1(成功)
	public int deleteCourse(String courseid) {
		return this.courseDAO.deleteCourse(courseid);
	}

	@Override // 继承接口的批量删除课程表数据 返回值0(失败),大于0(成功)
	public int deleteCourseByIds(String[] ids) {
		return this.courseDAO.deleteCourseByIds(ids);
	}

	@Override // 继承接口的查询课程表全部数据
	public List<Course> getAllCourse() {
		return this.courseDAO.getAllCourse();
	}

	@Override // 继承接口的按条件精确查询课程表数据
	public List<Course> getCourseByCond(Course course) {
		return this.courseDAO.getCourseByCond(course);
	}

	@Override // 继承接口的按条件模糊查询课程表数据
	public List<Course> getCourseByLike(Course course) {
		return this.courseDAO.getCourseByLike(course);
	}

	@Override // 继承接口的按主键查询课程表数据 返回Entity实例
	public Course getCourseById(String courseid) {
		return this.courseDAO.getCourseById(courseid);
	}

}

