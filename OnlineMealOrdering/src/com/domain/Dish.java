package com.domain;

import java.sql.SQLException;

import com.datasource.DishMapper;
import com.datasource.IdentityMap;
import com.datasource.MenuMapper;
import com.datasource.UnitOfWork;

public class Dish extends DomainObject{
	private String dishId;
	private float price;
	private String dishName;
	private String filePath;
	private String description;
	private String menuId;
	private int versionNo;
	
	public Dish(){
	}
	
	public Dish(String dishId, float price, String dishName, String description,String filePath){
		super(dishId);
		this.dishId = dishId;
		this.price = price;
		this.dishName = dishName;
		this.filePath = filePath;
		this.description = description;
		
	}
	
	
	public static void deleteDish(String id) {
		Dish dish = new Dish();
		dish.setDishId(id);
		new DishMapper().delete(dish);
	}
	
	public static void createDish(String dishName, String price, String description, 
			String filePath,String managerId) {
		Dish dish = new Dish();
		dish.setDishName(dishName);
		dish.setPrice(Float.valueOf(price));
		dish.setDescription(description);
		dish.setFilePath(filePath);
		Menu menu = null;
		try {
			menu = MenuMapper.findMenuByManagerId(managerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dish.setMenuId(menu.getMenuId());
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(dish);
		UnitOfWork.getCurrent().commit();
//		return DishMapper.saveDish(dish);
	}
	
	public static void updateDish(String dishName, String price, String description, 
			String filePath,String dishId) {
		
		Dish dish = new Dish(dishId, Float.valueOf(price), dishName, description, filePath);
		dish.setVersionNo(DishMapper.getVersionNo(dish));
		new DishMapper().update(dish);
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	

}
