package com.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBConnection {
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/onlinemealorderingdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Wxn717919";
	public static Connection dbConnection;
	

//	private static final String DB_CONNECTION = "jdbc:mysql://i943okdfa47xqzpy.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/q0nev6urz5r9xg7b";
//	private static final String DB_USER = "o6d9o8gu1ec6rvp0";
//	private static final String DB_PASSWORD = "muj2z3h0q3i713hq";
//	public static Connection dbConnection;
	
	public static PreparedStatement prepare(String stm) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection dbConnection = getDBConnection();
		preparedStatement = dbConnection.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
		return preparedStatement;
	}
	
	public static Connection getDBConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		return dbConnection;
		
	}
}

