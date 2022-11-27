package com.dao;

import java.util.ArrayList;

import com.model.SubjectiveQ;

public interface SubjectiveQDAO {
	public int addSubjective(SubjectiveQ s);
	public SubjectiveQ getSubjective(String id, String version);
	public ArrayList<SubjectiveQ> getSubjective(String id);
}
