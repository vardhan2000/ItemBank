package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.model.Question;

public class QuestionDAO_JDBC implements QuestionDAO {

	Connection dbConnection;

	public QuestionDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	// TODO Update static variables
	@Override
	public int addQuestion(Question ques)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into questionbank(qid,qdomain,aid,qstatus) values (?,?,?,?)";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, ques.getId());
			preparedStatement.setString(2, ques.getDomain());
			preparedStatement.setString(3, ques.getAuthorId());
			preparedStatement.setString(4, ques.getStatus());
 
			// execute insert SQL statement
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
	public Question getQuestion(String id) {
		Question ques = new Question();

		try
		{
			String sql = "select * from questionbank where qid = " + id;
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
            if(rs.next())
            {
				ques.setId(rs.getString("qid"));
				ques.setDomain(rs.getString("qdomain"));
				ques.setAuthorId(rs.getString("aid"));
				ques.setStatus(rs.getString("qstatus"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return ques;
	}

	@Override
	public int updateQuestion(Question ques) {
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "update questionbank set qdomain =?, qstatus = ? where qid = ?";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, ques.getDomain());
			preparedStatement.setString(2, ques.getStatus());
			preparedStatement.setString(3, ques.getId());
 
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
	public int deleteQuestion(Question ques) {
		ques.setStatus("DELETED");
		int status = updateQuestion(ques);
		return status;
	}
}

// @Override
// public Question getQuestionByKey(String id, String version) {
// 	Question it = new Question();
// 	String sql = "select item_domain, author_id, item_status, item_question from item where item_id = " + id + "item_version" + version;
// 	Statement stmt = null;
	
// 	try{
// 		stmt = dbConnection.createStatement();
// 		ResultSet rs = stmt.executeQuery(sql);

// 		it.setId(id);
// 		it.setDomain(rs.getString("item_domain"));
// 		it.setAuthorId(rs.getString("author_id"));
// 		it.setDomain(rs.getString("item_status"));
// 	}
// 	catch (SQLException ex) {
// 		// handle any errors
// 		System.out.println("SQLException: " + ex.getMessage());
// 		System.out.println("SQLState: " + ex.getSQLState());
// 		System.out.println("VendorError: " + ex.getErrorCode());
// 	}
// 	// Add exception handling when there is no matching record
// 	return it;
// }

// @Override
// public ArrayList<Question> getQuestionsById(String id) {
// 	ArrayList<Question> items = new ArrayList<> ();
// 	String sql = "select version, item_domain, author_id, item_status, item_question from item where item_id = " + id;
// 	Statement stmt = null;

// 	try {
// 		stmt = dbConnection.createStatement();
// 		ResultSet rs = stmt.executeQuery(sql);

// 		while (rs.next()) {
// 			Question it = new Question();
// 			it.setId(id);
// 			it.setDomain(rs.getString("item_domain"));
// 			it.setAuthorId(rs.getString("author_id"));
// 			it.setDomain(rs.getString("item_status"));

// 			items.add(it);
// 		}
// 	} 
// 	catch (SQLException ex) {
// 		// handle any errors
// 		System.out.println("SQLException: " + ex.getMessage());
// 		System.out.println("SQLState: " + ex.getSQLState());
// 		System.out.println("VendorError: " + ex.getErrorCode());
// 	}
// 	return items;

//}
