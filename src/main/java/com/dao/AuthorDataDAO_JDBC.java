package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.AuthorData;

public class AuthorDataDAO_JDBC implements AuthorDataDAO {

	Connection dbConnection;

	public AuthorDataDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	// TODO Update static variables
	@Override
	public int addAuthor(AuthorData author)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into authors(aid,ausername,apassword,aemail) values (?,?,?,?)";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, author.getId());
			preparedStatement.setString(2, author.getUsername());
			preparedStatement.setString(3, author.getPassword());
			preparedStatement.setString(4, author.getEmail());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Student: Roll No " + student.getRollno()
			// 	+ ", added to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
			status = 1;
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			status = 1;
 			System.out.println(e.getMessage());
 		}
		return status;
	}

	@Override
	public AuthorData getAuthor(String uname) {
		AuthorData authorData = new AuthorData();
		try
		{
			String sql = "select * from authors where ausername = '" + uname + "'";
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				authorData.setId(rs.getString("aid"));
				authorData.setUsername(rs.getString("ausername"));
				authorData.setPassword(rs.getString("apassword"));
				authorData.setEmail(rs.getString("aemail"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return authorData;
	}
	
	@Override
	public AuthorData getAuthorById(String id) {
		AuthorData authorData = new AuthorData();
		try
		{
			String sql = "select * from authors where aid = '" + id + "'";
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				authorData.setId(rs.getString("aid"));
				authorData.setUsername(rs.getString("ausername"));
				authorData.setPassword(rs.getString("apassword"));
				authorData.setEmail(rs.getString("aemail"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return authorData;
	}

	@Override
	public int updateAuthor(AuthorData authorData) {
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "update authors set ausername = ?,apassword = ?, aemail = ? where aid = ?";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, authorData.getUsername());
			preparedStatement.setString(2, authorData.getPassword());
			preparedStatement.setString(3, authorData.getEmail());
			preparedStatement.setString(4, authorData.getId());
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
 
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
			status = 1;
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			status = 1;
 			System.out.println(e.getMessage());
 		}
		return status;
	}

}
