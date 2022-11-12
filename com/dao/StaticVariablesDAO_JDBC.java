package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaticVariablesDAO_JDBC implements StaticVariablesDAO {

	Connection dbConnection;

	public StaticVariablesDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	@Override
	public int getStaticVariable(String varClass, String varVar)
	{
		try
		{
			String sql = "select * from staticvariables where var_class = '" + varClass + "' and var_var = '" + varVar + "'";
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
				return Integer.parseInt(rs.getString("var_value"));
			}

		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		return -1;
	}

	@Override
	public int setStaticVariable(String varClass, String varVar, int value)
	{
		int status = 0;
		PreparedStatement preparedStatement = null;
		String sql = "update staticvariables set var_value = " + value + " where var_class = '" + varClass + "' and var_var = '" + varVar + "'";
		System.out.println(sql);
		try
		{
			preparedStatement = dbConnection.prepareStatement(sql);
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
