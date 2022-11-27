package com.dao;

import com.model.Question;

public interface QuestionDAO {
	public int addQuestion(Question ques);
	public Question getQuestion(String id);
	public int updateQuestion(Question id);
	public int deleteQuestion(Question ques);
}
