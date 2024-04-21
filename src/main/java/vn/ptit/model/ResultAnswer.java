package vn.ptit.model;

public class ResultAnswer {
	private int id, questionid, resultid;
	private String userChoice;

	public ResultAnswer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public int getResultid() {
		return resultid;
	}

	public void setResultid(int resultid) {
		this.resultid = resultid;
	}

	public String getUserChoice() {
		return userChoice;
	}

	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}
}
