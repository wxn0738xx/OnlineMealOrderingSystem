package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Customer;
import com.securitytools.AuthenticationEnforcer;
import com.securitytools.InterceptingValidator;

/**
 * Servlet implementation class RegisterService
 */
@WebServlet("/RegisterService")
public class RegisterService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterService() {
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
		
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String pwd = AuthenticationEnforcer.jdkSHA1(password);
		String phonenumber = request.getParameter("phonenumber");
		
		Customer c = new Customer();
		c.setUsername(username);
		c.setName(name);
		c.setPassword(pwd);
		c.setPhoneNumber(phonenumber);
		if (new InterceptingValidator().validateUsername(username)) {
		c.register();
		
		PrintWriter pw = response.getWriter();
		pw.write("<script language='javascript'>alert('Register successfully!')</script>");

		RequestDispatcher dispatcher = request.getRequestDispatcher("customer_login.html");
		dispatcher.forward(request, response);
		}else {
			PrintWriter pw = response.getWriter();
			pw.write("<script language='javascript'>alert('The username has to be a combination of number and letters!');window.history.back();</script>");
			
		}
	}

}
