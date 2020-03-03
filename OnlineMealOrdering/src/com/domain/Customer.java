package com.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.datasource.CustomerMapper;
import com.datasource.DishMapper;
import com.datasource.ManagerMapper;
import com.datasource.MenuMapper;
import com.datasource.OrderMapper;

public class Customer extends DomainObject {
	private String cid;
	private String name;
	private String phoneNumber;
	private String username;
	private String password;

	public Customer() {
	}

	public Customer(String id, String name) {
		this.cid = id;
		this.name = name;
	}

	public void register() {
		new CustomerMapper().create(this);
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void createOrder(String menuId, Date time, String address, HashMap<String, Integer> dishQty) {

		Order order = new Order();
		order.setMenuId(menuId);
		order.setCreateTime(time);
		order.setAddress(address);
		order.setDishQty(dishQty);
		order.calculateTotalPrice();
		order.setCustomerId(this.getCid());
		new OrderMapper().create(order);
	}

	public static void cancelOrder(String orderId) {
		new OrderMapper().delete(new Order(orderId));
	}

	public static Customer login(String username, String pwd) {
		Customer customer = null;
		try {
			customer = CustomerMapper.findWithusernamePwd(username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public HashMap<Integer, String> getMenu() throws SQLException {
		return MenuMapper.getAllRestaurant();
	}

	/**
	 * get all orders of a customer
	 * 
	 * @return
	 */
	public ArrayList<Order> getAllHistoryOrder() {
		return new OrderMapper().findOrderByCusomterId(this.getCid());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Dish> getDishes(String menuId) throws SQLException {
		return DishMapper.getDishesByMenuId(menuId);
	}

}
