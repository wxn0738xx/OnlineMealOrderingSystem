package com.remotetools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.sf.json.JSONObject;

public class DishDTO {
	private String dishId;
	private float price;
	private String dishName;
	private String filePath;
	private String description;
	private String menuId;
	
	
	public DishDTO(String dishId, float price, String dishName, String description,String filePath){

		this.dishId = dishId;
		this.price = price;
		this.dishName = dishName;
		this.filePath = filePath;
		this.description = description;
		
	}
	
	public DishDTO() {
		// TODO Auto-generated constructor stub
	}

	public static void toJson(DishDTO dishDTO, DataOutputStream output) {
		 JSONObject json = JSONObject.fromObject(dishDTO);
	     String str = json.toString(); 
	     try {
			output.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DishDTO fromJson(DataInputStream input) throws IOException {
		 String jsonStr = input.readUTF();
		 JSONObject obj = JSONObject.fromObject(jsonStr);
		 DishDTO dishDTO = (DishDTO) JSONObject.toBean(obj, DishDTO.class);  
	     return dishDTO;
		
	}
	
	//getters and setters
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
	
	
	

}
