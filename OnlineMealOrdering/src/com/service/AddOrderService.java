package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datasource.IdentityMap;
import com.domain.Customer;
import com.domain.Dish;
import com.domain.Manager;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class AddOrderService
 */
@WebServlet("/AddOrderService")
public class AddOrderService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Dish> dishList = new ArrayList<Dish>();
	String menuId;
	
//	ArrayList List= new ArrayList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	Float currentPrice;
	String address = request.getParameter("address");
	System.out.println("address"+address);
	
	
	HashMap<String, Integer> map =  new HashMap<String, Integer>();
	System.out.println("dishList is "+dishList);
//	Customer.createOrder(1, null, address, null);
	for(Object o : dishList){
	    System.out.println("o is "+o);
	    currentPrice=((Dish) o).getPrice();

	    //System.out.println("o is price "+((Dish) o).getPrice());
	    Dish d = (Dish)o;
	    if(map.containsKey(d.getDishId())) {
	    	map.put(d.getDishId(), map.get(d.getDishId()+1));
	    }else {
	    	map.put(d.getDishId(), 1);
	    }
	    

	}
System.out.println("map"+map);

	java.util.Date date = new java.util.Date();
	Date d = new Date(date.getTime());
	HttpSession session = request.getSession();
	Customer customer = (Customer) session.getAttribute("customer");
	customer.createOrder(menuId, d,address,map);
	
	PrintWriter pw = response.getWriter();
	pw.write("<script language='javascript'>alert('Order has been made successfully')</script>");
	dishList.clear();
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/customer_viewResturant.jsp");
	
	dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 menuId = request.getParameter("menuId");
	
		String dishId = request.getParameter("dishId");
		String dishName = request.getParameter("dishName");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String filePath = request.getParameter("filePath");
	//	System.out.println("AddOrderService.dopost");
		Dish dish=new Dish(dishId, Float.parseFloat(price), dishName, description,filePath);
		
		dishList.add(dish);
//        dishList.add(dishId);
//        object.put("a1", "change");
//        System.out.println(dishList.toString());
		
		System.out.println("Array List:"+dishList);
		
		HttpSession session = request.getSession();
		session.setAttribute("dishList", dishList);
		session.setAttribute("menuId", menuId);
	
		session.getAttribute("resturantName");
		String res = (String) session.getAttribute("resturantName");
		System.out.println("res"+res);
		
		
//		Dish.deleteDish(dishId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer_viewOrder.jsp");
		dispatcher.forward(request, response);
	}

}
