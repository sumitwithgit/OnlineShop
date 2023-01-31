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
 * Servlet implementation class editPassword
 */
@WebServlet("/editPassword")
@MultipartConfig
public class editPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String confirmPassword=request.getParameter("confirmPassword");
		
		
		if(password.equals(oldPassword))
		{
			if(newPassword.equals(confirmPassword))
			{
				userDao udao=new userDao(connectionProvider.getConnection());
				
				user user=new user(id, email, newPassword);
				
				boolean f=udao.updatePassword(user);
				
				if(f) 
				{
					Message m=new Message("Password Updated Successfully.", "success", "alert-success");
					HttpSession session=request.getSession();
					
					session.setAttribute("msg", m);
					response.sendRedirect("editDetails.jsp");
				}
				else
				{
					Message m=new Message("Something Went Wrong.", "error", "alert-danger");
					HttpSession session=request.getSession();
					
					session.setAttribute("msg", m);
					response.sendRedirect("editDetails.jsp");
				}
			}
			else
			{
				Message m=new Message("New Password and Confirm Password doesn't Match", "error", "alert-danger");
				HttpSession session=request.getSession();
				
				session.setAttribute("msg", m);
				response.sendRedirect("editDetails.jsp");
			}
		}
		else
		{
			Message m=new Message("Your Old Password is not Correct.Try Again", "error", "alert-danger");
			HttpSession session=request.getSession();
			
			session.setAttribute("msg", m);
			response.sendRedirect("editDetails.jsp");
		}
	}

}
