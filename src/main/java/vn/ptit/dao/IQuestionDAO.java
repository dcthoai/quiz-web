package vn.ptit.dao;

import java.util.List;

import vn.ptit.model.Question;
import vn.ptit.model.ResultAnswer;

public interface IQuestionDAO extends IGenericDAO<Question>{
	public List<Question> getQuestionsByExamId(int examId);
	public boolean checkCorrectAnswer(ResultAnswer answer);
}
