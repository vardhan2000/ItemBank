package com.model;

public class AuthorData {
	String id;
	String username;
	String password;
	String email;

	public AuthorData() {}

	public AuthorData(String id, String username, String password, String email)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

    public String getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getEmail() { return this.email; }

    public void setId(String id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }

	@Override
	public String toString() {
		return id + " " + username + " " + password + " " + email;
	}
}
