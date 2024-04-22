package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IQuestionDAO;
import vn.ptit.mapper.MapperQuestion;
import vn.ptit.model.Question;
import vn.ptit.model.ResultAnswer;

@Repository
public class QuestionDAO extends AbstractDAO<Question> implements IQuestionDAO{

	@Override
	public int insert(Question question) {
		String sql = "INSERT INTO `exam`.`question` "
				+ "(`exam_id`, `questionName`, `answerA`, `answerB`, `answerC`, `answerD`, `correctAns`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		int questionId = executeInsert(sql, question.getExam_id(),
											question.getQuestionName(),
											question.getAnswerA(),
											question.getAnswerB(),
											question.getAnswerC(),
											question.getAnswerD(),
											question.getCorrectAns());
		
		return questionId;
	}

	@Override
	public int update(Question question) {
		String sql = "UPDATE `exam`.`question` SET "
				+ "`exam_id` = ?, `questionName` = ?, `answerA` = ?, `answerB` = ?, `answerC` = ?, `answerD` = ?, `correctAns` = ? "
				+ " WHERE `id` = ?";
		
		int affectedRows = executeUpdate(sql, question.getExam_id(),
											question.getQuestionName(),
											question.getAnswerA(),
											question.getAnswerB(),
											question.getAnswerC(),
											question.getAnswerD(),
											question.getCorrectAns(),
											question.getId());
		
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `exam`.`question` WHERE `id` = ?";
		
		int affectedRows = executeUpdate(sql, id);

		return affectedRows;
	}

	@Override
	public Question getById(int id) {
		String sql = "SELECT * FROM `exam`.`question` WHERE(`id` = ?)";
		
		List<Question> listQuestions = executeQuery(sql, new MapperQuestion(), id);
		return listQuestions.isEmpty() ? null : listQuestions.get(0);
	}

	@Override
	public List<Question> getQuestionsByExamId(int examId) {
		String sql = "SELECT * FROM `exam`.`question` WHERE(`exam_id` = ?)";
		
		List<Question> listQuestions = executeQuery(sql, new MapperQuestion(), examId);
		return listQuestions;
	}
}
