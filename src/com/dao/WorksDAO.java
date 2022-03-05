package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Works;

@Repository("worksDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到WorksServiceImpl中
public interface WorksDAO {

	/**
	* WorksDAO 接口 可以按名称直接调用works.xml配置文件的SQL语句
	*/

	// 插入勤工俭学表数据 调用mapper包works.xml里的insertWorks配置 返回值0(失败),1(成功)
	public int insertWorks(Works works);

	// 更新勤工俭学表数据 调用mapper包works.xml里的updateWorks配置 返回值0(失败),1(成功)
	public int updateWorks(Works works);

	// 按主键删除勤工俭学表数据 调用mapper包works.xml里的deleteWorks配置 返回值0(失败),1(成功)
	public int deleteWorks(String worksid);

	// 批量删除勤工俭学表数据 调用mapper包works.xml里的deleteWorksByIds配置 返回值0(失败),大于0(成功)
	public int deleteWorksByIds(String[] ids);

	// 查询勤工俭学表全部数据 调用mapper包works.xml里的getAllWorks配置 返回List<Works>类型的数据
	public List<Works> getAllWorks();

	// 按照Works类里面的值精确查询 调用mapper包works.xml里的getWorksByCond配置 返回List<Works>类型的数据
	public List<Works> getWorksByCond(Works works);

	// 按照Works类里面的值模糊查询 调用mapper包works.xml里的getWorksByLike配置 返回List<Works>类型的数据
	public List<Works> getWorksByLike(Works works);

	// 按主键查询勤工俭学表返回单一的Works实例 调用mapper包works.xml里的getWorksById配置
	public Works getWorksById(String worksid);

}


