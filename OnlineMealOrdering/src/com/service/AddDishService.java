package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.domain.Dish;
import com.domain.Manager;
import com.securitytools.InterceptingValidator;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/AddDishService")
public class AddDishService extends HttpServlet {
	private InterceptingValidator validator = new InterceptingValidator();

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public AddDishService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		String dishName = request.getParameter("dishName");
//		String price = request.getParameter("dishPrice");
//		String description = request.getParameter("description");
//		String filePath = getServletContext().getRealPath("/WebContent/Dishes_img/w.jpg");
//		
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
		String dishName = null;
		String price = null;
		String description = null;
		String filePath = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (item.isFormField()) {
				String value = item.getString("UTF-8"); 
				if (item.getFieldName().equals("dishName")) {
					dishName = value;
					System.out.println("dishName:" + dishName);
				} else if (item.getFieldName().equals("dishPrice")) {
					price = value;
					System.out.println("dishPrice:" + price);
				} else if (item.getFieldName().equals("description")) {
					description = value;
					System.out.println("description:" + description);
				}
			} else {

				String fieldName = item.getFieldName(); 
				
				String filename = item.getName();
			
				String t1 = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").length() - 4);
				File saveFile = new File(getServletContext().getRealPath("/Dishes_img/") + filename); 

				filePath = filename;

				

				try {
					item.write(saveFile);
				} catch (Exception e) {
					/* e.printStackTrace(); */
				//	System.out.println("file is null");
					filePath = "default.png";
				}

			}
		}
	//	System.out.println("sss" + dishName + price + description + filePath);
		/**
		 * 
		 */
		HttpSession session = request.getSession();
		Manager manager = (Manager) session.getAttribute("manager");

		String managerId = manager.getManagerId();
if(this.validator.validatePrice(price)) {

	Dish.createDish(dishName, price, description, filePath, managerId);
		PrintWriter pw = response.getWriter();
		pw.write("<script language='javascript'>alert('The dish has bee added')</script>");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/manager_menu.jsp");
		dispatcher.forward(request, response);
	
}
else {
	PrintWriter pw = response.getWriter();
	pw.write("<script language='javascript'>alert('The price should be a number'); window.history.back(); </script>");
}
	
		
//		System.out.println("dishPrice:" + price + "dishName:" + dishName);
		
//		doGet(request, response);

	}

}
