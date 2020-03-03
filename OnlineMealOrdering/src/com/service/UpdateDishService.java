package com.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.domain.Dish;
import com.domain.Manager;
import com.securitytools.InterceptingValidator;

/**
 * Servlet implementation class UpdateDishService
 */
@WebServlet("/UpdateDishService")
public class UpdateDishService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InterceptingValidator validator = new InterceptingValidator();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDishService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dishName = request.getParameter("dishName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String dishId = request.getParameter("dishId");
		String filename=request.getParameter("filename");
		

		request.setAttribute("dishId", dishId);
		request.setAttribute("dishName", dishName);
		request.setAttribute("price", price);
		request.setAttribute("description", description);
		request.setAttribute("filename", filename);

		RequestDispatcher dispatcher = request.getRequestDispatcher("update_dish.jsp");
		dispatcher.forward(request, response);
		}
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String dishName = request.getParameter("dishName");
//		String price = request.getParameter("price");
//		String description = request.getParameter("description");
//		String dishId = request.getParameter("dishId");
//
//		request.setAttribute("dishId", dishId);
//		request.setAttribute("dishName", dishName);
//		request.setAttribute("price", price);
//		request.setAttribute("description", description);
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher("update_dish.jsp");
//		dispatcher.forward(request, response);
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator iter = items.iterator();
		String dishId = null;
		String dishName = null;
		String price = null;
		String description = null;
		String filePath = null;
		// if non file is upload for update, use original filename
		String originfilename=null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) {
				String value = item.getString("UTF-8");
				if (item.getFieldName().equals("dishName")) {
					dishName = value;
//					System.out.println("dishName:" + dishName);
				} else if (item.getFieldName().equals("dishPrice")) {
					price = value;
//					System.out.println("dishPrice:" + price);
				
				} 
				else if (item.getFieldName().equals("dishId")) {
					dishId = value;
//					System.out.println("dishId:" + dishId);
				
				} else if (item.getFieldName().equals("description")) {
					description = value;
//					System.out.println("description:" + description);
				}
				else if (item.getFieldName().equals("filename")) {
					originfilename = value;
//					System.out.println("originfilename:" + originfilename);
				}
			} else {

				// String fieldName = item.getFieldName();

				String filename = item.getName();

				// String t1 = System.getProperty("user.dir").substring(0,
				// System.getProperty("user.dir").length() - 4);
				File saveFile = new File(getServletContext().getRealPath("/Dishes_img/") + filename);
//				System.out.println(saveFile);
				filePath = filename;

				try {
					item.write(saveFile);
				} catch (Exception e) {
					/* e.printStackTrace(); */
//					System.out.println("file is null");
					filePath=originfilename;
//					System.out.println("originfilename is:"+originfilename);
				}

			}
		}
		/**
		 * 
		 */
		//HttpSession session = request.getSession();
//		Dish.updateDish(dishName, price, description, filePath, dishId);
//		PrintWriter pw = response.getWriter();
//		pw.write("<script language='javascript'>alert('The dish has been updated');"
//				+ "window.history.back();</script>");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/manager_menu.jsp");
//		dispatcher.forward(request, response);
		
		if(this.validator.validatePrice(price)) {
			Dish.updateDish(dishName, price, description, filePath, dishId);
			PrintWriter pw = response.getWriter();
			pw.write("<script language='javascript'>alert('The dish has been updated'); </script>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/manager_menu.jsp");
			dispatcher.forward(request, response);
		}else {
			PrintWriter pw = response.getWriter();
			pw.write("<script language='javascript'>alert('The price should be a number'); window.history.back(); </script>");
		}

	}

}
