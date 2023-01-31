package com.onlineshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineshop.Helper.Message;
import com.onlineshop.dao.cartDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.cart;

/**
 * Servlet implementation class incDecQuantity
 */
@WebServlet("/incDecQuantity")
public class incDecQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public incDecQuantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		String quantityMsg=request.getParameter("quantity");
		int userId=Integer.parseInt(request.getParameter("userId"));
		int productId=Integer.parseInt(request.getParameter("productId"));
		
		
		cartDao cdao=new cartDao(connectionProvider.getConnection());
		cart cart=cdao.getProductDetailsByUserIdAndProductId(userId, productId);
		
		int productQuantity=cart.getProductQuantity();
		int productPrice=cart.getProductPrice();
		int totalPrice=cart.getTotalPrice();
		
		if(quantityMsg.equals("minus"))
		{
			if(productQuantity!=0)
			{
				productQuantity=productQuantity-1;
				totalPrice=totalPrice-productPrice;
				cart.setProductQuantity(productQuantity);
				cart.setProductPrice(productPrice);
				cart.setTotalPrice(totalPrice);
				
				boolean b=cdao.incDecQuantity(cart);
				
				if(b) {
					out.write("done");
					Message m = new Message("Quantity Decreased.", "success", "alert-success");
					HttpSession session = request.getSession();
					session.setAttribute("msg", m);
					response.sendRedirect("myCart.jsp");
				}else {
					out.write("failed");
					Message m = new Message("Something Went Wrong.", "error", "alert-danger");
					HttpSession session = request.getSession();
					session.setAttribute("msg", m);
					response.sendRedirect("myCart.jsp");
				}
				
			}else {
				out.write("failed");
				Message m = new Message("This Product is no longer in Your Cart.", "error", "alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("msg", m);
				response.sendRedirect("myCart.jsp");
			}
		}
		if(quantityMsg.equals("plus"))
		{
			productQuantity=productQuantity+1;
			totalPrice=totalPrice+productPrice;
			cart.setProductQuantity(productQuantity);
			cart.setProductPrice(productPrice);
			cart.setTotalPrice(totalPrice);
			
			boolean b=cdao.incDecQuantity(cart);
			
			if(b) {
				out.write("done");
				Message m = new Message("Quantity Increased.", "success", "alert-success");
				HttpSession session = request.getSession();
				session.setAttribute("msg", m);
				response.sendRedirect("myCart.jsp");
			}else {
				out.write("failed");
				Message m = new Message("Something Went Wrong.", "error", "alert-danger");
				HttpSession session = request.getSession();
				session.setAttribute("msg", m);
				response.sendRedirect("myCart.jsp");
			}
			
		}
	}

}
