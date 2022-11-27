package com.model;

public class Question {
	String id;
	String authorId;
	String domain;
	String status;

	public Question() {}

	public Question(String id, String domain, String authorId, String status)
	{
		this.id = id;
		this.domain = domain;
		this.authorId = authorId;
		this.status = status;
	}

    public String getId() { return this.id; }
    public String getDomain() { return this.domain; }
    public String getAuthorId() { return this.authorId; }
    public String getStatus() { return this.status; }

    public void setId(String id) { this.id = id; }
    public void setDomain(String domain) { this.domain = domain; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }
    public void setStatus(String status) { this.status = status; }

	@Override
	public String toString() {
		return id + " " + domain + " " + authorId + " " + status;
	}
}
