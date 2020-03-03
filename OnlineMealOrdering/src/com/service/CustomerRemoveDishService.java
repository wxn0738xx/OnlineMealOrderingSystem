package com.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Customer;
import com.domain.Dish;

/**
 * Servlet implementation class CustomerRemoveDishService
 */
@WebServlet("/CustomerRemoveDishService")
public class CustomerRemoveDishService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRemoveDishService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Dish> currentdishList = new ArrayList<Dish>();
		HttpSession session = request.getSession();
	    currentdishList = (ArrayList<Dish>) session.getAttribute("dishList");
		
		String dishId = request.getParameter("dishId");
//		System.out.println("passed in"+dishId);
	
		for(Object o : currentdishList){
//		    System.out.println("o is "+o);
		   String currentdishId=((Dish) o).getDishId();

//		    System.out.println("currentdishId "+currentdishId);
		   System.out.println("a1111111 "+currentdishId+"  "+dishId);
		    if(currentdishId.equals(dishId)) {
		    	System.out.println("already remove "+o);
		    	currentdishList.remove((Dish)o);
		    	
		    }
		    break;

		}
		session.setAttribute("dishList", currentdishList);
		
		
	    ArrayList<Dish> a = (ArrayList<Dish>) session.getAttribute("dishList");
	    
	   System.out.println("a"+a);

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/customer_viewOrder.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
