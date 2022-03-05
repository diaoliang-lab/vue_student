package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ScoreDAO;
import com.entity.Score;
import com.service.ScoreService;

@Service("scoreService") //
public class ScoreServiceImpl implements ScoreService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private ScoreDAO scoreDAO;
	@Override // 继承接口的新增学生成绩表数据 返回值0(失败),1(成功)
	public int insertScore(Score score) {
		return this.scoreDAO.insertScore(score);
	}

	@Override // 继承接口的更新学生成绩表数据 返回值0(失败),1(成功)
	public int updateScore(Score score) {
		return this.scoreDAO.updateScore(score);
	}

	@Override // 继承接口的按主键删除学生成绩表数据 返回值0(失败),1(成功)
	public int deleteScore(String scoreid) {
		return this.scoreDAO.deleteScore(scoreid);
	}

	@Override // 继承接口的批量删除学生成绩表数据 返回值0(失败),大于0(成功)
	public int deleteScoreByIds(String[] ids) {
		return this.scoreDAO.deleteScoreByIds(ids);
	}

	@Override // 继承接口的查询学生成绩表全部数据
	public List<Score> getAllScore() {
		return this.scoreDAO.getAllScore();
	}

	@Override // 继承接口的按条件精确查询学生成绩表数据
	public List<Score> getScoreByCond(Score score) {
		return this.scoreDAO.getScoreByCond(score);
	}

	@Override // 继承接口的按条件模糊查询学生成绩表数据
	public List<Score> getScoreByLike(Score score) {
		return this.scoreDAO.getScoreByLike(score);
	}

	@Override // 继承接口的按主键查询学生成绩表数据 返回Entity实例
	public Score getScoreById(String scoreid) {
		return this.scoreDAO.getScoreById(scoreid);
	}

}

