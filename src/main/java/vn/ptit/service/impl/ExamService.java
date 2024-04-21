package vn.ptit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ptit.dao.impl.ExamDAO;
import vn.ptit.dao.impl.QuestionDAO;
import vn.ptit.model.Exam;
import vn.ptit.model.Question;
import vn.ptit.service.IExamService;

@Service
public class ExamService implements IExamService{

	@Autowired
	private ExamDAO examDAO;
	
	@Autowired
	private QuestionDAO questionDAO;
	
	@Override
	public int insertExam(Exam exam) {
		return examDAO.insert(exam);
	}

	@Override
	public boolean updateExam(Exam exam) {
		return examDAO.update(exam) > 0;
	}

	@Override
	public boolean deleteExam(int id) {
		return examDAO.delete(id) > 0;
	}

	@Override
	public Exam getExamById(int id) {
		return examDAO.getById(id);
	}

	@Override
	public List<Exam> getExamEnabled() {
		return examDAO.getExamEnabled();
	}

	@Override
	public List<Exam> getExamFree() {
		return examDAO.getExamFree();
	}

	@Override
	public List<Exam> getExamByName(String name) {
		return examDAO.getExamByName(name);
	}

	@Override
	public List<Exam> getAllExams() {
		return examDAO.getAllExams();
	}

	@Override
	public List<Question> getQuestionsByExamId(int examId) {
		return questionDAO.getQuestionsByExamId(examId);
	}

	@Override
	public int insertQuestion(Question question) {
		return questionDAO.insert(question);
	}

	@Override
	public boolean updateQuestion(Question question) {
		return questionDAO.update(question) > 0;
	}

	@Override
	public boolean deleteQuestion(int id) {
		return questionDAO.delete(id) > 0;
	}

	@Override
	public Question getQuestionById(int id) {
		return questionDAO.getById(id);
	}
}
