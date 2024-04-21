package vn.ptit.model;

public class Result {
	private int id, exam_id, user_id, score, totalCorrectAns;

	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTotalCorrectAns() {
		return totalCorrectAns;
	}

	public void setTotalCorrectAns(int totalCorrectAns) {
		this.totalCorrectAns = totalCorrectAns;
	}
}
