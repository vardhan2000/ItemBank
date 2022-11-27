package com.model;

public class QuestionVersion {
	String id;
	String version;
	int type;

	public QuestionVersion() {}

	public QuestionVersion(String id, String version, int type)
	{
		this.id = id;
		this.version = version;
		this.type = type;
	}

    public String getId() { return this.id; }
    public String getVersion() { return version; }
	public int getType() { return type; }


	public void setId(String id) { this.id = id; }
	public void setVersion(String version) { this.version = version; }
	public void setType(int type) { this.type = type; }

	@Override
	public String toString() {
		return id + " " + version + " " + type;
	}
}
