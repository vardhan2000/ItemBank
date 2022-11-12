package com.dao;

public interface StaticVariablesDAO {
	public int getStaticVariable(String varClass, String varVar);
	public int setStaticVariable(String varClass, String varVar, int value);
}
