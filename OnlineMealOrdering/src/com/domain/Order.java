package com.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.datasource.DishMapper;

public class Order extends DomainObject{
	private String customerId;
	private String menuId;
	private float totalPrice;
	private String address;
	private Date createTime;
	private HashMap<String, Integer> dish_Qty;
	
	private String orderId;
	
	public Order() {};
	
	public Order(String orderId){
		super(orderId);
	}
	
	/**
	 * Calculate total price of the order
	 */
	public void calculateTotalPrice() {
		//get the price of each dish and sum them up
		for (Map.Entry<String, Integer> e : dish_Qty.entrySet()) {
			Dish dish = new Dish();
			dish.setDishId(e.getKey());
			dish = (Dish)new DishMapper().find(dish);
			this.totalPrice += dish.getPrice()*e.getValue();
		}
	}
	
	public ArrayList<Dish> getAllDish(){
		ArrayList<Dish> result = new ArrayList<Dish>();
		Set<String> dishIdSet = new DishMapper().getAllDishByOrderId(this.getOrderId()).keySet();
		for(String id : dishIdSet) {
			Dish dish = new Dish();
			dish.setDishId(id);
			result.add((Dish)new DishMapper().find(dish));
		}
		System.out.println("dish:"+ result);
		return result;
	}
	
	public ArrayList<Integer> getAllDishQty(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		HashMap<String, Integer> dishqty = new DishMapper().getAllDishByOrderId(this.getId());
		for(String key:dishqty.keySet()) {
			result.add(dishqty.get(key));
		}
		return result;
	}
	
	public HashMap<String, Integer> getDishQty() {
		return dish_Qty;
	}
	public void setDishQty(HashMap<String, Integer> dish_Qty) {
		this.dish_Qty = dish_Qty;
	}
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	
}
