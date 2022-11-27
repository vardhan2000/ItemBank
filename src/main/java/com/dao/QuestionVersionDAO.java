package com.dao;

import java.util.ArrayList;

import com.model.QuestionVersion;

public interface QuestionVersionDAO {
	public int addQuestionVersion(QuestionVersion quesVersion);
	public QuestionVersion getQuestionVersion(String id, String version);
	public ArrayList<QuestionVersion> getQuestionVersion(String id);
}
