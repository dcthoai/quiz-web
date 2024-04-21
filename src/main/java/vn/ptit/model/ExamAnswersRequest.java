package vn.ptit.model;

import java.util.ArrayList;
import java.util.List;

public class ExamAnswersRequest {
	private int examId;
	private List<ResultAnswer> listAnswers = new ArrayList<ResultAnswer>();

	public ExamAnswersRequest(int examId, List<ResultAnswer> listAnswers) {
		super();
		this.examId = examId;
		this.listAnswers = listAnswers;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public List<ResultAnswer> getListAnswers() {
		return listAnswers;
	}

	public void setListAnswers(List<ResultAnswer> listAnswers) {
		this.listAnswers = listAnswers;
	}
}
