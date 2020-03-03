package com.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.domain.Customer;

public class CustomerMapper implements IDataMapper {

	public static Customer findWithusernamePwd(String username, String pwd) throws SQLException {
		String sql = "SELECT customer_id, username, name FROM customer" + " WHERE username = ? AND password=?";
		ResultSet rs;
		int id = -1;
		String name = "";

		PreparedStatement sqlPrepared = DBConnection.prepare(sql);
		sqlPrepared.setString(1, username);
		sqlPrepared.setString(2, pwd);
		rs = sqlPrepared.executeQuery();
		while (rs.next()) {
			id = rs.getInt(1);
			name = rs.getString(2);
		}
		sqlPrepared.close();
		DBConnection.dbConnection.close();
		return new Customer(String.valueOf(id), name);

	}

	@Override
	public void create(Object obj) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) obj;
		String sql = "INSERT INTO customer(name, username, password, phone_number) VALUES (?,?,?,?)";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setString(1, customer.getName());
			sqlPrepared.setString(2, customer.getUsername());
			sqlPrepared.setString(3, customer.getPassword());
			sqlPrepared.setString(4, customer.getPhoneNumber());
			sqlPrepared.executeUpdate();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object find(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
