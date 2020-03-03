package com.remotetools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import com.datasource.OrderMapper;
import com.domain.Order;

public class OrderServiceBean {
	public OrderDTO getOrder(String id) throws RemoteException{
		Order order = new Order(id);
		return new OrderAssembler().writeDTO((Order)new OrderMapper().find(order));
	}
	
	public void getOrderJson(String id, DataOutputStream output) throws RemoteException{
		OrderDTO dto = getOrder(id);
		OrderDTO.toJson(dto, output);
	}
	
//	public void updateOrder(String id, DataInputStream input) {
//		try {
//			OrderDTO dto = OrderDTO.fromJson(input);
//			updateOrder(id, dto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	
}
