package com.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domain.Dish;
import com.domain.Menu;

public class MenuMapper implements IDataMapper{
	public MenuMapper() {};
	
	public static Menu findMenuByManagerId(String managerId) throws SQLException {
		String sql = "SELECT menu_id, resturant_name, manager_id FROM menu" + " WHERE manager_id = ?";
		ResultSet rs;
		int menuId = -1;
		int ownerId = -1;
		String resturantName = "";
		PreparedStatement sqlPrepared = DBConnection.prepare(sql);
		sqlPrepared.setString(1, managerId);
		rs = sqlPrepared.executeQuery();
		while(rs.next()) {
			menuId = rs.getInt(1);
			resturantName = rs.getString(2);
			ownerId = rs.getInt(3);
		}
		DBConnection.dbConnection.close();
		return new Menu(String.valueOf(menuId),resturantName,String.valueOf(ownerId));
		
	}

	@Override
	public void create(Object obj) {
		Menu menu = (Menu) obj;
		String sql = "INSERT INTO menu (resturant_name, manager_id) VALUES (?,?)";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setString(1, menu.getResturantName());
			sqlPrepared.setString(2, menu.getManagerId());
			sqlPrepared.executeUpdate();
			DBConnection.dbConnection.close();
		} catch(SQLException e) {
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
		Menu menu = (Menu) obj;
		List<Dish> dishes = new ArrayList<Dish>();
		DishMapper dishMapper = new DishMapper();
		//delete all the dishes at first
		try {
			dishes= DishMapper.getDishesByMenuId(menu.getMenuId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Dish dish : dishes) {
			dishMapper.delete(dish);
		} 
		String sql = "DELETE FROM menu where menu_id=?";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(menu.getMenuId()));
			sqlPrepared.executeUpdate();
			sqlPrepared.close();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Object find(Object obj) {
		return null;
	}
	
	/**
	 * @return All existing restaurant with menuid  
	 * @throws SQLException
	 */
	public static HashMap<Integer, String> getAllRestaurant() throws SQLException{
		HashMap<Integer,String> result = new HashMap<Integer,String>();
		String sql = "SELECT menu_id, resturant_name FROM menu";
		ResultSet rs;
		int menuId = -1;
		String resturantName = "";
		PreparedStatement sqlPrepared = DBConnection.prepare(sql);
		rs = sqlPrepared.executeQuery();
		while(rs.next()) {
			menuId = rs.getInt(1);
			resturantName = rs.getString(2);
			result.put(menuId, resturantName);
		}
		DBConnection.dbConnection.close();
		return result;
	}
	
	

}
