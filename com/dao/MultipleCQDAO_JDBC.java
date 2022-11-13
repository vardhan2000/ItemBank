package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.MultipleCQ;

public class MultipleCQDAO_JDBC implements MultipleCQDAO {

	Connection dbConnection;

	public MultipleCQDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public int addMCQ(MultipleCQ mcq)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "insert into mcq(qid,qversion,mcq_choice,mcq_option,is_correct,question_text) values (?,?,?,?,?,?)";

		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, mcq.getId());
			preparedStatement.setString(2, mcq.getVersion());
			preparedStatement.setString(3, mcq.getChoice());
			preparedStatement.setString(4, mcq.getOption());
			preparedStatement.setString(5, mcq.getIs_correct());
			preparedStatement.setString(6, mcq.getQuestion_text());

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
	public MultipleCQ getMCQ(String id, String version, String choice)
	{
		MultipleCQ MCQAnswer = new MultipleCQ();
		try
		{
			String sql = "select * from mcq where qid = " + id + " and qversion = " + version + " and mcq_choice = " + choice;
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				MCQAnswer.setId(rs.getString("qid"));
				MCQAnswer.setVersion(rs.getString("qversion"));
				MCQAnswer.setChoice(rs.getString("mcq_choice"));
				MCQAnswer.setOption(rs.getString("mcq_option"));
				MCQAnswer.setIs_correct(rs.getString("is_correct"));
				MCQAnswer.setQuestion_text(rs.getString("question_text"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return MCQAnswer;
	}

	@Override
	public ArrayList<MultipleCQ> getMCQ(String id, String version)
	{
		ArrayList<MultipleCQ> mcqList = new ArrayList<MultipleCQ>();
		int c = 1;
		while(true)
		{
			MultipleCQ m = getMCQ(id, version, c+"");
			c++;
			if(m.getId() != null)
				mcqList.add(m);
			else
				break;
		}

		return mcqList;
	}

	@Override
	public ArrayList<ArrayList<MultipleCQ>> getMCQ(String id)
	{
		ArrayList<ArrayList<MultipleCQ>> mcqList = new ArrayList<ArrayList<MultipleCQ>>();
		int v = 1;
		while(true)
		{
			ArrayList<MultipleCQ> m = getMCQ(id, v+"");
			v++;
			if(m.size() > 0)
				mcqList.add(m);
			else
				break;
		}

		return mcqList;
	}


}
