package com.remotetools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datasource.DishMapper;
import com.domain.Dish;
import com.domain.Order;

public class OrderAssembler {
	
	public OrderDTO writeDTO(Order order) { 
		OrderDTO result = new OrderDTO();
		result.setAddress(order.getAddress());
		result.setCreateTime(order.getCreateTime());
		result.setCustomerId(order.getCustomerId());
		result.setMenuId(order.getMenuId());
		writeDishes(result, order);
		return result;
	}
	
	private void writeDishes(OrderDTO orderDTO, Order order) {
		List<DishDTO> dishes = new ArrayList<DishDTO>();
		Iterator<String> it = order.getDishQty().keySet().iterator();
		while(it.hasNext()) {
			DishDTO newDTO = new DishDTO();
			String dishId = it.next();
			Dish dish = new Dish();
			dish.setDishId(dishId);
			dish = (Dish) new DishMapper().find(dish);
			newDTO.setDescription(dish.getDescription());
			newDTO.setDishId(dish.getDishId());
			newDTO.setDishName(dish.getDishName());
			newDTO.setFilePath(dish.getFilePath());
			newDTO.setMenuId(dish.getMenuId());
			newDTO.setPrice(dish.getPrice());
			dishes.add(newDTO);
		}
		orderDTO.setDishes(dishes);
	}
	

}
