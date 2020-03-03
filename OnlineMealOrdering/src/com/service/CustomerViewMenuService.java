		package com.service;

import java.io.IOException;
import java.sql.SQLException;
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
import com.domain.Manager;
import com.domain.Menu;

/**
 * Servlet implementation class CustomerViewMenuService
 */
@WebServlet("/CustomerViewMenuService")
public class CustomerViewMenuService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerViewMenuService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String resturantName=request.getParameter("resturantName");
		String menuId = request.getParameter("menuId");
		System.out.println("menuId"+menuId);
		
//		ArrayList<Dish> dishes = Menu.getAllDishes().getDishesByMenuId(menuId);
		
		request.setAttribute("resturantName", resturantName);
		request.setAttribute("menuId", menuId);
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		session.setAttribute("resturantName", resturantName);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_viewMenu.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
