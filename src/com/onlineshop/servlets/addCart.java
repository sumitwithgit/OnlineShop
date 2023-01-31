package com.onlineshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineshop.Helper.Message;
import com.onlineshop.dao.cartDao;
import com.onlineshop.dao.productDao;
import com.onlineshop.dao.userDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.cart;
import com.onlineshop.entities.product;
import com.onlineshop.entities.user;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int pid = Integer.parseInt(request.getParameter("pid"));
		int uid = Integer.parseInt(request.getParameter("uid"));

		int price = Integer.parseInt(request.getParameter("price"));

		productDao pdao = new productDao(connectionProvider.getConnection());

		product product = pdao.getProductDetailsById(pid);

		userDao udao = new userDao(connectionProvider.getConnection());

		user user = udao.getUserDetailsById(uid);

		cartDao cdao = new cartDao(connectionProvider.getConnection());

		cart cart = new cart();
		int quantity = cdao.getProductQuantity(uid, pid);

		System.out.println(quantity);

		int totalPrice = 0;
		if (quantity == 0) {
			cart.setUserId(uid);
			cart.setProductId(pid);
			cart.setProductName(product.getpName());
			cart.setProductPhoto(product.getpPhoto());
			cart.setProductQuantity(1);

			totalPrice += product.getpPrice();

			cart.setProductPrice(product.getpPrice());

			cart.setTotalPrice(totalPrice);

			boolean f = cdao.addItemToCart(cart, uid);

			if (f) {
				out.write("done");
				Message m = new Message("Product Added To Cart", "success", "alert-success");
				HttpSession session = request.getSession();
				session.setAttribute("msg", m);
				response.sendRedirect("userProfile.jsp");

			} else {
				out.write("failed");
				Message m = new Message("Something Went Wrong", "error", "alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("msg", m);
				response.sendRedirect("userProfile.jsp");

			}
		} else {
			out.write("failed");
			Message m = new Message("Product Already Added in Cart. For increasing quantity go to MyCart.", "error", "alert-danger");
			HttpSession session = request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("userProfile.jsp");

		}

	}

}
