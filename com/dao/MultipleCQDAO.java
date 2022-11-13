package com.dao;

import java.util.ArrayList;

import com.model.MultipleCQ;

public interface MultipleCQDAO {
	public int addMCQ(MultipleCQ mcq);
	public MultipleCQ getMCQ(String id, String version, String choice);
	public ArrayList<MultipleCQ> getMCQ(String id, String verstion);
	public ArrayList<ArrayList<MultipleCQ>> getMCQ(String id);
}
