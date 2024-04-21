package vn.ptit.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.ptit.dao.IExamDAO;
import vn.ptit.mapper.MapperExam;
import vn.ptit.model.Exam;

@Repository
public class ExamDAO extends AbstractDAO<Exam> implements IExamDAO{

	@Override
	public int insert(Exam exam) {
		String sql = "INSERT INTO `exam`.`exam` (`examName`, `desciption`, `status`, `deadline`, `time`, `timeStart`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		int examId = executeInsert(sql, exam.getExamName(),
										exam.getDescription(),
										exam.getStatus(),
										exam.getDeadline(),
										exam.getTime(),
										exam.getTimeStart());
		
		return examId;
	}

	@Override
	public int update(Exam exam) {
		String sql = "UPDATE `exam`.`exam` SET `examName` = ?, "
											+ "`desciption` = ?, "
											+ "`status` = ?, "
											+ "`deadline` = ?, "
											+ "`time` = ?, "
											+ "`timeStart` = ? WHERE (`id` = ?)";

		int affectedRows = executeUpdate(sql, exam.getExamName(),
										exam.getDescription(),
										exam.getStatus(),
										exam.getDeadline(),
										exam.getTime(),
										exam.getTimeStart());

		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE `exam`.`exam` WHERE(`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		return affectedRows;
	}

	@Override
	public Exam getById(int id) {
		String sql = "SELECT * FROM `exam`.`exam` WHERE(`id` = ?)";
		
		List<Exam> listExams = executeQuery(sql, new MapperExam(), id);
		return listExams.isEmpty() ? null : listExams.get(0);
	}

	@Override
	public List<Exam> getExamEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExamFree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExamByName(String name) {
		String sql = "SELECT * FROM `exam`.`exam` WHERE(`examName` LIKE ?)";
		
		List<Exam> listExams = executeQuery(sql, new MapperExam(), name);
		return listExams;
	}
}
