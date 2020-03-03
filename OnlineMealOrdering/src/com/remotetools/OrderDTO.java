package com.remotetools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import net.sf.json.JSONObject;


public class OrderDTO {
	private String customerId;
	private String menuId;
	private float totalPrice;
	private String address;
	private Date createTime;
	private List<DishDTO> dishes;
	private List<Integer> quantities;
	
	
	public OrderDTO(String cid, String mid, float price, String address,
			Date createTime, List<DishDTO> dishes, List<Integer> qty) {
		this.customerId = cid;
		this.menuId = mid;
		this.totalPrice = price;
		this.address = address;
		this.createTime = createTime;
		this.dishes = dishes;
		this.quantities = qty;
	}
	
	public OrderDTO() {
	}

	public static void toJson(OrderDTO orderDTO, DataOutputStream output) {
		 JSONObject json = JSONObject.fromObject(orderDTO);
	     String str = json.toString(); 
	     try {
			output.writeUTF(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static OrderDTO fromJson(DataInputStream input) throws IOException {
		 String jsonStr = input.readUTF();
		 JSONObject obj = JSONObject.fromObject(jsonStr);
		 OrderDTO orderDTO = (OrderDTO) JSONObject.toBean(obj, OrderDTO.class);  
	     return orderDTO;
		
	}
	
	

	//getter and setters
	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public List<DishDTO> getDishes() {
		return dishes;
	}


	public void setDishes(List<DishDTO> dishes) {
		this.dishes = dishes;
	}


	public List<Integer> getQuantities() {
		return quantities;
	}


	public void setQuantities(List<Integer> quantities) {
		this.quantities = quantities;
	}
	
	
	
}
