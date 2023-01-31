package com.onlineshop.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.onlineshop.Helper.Helper;
import com.onlineshop.Helper.Message;
import com.onlineshop.dao.userDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.user;

/**
 * Servlet implementation class editServlet
 */
@WebServlet("/editServlet")
@MultipartConfig
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int mobile = Integer.parseInt(request.getParameter("mobile"));
		String usertype=request.getParameter("type");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");

		Part part=request.getPart("newPic");
		String picName=part.getSubmittedFileName();
		
		String path=getServletContext().getRealPath("")+"img"+File.separator+picName;
		
		int pincode = Integer.parseInt(request.getParameter("pincode"));

	
		
		
		user user = new user(id, name, password, mobile, picName, address, city, state, country, pincode);

		userDao udao = new userDao(connectionProvider.getConnection());

		boolean f = udao.editProfile(user);

		if (f) {
			File file=new File(path);
			
			part.write(path);
			if(usertype.equals("admin"))
			{
				HttpSession session = request.getSession();
				Message m = new Message("Updates Successfully.", "success", "alert-success");
				session.setAttribute("msg", m);
				response.sendRedirect("adminProfile.jsp");
			}else {
				HttpSession session = request.getSession();
				Message m = new Message("Updates Successfully.", "success", "alert-success");
				session.setAttribute("msg", m);
				response.sendRedirect("userProfile.jsp");
			}
		} else {
			HttpSession session = request.getSession();
			Message m = new Message("Something Went Wrong", "error", "alert-danger");
			session.setAttribute("msg", m);
			response.sendRedirect("login.jsp");
		}

	}

}
