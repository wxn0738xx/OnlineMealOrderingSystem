package com.domain;

import java.sql.SQLException;
import java.util.ArrayList;

import com.datasource.DishMapper;
import com.datasource.MenuMapper;

public class Menu {
	private String menuId;
	private String managerId;
	private ArrayList<Dish> dish;
	private String resturantName;
	
	/**
	 * used when new dish is created
	 * @param menuId
	 * @param resturantName
	 * @param ownerId
	 */
	public Menu(String menuId, String resturantName,String ownerId){
		this.menuId = menuId;
		this.resturantName = resturantName;
		this.managerId = ownerId;
	}
	
	/**
	 * Used when manager create a menu
	 * @param managerId
	 * @param resturantName
	 */
	public Menu(String managerId, String resturantName) {
		this.managerId = managerId;
		this.resturantName = resturantName;
	}
	
	public Menu(String managerId,ArrayList<Dish> dish, String resturantName){
		this.managerId = managerId;
		this.dish = dish;
		this.resturantName = resturantName;
	}
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return all the dishes that belong to the menu
	 * @throws SQLException 
	 */
	public ArrayList<Dish> getAllDishes() throws SQLException {
		
		return DishMapper.getDishesByMenuId(this.getMenuId());
	}

//	public static void deleteMenu(String menuId) {
//		Menu menu = new Menu();
//		menu.setMenuId(menuId);
//		new MenuMapper().delete(menu);
//	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public ArrayList<Dish> getDish() {
		return dish;
	}

	public void setDish(ArrayList<Dish> dish) {
		this.dish = dish;
	}

	//lazy load is used to get restaurant name
	public String getResturantName() {
		if(this.resturantName == null) {
			try {
				Menu menu = MenuMapper.findMenuByManagerId(this.managerId);
				this.resturantName = menu.getResturantName();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resturantName;
	}

	public void setResturantName(String resturantName) {
		this.resturantName = resturantName;
	}
	
	public static String getResturantName(String mid) {
		String resturantName="";
			try {
				Menu menu = MenuMapper.findMenuByManagerId(mid);
				resturantName = menu.getResturantName();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return resturantName;
	}
	
	

}
