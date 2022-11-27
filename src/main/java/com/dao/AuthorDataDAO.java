package com.dao;

import com.model.AuthorData;

public interface AuthorDataDAO {
	public int addAuthor(AuthorData author);
	public AuthorData getAuthor(String id);
	public int updateAuthor(AuthorData author);
	public AuthorData getAuthorById(String id);
}
