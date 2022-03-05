package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Works;
@Service("worksService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface WorksService {
	// 插入勤工俭学表数据 调用worksDAO里的insertWorks配置
	public int insertWorks(Works works);

	// 更新勤工俭学表数据 调用worksDAO里的updateWorks配置
	public int updateWorks(Works works);

	// 按主键删除勤工俭学表数据 调用worksDAO里的deleteWorks配置
	public int deleteWorks(String worksid);

	// 批量删除勤工俭学表数据 调用mapper包works.xml里的deleteWorksByIds配置 返回值0(失败),大于0(成功)
	public int deleteWorksByIds(String[] ids);

	// 查询全部数据 调用worksDAO里的getAllWorks配置
	public List<Works> getAllWorks();

	// 按照Works类里面的字段名称精确查询 调用worksDAO里的getWorksByCond配置
	public List<Works> getWorksByCond(Works works);

	// 按照Works类里面的字段名称模糊查询 调用worksDAO里的getWorksByLike配置
	public List<Works> getWorksByLike(Works works);

	// 按主键查询表返回单一的Works实例 调用worksDAO里的getWorksById配置
	public Works getWorksById(String worksid);

}

