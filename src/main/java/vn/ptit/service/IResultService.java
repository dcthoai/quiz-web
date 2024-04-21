package vn.ptit.service;

import java.util.List;

import vn.ptit.model.Question;
import vn.ptit.model.Result;
import vn.ptit.model.ResultAnswer;

public interface IResultService {
	public int insertResult(Result result);
	public boolean updateResult(Result result);
	public boolean deleteResult(int id);
	public Result getResultById(int id);
	public List<Result> getResultsByUserId(int userId);
	public List<Result> getResultsByExamId(int examId);
	
	public int insertResultAnswer(ResultAnswer resultAnswer);
	public boolean updateResultAnswer(ResultAnswer resultAnswer);
	public boolean deleteResultAnswer(int id);
	
	public List<ResultAnswer> getResultAnswers(int examId);
	public boolean checkCorrectAnswer(Question question, ResultAnswer answer);
	public Result markExam(int exameId, List<ResultAnswer> resultAnswers);
}
