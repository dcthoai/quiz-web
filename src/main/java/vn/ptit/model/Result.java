package vn.ptit.model;

public class Result {
	private int id, examId, userId, totalCorrectAns;
	private float score;

	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTotalCorrectAns() {
		return totalCorrectAns;
	}

	public void setTotalCorrectAns(int totalCorrectAns) {
		this.totalCorrectAns = totalCorrectAns;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
