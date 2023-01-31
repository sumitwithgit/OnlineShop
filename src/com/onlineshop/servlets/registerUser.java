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
 * Servlet implementation class registerUser
 */
@WebServlet("/registerUser")
@MultipartConfig
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		int mobile=Integer.parseInt(request.getParameter("mobile"));
		
		user user=new user(name, email, password, mobile);
		
		userDao udao=new userDao(connectionProvider.getConnection());
		
		boolean f=udao.registerUser(user);
		
		if(f) {
			out.write("done");
			Message m=new Message("Registration Successfully Done.", "success", "alert-success");
			HttpSession session=request.getSession();
			
			session.setAttribute("msg", m);
			
			response.sendRedirect("register.jsp");
		}else {
			out.write("failed");
			Message m=new Message("Someting Went Wrong.Try Again", "error", "alert-danger");
			
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			
			response.sendRedirect("register.jsp");
		}
	}

}
