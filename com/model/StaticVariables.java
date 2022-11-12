package com.model;

public class StaticVariables {
	String varClass;
	String varVar;
	String varValue;

	public StaticVariables() {}

	public StaticVariables(String varClass, String varVar, String varValue)
	{
		this.varClass = varClass;
		this.varVar = varVar;
		this.varValue = varValue;
	}

	@Override
	public String toString() {
		return varClass + " " + varVar + " " + varValue;
	}

	public String getVarClass() {
		return varClass;
	}

	public void setVarClass(String varClass) {
		this.varClass = varClass;
	}

	public String getVarVar() {
		return varVar;
	}

	public void setVarVar(String varVar) {
		this.varVar = varVar;
	}

	public String getVarValue() {
		return varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}
}
