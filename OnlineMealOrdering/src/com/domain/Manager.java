package com.domain;

import java.sql.SQLException;
import java.util.ArrayList;

import com.datasource.ManagerMapper;
import com.datasource.MenuMapper;
import com.datasource.OrderMapper;

public class Manager {
	private String name;
	private String managerId;
	
	public Manager(String id, String name){
		this.managerId = id;
		this.name = name;
	}
	public static Manager login(String username, String pwd) {
		Manager manager = null;
		try {
			manager = ManagerMapper.findWithusernamePwd(username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
	}
	public ArrayList<Order> viewAllOrder(String mid){
		return new OrderMapper().findOrderByMenuId(mid);
	}
	
	public void createMenu(Menu menu) {
		menu.setManagerId(this.managerId);
		new MenuMapper().create(menu);
	}
	
	public Menu getMenu() throws SQLException {
		return MenuMapper.findMenuByManagerId(this.managerId);
	}
	public void deleteMenu(String menuId) {
		Menu menu = new Menu();
		menu.setMenuId(menuId);
		new MenuMapper().delete(menu);
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
