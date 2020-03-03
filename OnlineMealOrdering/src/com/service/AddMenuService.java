package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Manager;
import com.domain.Menu;

/**
 * Servlet implementation class AddMenuService
 */
@WebServlet("/AddMenuService")
public class AddMenuService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMenuService() {
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
		String resturantName = request.getParameter("resturantName");
		//get the id of current user
		HttpSession session = request.getSession();
		Manager currentManager = (Manager)session.getAttribute("manager");
		Menu menu = new Menu(currentManager.getManagerId(), resturantName);
		currentManager.createMenu(menu);
		//Menu.createMenu(resturantName, currentManager.getManagerId());
			PrintWriter pw=response.getWriter();
			pw.write("<script language='javascript'>alert('The menu has bee created'); </script>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager_menu.jsp");
			dispatcher.forward(request, response);
//		doGet(request, response);
	}

}
