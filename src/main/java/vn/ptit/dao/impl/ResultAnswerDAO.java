package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IResultAnswerDAO;
import vn.ptit.mapper.MapperResultAnswer;
import vn.ptit.model.ResultAnswer;

@Repository
public class ResultAnswerDAO extends AbstractDAO<ResultAnswer> implements IResultAnswerDAO{

	@Override
	public int insert(ResultAnswer answer) {
		String sql = "INSERT INTO `exam`.`result_ans` (`questionid`, `resultid`, `userChoice`) VALUES (?, ?, ?)";
		
		int answerId = executeInsert(sql, answer.getQuestionId(), answer.getResultId(), answer.getUserChoice());
		
		return answerId;
	}

	@Override
	public int update(ResultAnswer answer) {
//		String sql = "UPDATE `exam`.`result_ans` SET `questionid` = ?, `resultid` = ?, `userChoice` = ? WHERE (`id` = ?)";
//		
//		return executeUpdate(sql, answer.getQuestionId(), answer.getResultId(), answer.getUserChoice());
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultAnswer getById(int id) {
		String sql = "SELECT * FROM `exam`.`result_ans` WHERE(`id` = ?)";
		
		List<ResultAnswer> listAnswers = executeQuery(sql, new MapperResultAnswer(), id);
		return listAnswers.isEmpty() ? null : listAnswers.get(0);
	}

	@Override
	public List<ResultAnswer> getAnswersByResultId(int resultId) {
		String sql = "SELECT * FROM `exam`.`result_ans` WHERE(`resultid` = ?)";
		
		List<ResultAnswer> listAnswers = executeQuery(sql, new MapperResultAnswer(), resultId);
		return listAnswers;
	}
}
