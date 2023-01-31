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
 * Servlet implementation class login
 */
@WebServlet("/login")
@MultipartConfig
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
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

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		userDao udao = new userDao(connectionProvider.getConnection());
		user user = udao.loginUser(email, password);

		if (user != null) {
			if (user.getUserType().equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				Message m = new Message("Login Successfully", "success", "alert-success");

				session.setAttribute("msg", m);
				response.sendRedirect("adminProfile.jsp");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				Message m = new Message("Login Successfully", "success", "alert-success");

				session.setAttribute("msg", m);

				response.sendRedirect("userProfile.jsp");
			}
		} else {
			HttpSession session = request.getSession();
			Message m = new Message("Something Went Wrong.Try Again", "error", "alert-danger");

			session.setAttribute("msg", m);

			response.sendRedirect("login.jsp");

		}

	}

}
