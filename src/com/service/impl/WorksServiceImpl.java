package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.WorksDAO;
import com.entity.Works;
import com.service.WorksService;

@Service("worksService") //
public class WorksServiceImpl implements WorksService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private WorksDAO worksDAO;
	@Override // 继承接口的新增勤工俭学表数据 返回值0(失败),1(成功)
	public int insertWorks(Works works) {
		return this.worksDAO.insertWorks(works);
	}

	@Override // 继承接口的更新勤工俭学表数据 返回值0(失败),1(成功)
	public int updateWorks(Works works) {
		return this.worksDAO.updateWorks(works);
	}

	@Override // 继承接口的按主键删除勤工俭学表数据 返回值0(失败),1(成功)
	public int deleteWorks(String worksid) {
		return this.worksDAO.deleteWorks(worksid);
	}

	@Override // 继承接口的批量删除勤工俭学表数据 返回值0(失败),大于0(成功)
	public int deleteWorksByIds(String[] ids) {
		return this.worksDAO.deleteWorksByIds(ids);
	}

	@Override // 继承接口的查询勤工俭学表全部数据
	public List<Works> getAllWorks() {
		return this.worksDAO.getAllWorks();
	}

	@Override // 继承接口的按条件精确查询勤工俭学表数据
	public List<Works> getWorksByCond(Works works) {
		return this.worksDAO.getWorksByCond(works);
	}

	@Override // 继承接口的按条件模糊查询勤工俭学表数据
	public List<Works> getWorksByLike(Works works) {
		return this.worksDAO.getWorksByLike(works);
	}

	@Override // 继承接口的按主键查询勤工俭学表数据 返回Entity实例
	public Works getWorksById(String worksid) {
		return this.worksDAO.getWorksById(worksid);
	}

}

