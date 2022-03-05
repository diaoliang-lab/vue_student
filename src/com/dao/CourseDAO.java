package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Course;

@Repository("courseDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到CourseServiceImpl中
public interface CourseDAO {

	/**
	* CourseDAO 接口 可以按名称直接调用course.xml配置文件的SQL语句
	*/

	// 插入课程表数据 调用mapper包course.xml里的insertCourse配置 返回值0(失败),1(成功)
	public int insertCourse(Course course);

	// 更新课程表数据 调用mapper包course.xml里的updateCourse配置 返回值0(失败),1(成功)
	public int updateCourse(Course course);

	// 按主键删除课程表数据 调用mapper包course.xml里的deleteCourse配置 返回值0(失败),1(成功)
	public int deleteCourse(String courseid);

	// 批量删除课程表数据 调用mapper包course.xml里的deleteCourseByIds配置 返回值0(失败),大于0(成功)
	public int deleteCourseByIds(String[] ids);

	// 查询课程表全部数据 调用mapper包course.xml里的getAllCourse配置 返回List<Course>类型的数据
	public List<Course> getAllCourse();

	// 按照Course类里面的值精确查询 调用mapper包course.xml里的getCourseByCond配置 返回List<Course>类型的数据
	public List<Course> getCourseByCond(Course course);

	// 按照Course类里面的值模糊查询 调用mapper包course.xml里的getCourseByLike配置 返回List<Course>类型的数据
	public List<Course> getCourseByLike(Course course);

	// 按主键查询课程表返回单一的Course实例 调用mapper包course.xml里的getCourseById配置
	public Course getCourseById(String courseid);

}


