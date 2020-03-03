package com.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.domain.Dish;

public class DishMapper implements IDataMapper{
	public static List<Dish> dishes = new ArrayList<Dish>();
	public DishMapper(){}
	//get all dish related to a given menu
	public static ArrayList<Dish> getDishesByMenuId(String mid) throws SQLException {
		ArrayList<Dish> result = new ArrayList<Dish>();
		String sql = "SELECT dish_id, name, price, picture, description FROM dish" + " WHERE menu_id = ?";
		ResultSet rs;
		int dishId = -1;
		String name = "";
		float price = 0f;
		String filePath = "";
		String description = "";

		PreparedStatement sqlPrepared = DBConnection.prepare(sql);
		sqlPrepared.setString(1, mid);
		rs = sqlPrepared.executeQuery();
		while (rs.next()) {
			dishId = rs.getInt(1);
			name = rs.getString(2);
			price = rs.getFloat(3);
			filePath = rs.getString(4);
			description = rs.getString(5);
			Dish dish = new Dish(String.valueOf(dishId), price, name, description,filePath);
			//put dish into identity map
			IdentityMap<Dish> map = IdentityMap.getInstance(dish);
			map.put(dishId,dish);
			
			result.add(dish);
		}
		sqlPrepared.close();
		DBConnection.dbConnection.close();
		return result;
	}
	
	
	@Override
	public void create(Object obj) {
		Dish dish = (Dish)obj;
		String sql = "INSERT INTO dish (menu_id, price, name, description, picture, modified_time) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(dish.getMenuId()));
			sqlPrepared.setFloat(2, dish.getPrice());
			sqlPrepared.setString(3, dish.getDishName());
			sqlPrepared.setString(4, dish.getDescription());
			sqlPrepared.setString(5, dish.getFilePath());
			sqlPrepared.setDate(6, new Date(new java.util.Date().getTime()));
			sqlPrepared.executeUpdate();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(Object obj) {
		Dish dish = (Dish) obj;
		
		try {
			//update
			String sql = "UPDATE dish SET name=?, description=?, price=?, picture=?, version=?,"
					+ "modified_time=? where dish_id=?";
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setString(1, dish.getDishName());
			sqlPrepared.setString(2, dish.getDescription());
			sqlPrepared.setFloat(3, dish.getPrice());
			sqlPrepared.setString(4, dish.getFilePath());
			sqlPrepared.setInt(5, dish.getVersionNo()+1);
			sqlPrepared.setDate(6, new Date(new java.util.Date().getTime()));
			sqlPrepared.setInt(7, Integer.parseInt(dish.getDishId()));
			
			
			//before updating, check the version number in DB at first
			String dishId = dish.getDishId();
			String sqlfindversion = "SELECT version, modified_time FROM dish" + " WHERE dish_id = ?";
			
			ResultSet rs;
			PreparedStatement sqlPreparedForQuery;
			sqlPreparedForQuery = DBConnection.prepare(sqlfindversion);
			sqlPreparedForQuery.setString(1, dishId);
			rs = sqlPreparedForQuery.executeQuery();
			if(rs.next()) {
				int rsVersion = rs.getInt(1);
				Date modifiedDate = rs.getDate(2);
				System.out.println(rsVersion);
				//if the version in DB is greater than the current updating dish
				//throw a exception.
				if(rsVersion > dish.getVersionNo()) {
					try {
						throw new Exception("Row " + dish.getDishId() + " in"
								+ "table dish was modified at " + modifiedDate.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					sqlPrepared.executeUpdate();
					
				}
				
			}
			DBConnection.dbConnection.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void delete(Object obj) {
		Dish dish = (Dish) obj;
		String sql = "DELETE FROM dish where dish_id=?";
		try {
			PreparedStatement sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(dish.getDishId()));
			sqlPrepared.executeUpdate();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Object find(Object obj) {
		Dish dish = (Dish) obj;
		String dishId = dish.getDishId();
		String sql = "SELECT name, price, picture, description, version FROM dish" + " WHERE dish_id = ?";
		ResultSet rs;
		PreparedStatement sqlPrepared;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setString(1, dishId);
			rs = sqlPrepared.executeQuery();
			while (rs.next()) {
				dish.setDishName(rs.getString(1));
				dish.setPrice(rs.getFloat(2));
				dish.setFilePath(rs.getString(3));
				dish.setDescription(rs.getString(4));
				dish.setVersionNo(rs.getInt(5));
				//put dish into identity map
				IdentityMap<Dish> map = IdentityMap.getInstance(dish);
				map.put(Integer.valueOf(dishId),dish);
				sqlPrepared.close();
				DBConnection.dbConnection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dish;
	}
	
	public HashMap<String, Integer> getAllDishByOrderId(String oid){
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String sql =  "SELECT dish_id, qty FROM order_dish WHERE order_id = ?";
		ResultSet rs;
		PreparedStatement sqlPrepared;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.setInt(1, Integer.parseInt(oid));
			rs = sqlPrepared.executeQuery();
			while (rs.next()){
				result.put(String.valueOf(rs.getInt(1)),rs.getInt(2));
				
			}
			sqlPrepared.close();
			DBConnection.dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	public static int getVersionNo(Dish dish) {
		
		String sqlfindversion = "SELECT version FROM dish" + " WHERE dish_id = ?";
		ResultSet rs;
		PreparedStatement sqlPreparedForQuery;
		int rsVersion = 0;
		try {
			sqlPreparedForQuery = DBConnection.prepare(sqlfindversion);
			sqlPreparedForQuery.setString(1, dish.getDishId());
			rs = sqlPreparedForQuery.executeQuery();
			while(rs.next()) {
				rsVersion = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsVersion;
	}

}
