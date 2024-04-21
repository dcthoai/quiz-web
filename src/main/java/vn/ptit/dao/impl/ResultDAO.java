package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IResultDAO;
import vn.ptit.mapper.MapperResult;
import vn.ptit.model.Result;

@Repository
public class ResultDAO extends AbstractDAO<Result> implements IResultDAO{

	@Override
	public int insert(Result result) {
		String sql = "INSERT INTO `exam`.`result` (`exam_id`, `user_id`, `totalCorrectAns`, `score`) "
				+ "VALUES (?, ?, ?, ?)";
		
		int resultId = executeInsert(sql, result.getExamId(),
											result.getUserId(),
											result.getTotalCorrectAns(),
											result.getScore());
		
		return resultId;
	}

	@Override
	public int update(Result object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE `exam`.`result` WHERE(`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		return affectedRows;
	}

	@Override
	public Result getById(int id) {
		String sql = "SELECT * FROM `exam`.`result` WHERE(`id` = ?)";
		
		List<Result> listResults = executeQuery(sql, new MapperResult(), id);
		
		return listResults.isEmpty() ? null : listResults.get(0);
	}

	@Override
	public List<Result> getResultsByUserId(int userId) {
		String sql = "SELECT * FROM `exam`.`result` WHERE(`user_id` = ?)";
		
		List<Result> listResults = executeQuery(sql, new MapperResult(), userId);
		
		return listResults;
	}

	@Override
	public List<Result> getResultsByExamId(int examId) {
		String sql = "SELECT * FROM `exam`.`result` WHERE(`exam_id` = ?)";
		
		List<Result> listResults = executeQuery(sql, new MapperResult(), examId);
		
		return listResults;
	}
}
