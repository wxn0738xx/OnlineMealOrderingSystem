package com.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.domain.Manager;


public class ManagerMapper{
	public static Manager findWithusernamePwd(String username, String pwd) throws SQLException {
		String sql = "SELECT manager_id, username, name FROM manager" + " WHERE username = ? AND password=?";
		ResultSet rs;
		int id = -1;
		String name = "";

		PreparedStatement sqlPrepared = DBConnection.prepare(sql);
		sqlPrepared.setString(1, username);
		sqlPrepared.setString(2, pwd);
		rs = sqlPrepared.executeQuery();
		while(rs.next()) {
			id = rs.getInt(1);
			name = rs.getString(2);
		}
		System.out.println("id"+id);
		sqlPrepared.close();
		DBConnection.dbConnection.close();
		return new Manager(String.valueOf(id), name);

	}


}
