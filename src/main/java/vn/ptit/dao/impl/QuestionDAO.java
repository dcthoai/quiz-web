package vn.ptit.dao.impl;

import java.util.List;

import vn.ptit.dao.IQuestionDAO;
import vn.ptit.model.Question;
import vn.ptit.model.ResultAnswer;

public class QuestionDAO extends AbstractDAO<Question> implements IQuestionDAO{

	@Override
	public int insert(Question object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Question object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Question getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionsByExamId(int examId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkCorrectAnswer(ResultAnswer answer) {
		// TODO Auto-generated method stub
		return false;
	}
}
