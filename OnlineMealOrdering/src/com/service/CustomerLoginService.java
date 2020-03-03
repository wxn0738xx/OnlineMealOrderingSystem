package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.domain.Customer;
import com.domain.Manager;
import com.securitytools.AuthenticationEnforcer;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/CustomerLoginService")
public class CustomerLoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CustomerLoginService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
//		Manager manager = Manager.login(username, AuthenticationEnforcer.jdkSHA1(password));
		
		
//		Manager manager = AuthenticationEnforcer.authenticateManager(username, password);
		Customer customer = AuthenticationEnforcer.authenticateCustomer(username, password);
	
		if (customer!=null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/customer_viewResturant.jsp");

			dispatcher.forward(request, response);
			
		}

		else {
			PrintWriter pw = response.getWriter();
			pw.write(
					"<script language='javascript'>alert('Incorrect username or password!'); window.history.back();</script>");
		}
		// doGet(request, response);

	}

}
