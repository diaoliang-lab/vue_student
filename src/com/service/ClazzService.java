package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Clazz;
@Service("clazzService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface ClazzService {
	// 插入班级表数据 调用clazzDAO里的insertClazz配置
	public int insertClazz(Clazz clazz);

	// 更新班级表数据 调用clazzDAO里的updateClazz配置
	public int updateClazz(Clazz clazz);

	// 按主键删除班级表数据 调用clazzDAO里的deleteClazz配置
	public int deleteClazz(String clazzid);

	// 批量删除班级表数据 调用mapper包clazz.xml里的deleteClazzByIds配置 返回值0(失败),大于0(成功)
	public int deleteClazzByIds(String[] ids);

	// 查询全部数据 调用clazzDAO里的getAllClazz配置
	public List<Clazz> getAllClazz();

	// 按照Clazz类里面的字段名称精确查询 调用clazzDAO里的getClazzByCond配置
	public List<Clazz> getClazzByCond(Clazz clazz);

	// 按照Clazz类里面的字段名称模糊查询 调用clazzDAO里的getClazzByLike配置
	public List<Clazz> getClazzByLike(Clazz clazz);

	// 按主键查询表返回单一的Clazz实例 调用clazzDAO里的getClazzById配置
	public Clazz getClazzById(String clazzid);

}

