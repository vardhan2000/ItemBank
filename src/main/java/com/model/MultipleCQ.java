package com.model;

public class MultipleCQ {
	String id;
	String version;
	String choice;
	String option;
	String is_correct;
	String question_text;

	public MultipleCQ() {}

	public MultipleCQ(String id, String version, String choice, String option, String is_correct, String question_text)
	{
		this.id = id;
		this.version = version;
		this.choice = choice;
		this.option = option;
		this.is_correct = is_correct;
		this.question_text = question_text;
	}

	@Override
	public String toString() {
		return id + " " + version+ " " + choice+ " " + option+ " " + is_correct + " " + question_text;
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

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getIs_correct() {
		return is_correct;
	}

	public void setIs_correct(String is_correct) {
		this.is_correct = is_correct;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
}
