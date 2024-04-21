package vn.ptit.dao;

import java.util.List;

import vn.ptit.model.Exam;

public interface IExamDAO extends IGenericDAO<Exam>{
	public List<Exam> getExamEnabled();
	public List<Exam> getExamFree();
	public List<Exam> getExamByName(String name);
	public List<Exam> getAllExams();
}
