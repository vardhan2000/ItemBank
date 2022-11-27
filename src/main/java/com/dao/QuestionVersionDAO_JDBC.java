package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.QuestionVersion;

public class QuestionVersionDAO_JDBC implements QuestionVersionDAO {

	Connection dbConnection;

	public QuestionVersionDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	// TODO Update static variables
	@Override
	public int addQuestionVersion(QuestionVersion quesVersion)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into qvtable(qid,qversion,qtype) values (?,?,?)";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, quesVersion.getId());
			preparedStatement.setString(2, quesVersion.getVersion());
			preparedStatement.setString(3, quesVersion.getType()+"");

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
	public QuestionVersion getQuestionVersion(String id,String version) {
		QuestionVersion quesVersion = new QuestionVersion();
		try
		{
			String sql = "select * from qvtable where qid = '" + id + "' and qversion = '"  + version +"'";
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				quesVersion.setId(rs.getString("qid"));
				quesVersion.setVersion(rs.getString("qversion"));
				quesVersion.setType(Integer.parseInt(rs.getString("qtype")));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return quesVersion;
	}

	@Override
	public ArrayList<QuestionVersion> getQuestionVersion(String id) {
		ArrayList<QuestionVersion> quesVersion = new ArrayList<QuestionVersion>();

		int v = 1;
		while(true)
		{
			QuestionVersion qv = getQuestionVersion(id, v+"");
			if(qv.getId() == null)
			{
				break;
			}
			if(v == 5)
			{
				System.out.println("Failed");
			}
			quesVersion.add(qv);
			v++;
		}
		return quesVersion;
	}

}
