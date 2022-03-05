package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Clazz;

@Repository("clazzDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到ClazzServiceImpl中
public interface ClazzDAO {

	/**
	* ClazzDAO 接口 可以按名称直接调用clazz.xml配置文件的SQL语句
	*/

	// 插入班级表数据 调用mapper包clazz.xml里的insertClazz配置 返回值0(失败),1(成功)
	public int insertClazz(Clazz clazz);

	// 更新班级表数据 调用mapper包clazz.xml里的updateClazz配置 返回值0(失败),1(成功)
	public int updateClazz(Clazz clazz);

	// 按主键删除班级表数据 调用mapper包clazz.xml里的deleteClazz配置 返回值0(失败),1(成功)
	public int deleteClazz(String clazzid);

	// 批量删除班级表数据 调用mapper包clazz.xml里的deleteClazzByIds配置 返回值0(失败),大于0(成功)
	public int deleteClazzByIds(String[] ids);

	// 查询班级表全部数据 调用mapper包clazz.xml里的getAllClazz配置 返回List<Clazz>类型的数据
	public List<Clazz> getAllClazz();

	// 按照Clazz类里面的值精确查询 调用mapper包clazz.xml里的getClazzByCond配置 返回List<Clazz>类型的数据
	public List<Clazz> getClazzByCond(Clazz clazz);

	// 按照Clazz类里面的值模糊查询 调用mapper包clazz.xml里的getClazzByLike配置 返回List<Clazz>类型的数据
	public List<Clazz> getClazzByLike(Clazz clazz);

	// 按主键查询班级表返回单一的Clazz实例 调用mapper包clazz.xml里的getClazzById配置
	public Clazz getClazzById(String clazzid);

}


