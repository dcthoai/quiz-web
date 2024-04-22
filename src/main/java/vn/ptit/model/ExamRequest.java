package vn.ptit.model;

import java.sql.Timestamp;
import java.util.List;

public class ExamRequest {
	private String examName, description, status;
	private Timestamp deadline, timeStart;
	private int id, time;
	private List<Question> listQuestions;

	public ExamRequest(String examName, String description, String status, Timestamp deadline, int time, Timestamp timeStart,
			List<Question> listQuestions, int id) {
		super();
		this.examName = examName;
		this.description = description;
		this.status = status;
		this.deadline = deadline;
		this.timeStart = timeStart;
		this.time = time;
		this.listQuestions = listQuestions;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public Timestamp getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Timestamp timeStart) {
		this.timeStart = timeStart;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public List<Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}
}
