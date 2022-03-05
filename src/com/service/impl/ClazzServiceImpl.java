package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ClazzDAO;
import com.entity.Clazz;
import com.service.ClazzService;

@Service("clazzService") //
public class ClazzServiceImpl implements ClazzService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ClazzDAO clazzDAO;
	@Override // 继承接口的新增班级表数据 返回值0(失败),1(成功)
	public int insertClazz(Clazz clazz) {
		return this.clazzDAO.insertClazz(clazz);
	}

	@Override // 继承接口的更新班级表数据 返回值0(失败),1(成功)
	public int updateClazz(Clazz clazz) {
		return this.clazzDAO.updateClazz(clazz);
	}

	@Override // 继承接口的按主键删除班级表数据 返回值0(失败),1(成功)
	public int deleteClazz(String clazzid) {
		return this.clazzDAO.deleteClazz(clazzid);
	}

	@Override // 继承接口的批量删除班级表数据 返回值0(失败),大于0(成功)
	public int deleteClazzByIds(String[] ids) {
		return this.clazzDAO.deleteClazzByIds(ids);
	}

	@Override // 继承接口的查询班级表全部数据
	public List<Clazz> getAllClazz() {
		return this.clazzDAO.getAllClazz();
	}

	@Override // 继承接口的按条件精确查询班级表数据
	public List<Clazz> getClazzByCond(Clazz clazz) {
		return this.clazzDAO.getClazzByCond(clazz);
	}

	@Override // 继承接口的按条件模糊查询班级表数据
	public List<Clazz> getClazzByLike(Clazz clazz) {
		return this.clazzDAO.getClazzByLike(clazz);
	}

	@Override // 继承接口的按主键查询班级表数据 返回Entity实例
	public Clazz getClazzById(String clazzid) {
		return this.clazzDAO.getClazzById(clazzid);
	}

}

