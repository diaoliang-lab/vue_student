package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Course;
@Service("courseService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface CourseService {
	// 插入课程表数据 调用courseDAO里的insertCourse配置
	public int insertCourse(Course course);

	// 更新课程表数据 调用courseDAO里的updateCourse配置
	public int updateCourse(Course course);

	// 按主键删除课程表数据 调用courseDAO里的deleteCourse配置
	public int deleteCourse(String courseid);

	// 批量删除课程表数据 调用mapper包course.xml里的deleteCourseByIds配置 返回值0(失败),大于0(成功)
	public int deleteCourseByIds(String[] ids);

	// 查询全部数据 调用courseDAO里的getAllCourse配置
	public List<Course> getAllCourse();

	// 按照Course类里面的字段名称精确查询 调用courseDAO里的getCourseByCond配置
	public List<Course> getCourseByCond(Course course);

	// 按照Course类里面的字段名称模糊查询 调用courseDAO里的getCourseByLike配置
	public List<Course> getCourseByLike(Course course);

	// 按主键查询表返回单一的Course实例 调用courseDAO里的getCourseById配置
	public Course getCourseById(String courseid);

}

