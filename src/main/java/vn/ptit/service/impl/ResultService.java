package vn.ptit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ptit.dao.impl.QuestionDAO;
import vn.ptit.dao.impl.ResultAnswerDAO;
import vn.ptit.dao.impl.ResultDAO;
import vn.ptit.model.Question;
import vn.ptit.model.Result;
import vn.ptit.model.ResultAnswer;
import vn.ptit.service.IResultService;

@Service
public class ResultService implements IResultService{
	
	@Autowired
	private ResultDAO resultDAO;
	
	@Autowired
	private ResultAnswerDAO answerDAO;
	
	@Autowired
	private QuestionDAO questionDAO;

	@Override
	public int insertResult(Result result) {
		return resultDAO.insert(result);
	}

	@Override
	public boolean updateResult(Result result) {
		return resultDAO.update(result) > 0;
	}

	@Override
	public boolean deleteResult(int id) {
		return resultDAO.delete(id) > 0;
	}

	@Override
	public int insertResultAnswer(ResultAnswer resultAnswer) {
		return answerDAO.insert(resultAnswer);
	}

	@Override
	public boolean updateResultAnswer(ResultAnswer resultAnswer) {
		return answerDAO.update(resultAnswer) > 0;
	}

	@Override
	public boolean deleteResultAnswer(int id) {
		return answerDAO.delete(id) > 0;
	}

	@Override
	public Result markExam(int exameId, List<ResultAnswer> resultAnswers) {
		Result result = new Result();
		List<Question> listQuestions = questionDAO.getQuestionsByExamId(exameId);
		result.setExamId(exameId);
		
		int correctAnswers = 0;
		int totalQuestions = listQuestions.size();
		
		for(int i = 0; i < totalQuestions; ++i) {
			ResultAnswer answer = resultAnswers.get(i);
			Question question = listQuestions.get(i);
			
			if (answer != null && question != null) {
				if (checkCorrectAnswer(question, answer))
					correctAnswers += 1;
			}
		}
		
		result.setTotalCorrectAns(correctAnswers);
		result.setScore((float) correctAnswers / (float) totalQuestions);
		
		return result;
	}

	@Override
	public boolean checkCorrectAnswer(Question question, ResultAnswer answer) {
		if (question.getId() == answer.getQuestionId())
			return question.getCorrectAns().equals(answer.getUserChoice());
		
		return false;
	}

	@Override
	public Result getResultById(int id) {
		return resultDAO.getById(id);
	}

	@Override
	public List<Result> getResultsByUserId(int userId) {
		return resultDAO.getResultsByUserId(userId);
	}

	@Override
	public List<Result> getResultsByExamId(int examId) {
		return resultDAO.getResultsByExamId(examId);
	}

	@Override
	public List<ResultAnswer> getResultAnswers(int examId) {
		return answerDAO.getAnswersByResultId(examId);
	}
}
