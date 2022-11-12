package com.model;

public class SubjectiveQ {
	String id;
	String version;
	String subjective_answer_points;
	String question_text;

	public SubjectiveQ() {}

	public SubjectiveQ(String id, String version, String subjective_answer_points, String question_text)
	{
		this.id = id;
		this.version = version;
		this.subjective_answer_points = subjective_answer_points;
		this.question_text = question_text;
	}

	@Override
	public String toString() {
		return id + " " + version+ " " + subjective_answer_points+ " " +question_text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSubjective_answer_points() {
		return subjective_answer_points;
	}

	public void setSubjective_answer_points(String subjective_answer_points) {
		this.subjective_answer_points = subjective_answer_points;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
}
