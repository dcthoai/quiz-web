package vn.ptit.service;

import java.util.List;

import vn.ptit.model.Exam;
import vn.ptit.model.Question;
import vn.ptit.model.ResultAnswer;

public interface IExamService {
	public int insertExam(Exam exam);
	public boolean updateExam(Exam exam);
	public boolean deleteExam(int id);
	public Exam getExamById(int id);
	public List<Exam> getExamEnabled();
	public List<Exam> getExamFree();
	public List<Exam> getExamByName(String name);
	public List<Exam> getAllExams();
	
	public int insertQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean deleteQuestion(int id);
	public Question getQuestionById(int id);
	public List<Question> getQuestionsByExamId(int examId);
}
