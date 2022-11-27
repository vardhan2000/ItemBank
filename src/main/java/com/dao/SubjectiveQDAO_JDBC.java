package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.SubjectiveQ;

public class SubjectiveQDAO_JDBC implements SubjectiveQDAO {

	Connection dbConnection;

	public SubjectiveQDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public int addSubjective(SubjectiveQ s)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into subjective(qid,qversion,subjective_answer_points,question_text) values (?,?,?,?)";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, s.getId());
			preparedStatement.setString(2, s.getVersion());
			preparedStatement.setString(3, s.getSubjective_answer_points());
			preparedStatement.setString(4, s.getQuestion_text());
			
			System.out.println(preparedStatement.toString());
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			// System.out.println("Student: Roll No " + student.getRollno()
			// 	+ ", added to the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 			
 			System.out.println("Hola");
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
	public SubjectiveQ getSubjective(String id, String version)
	{
		SubjectiveQ s = new SubjectiveQ();
		try
		{
			String sql = "select * from subjective where qid = " + id + " and qversion = " + version;
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				s.setId(rs.getString("qid"));
				s.setVersion(rs.getString("qversion"));
				s.setSubjective_answer_points(rs.getString("subjective_answer_points"));
				s.setQuestion_text(rs.getString("question_text"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return s;
	}

	@Override
	public ArrayList<SubjectiveQ> getSubjective(String id)
	{
		ArrayList<SubjectiveQ> sList = new ArrayList<SubjectiveQ>();
		int v = 1;
		while(true)
		{
			SubjectiveQ s = getSubjective(id, v+"");
			v++;
			if(s.getId() != null)
				sList.add(s);
			else
				break;
		}

		return sList;
	}

}
