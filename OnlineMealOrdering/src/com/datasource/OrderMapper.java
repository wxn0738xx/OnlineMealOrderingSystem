package com.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.domain.Dish;
import com.domain.Order;
public class OrderMapper implements IDataMapper {

	@Override
	public void create(Object obj) {
		Order order = (Order) obj;
		String sql = "INSERT INTO meal_order(create_time, address, total_price, customer_id, menu_id) "
				+ "VALUES (?,?,?,?,?)";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setDate(1, order.getCreateTime());
			sqlPrepared.setString(2, order.getAddress());
			sqlPrepared.setFloat(3, order.getTotalPrice());
			sqlPrepared.setInt(4, Integer.valueOf(order.getCustomerId()));
			sqlPrepared.setInt(5, Integer.valueOf(order.getMenuId()));
			sqlPrepared.executeUpdate();
			//after inserting into DB, get the primary key of inserted order
			int orderid = 0;
			ResultSet rs = sqlPrepared.getGeneratedKeys();
			if(rs.next()) {
				orderid = rs.getInt(1);
			}
			String order_dishSql= "INSERT INTO order_dish(order_id, dish_id, qty) VALUES(?,?,?)";
			sqlPrepared = DBConnection.prepare(order_dishSql);
			HashMap<String, Integer> dish_Qty = order.getDishQty();
			//insert each dish and its quantity into DB
			for (Map.Entry<String, Integer> e : dish_Qty.entrySet()) {
				sqlPrepared.setInt(1, orderid);
				sqlPrepared.setInt(2, Integer.valueOf(e.getKey()));
				sqlPrepared.setInt(3, e.getValue());
				sqlPrepared.executeUpdate();
			}
			
			DBConnection.dbConnection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		Order order = (Order) obj;
		//delete from order_dish table at first, then delete from order table
		String sql1 = "DELETE FROM order_dish where order_id=?";
		String sql2 = "DELETE FROM meal_order where order_id=?";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql1);
			sqlPrepared.setInt(1, Integer.parseInt(order.getId()));
			sqlPrepared.executeUpdate();
			
			sqlPrepared = DBConnection.prepare(sql2);
			sqlPrepared.setInt(1, Integer.parseInt(order.getId()));
			sqlPrepared.executeUpdate();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Object find(Object obj) {
		Order o = (Order) obj;
		String oid = o.getId();
		String sql = "SELECT create_time, address, total_price, menu_id FROM meal_order" + " WHERE order_id = ?";
		ResultSet rs;
		PreparedStatement sqlPrepared;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setString(1, oid);
			rs = sqlPrepared.executeQuery();
			while (rs.next()) {
				o.setCreateTime(rs.getDate(1));
				o.setAddress(rs.getString(2));
				o.setTotalPrice(rs.getFloat(3));
				o.setMenuId(String.valueOf(rs.getInt(4)));
			}
			sqlPrepared.close();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}
	
	public ArrayList<Order> findOrderByCusomterId(String cid){
		ArrayList<Order> result = new ArrayList<Order>();
		String sql = "SELECT create_time, address, total_price, menu_id, order_id FROM meal_order" + " WHERE customer_id =?";
		ResultSet rs;
		PreparedStatement sqlPrepared;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(cid));
			rs = sqlPrepared.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setCreateTime(rs.getDate(1));
				o.setAddress(rs.getString(2));
				o.setTotalPrice(rs.getFloat(3));
				o.setMenuId(String.valueOf(rs.getInt(4)));
				o.setOrderId(String.valueOf(rs.getInt(5)));
				result.add(o);
			}
			sqlPrepared.close();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public ArrayList<Order> findOrderByMenuId(String mid){
		ArrayList<Order> result = new ArrayList<Order>();
		String sql = "SELECT create_time, address, total_price, customer_id, order_id FROM meal_order" + " WHERE  menu_id =?";
		ResultSet rs;
		PreparedStatement sqlPrepared;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(mid));
			rs = sqlPrepared.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.setCreateTime(rs.getDate(1));
				o.setAddress(rs.getString(2));
				o.setTotalPrice(rs.getFloat(3));
				o.setCustomerId(String.valueOf(rs.getInt(4)));
				o.setOrderId(String.valueOf(rs.getInt(5)));
				result.add(o);
			}
			sqlPrepared.close();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	

}
