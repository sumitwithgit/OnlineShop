package com.onlineshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineshop.Helper.Message;
import com.onlineshop.dao.userDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.user;

/**
 * Servlet implementation class changeAddress
 */
@WebServlet("/changeAddress")
@MultipartConfig
public class changeAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
//		int id=Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		int pincode=Integer.parseInt(request.getParameter("pincode"));
		
		user user=new user(email, address, city, state, country, pincode);
		
		
		userDao udao=new userDao(connectionProvider.getConnection());
		
		boolean f=udao.updateAddress(user);
		
		if(f)
		{
			out.write("done");
			Message m=new Message("Your Address Updated Successfully", "success", "alert-success");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("editDetails.jsp");
		}else
		{
			out.write("done");
			Message m=new Message("Something Went Wrong.", "error", "alert-danger");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("editDetails.jsp");
		}
	}

}
