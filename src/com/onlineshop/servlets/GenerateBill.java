package com.onlineshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.onlineshop.Helper.Message;
import com.onlineshop.dao.cartDao;
import com.onlineshop.dao.orderDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.cart;
import com.onlineshop.entities.order;

/**
 * Servlet implementation class GenerateBill
 */
@WebServlet("/GenerateBill")
public class GenerateBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateBill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int flag=0;
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		String orderStatus=request.getParameter("orderStatus");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		int mobile=Integer.parseInt(request.getParameter("mobile"));
		int pincode=Integer.parseInt(request.getParameter("pincode"));
		String paymentOption=request.getParameter("paymentOption");
		String tid=request.getParameter("tid");
		
		cartDao cdao=new cartDao(connectionProvider.getConnection());
		ArrayList<cart> clist=cdao.getAllProductByUserIdFromCart(userId);
		for(cart c:clist)
		{
			int productId=c.getProductId();
			String productName=c.getProductName();
			int productQuantity=c.getProductQuantity();
			int productPrice=c.getProductPrice();
			int totalPrice=c.getTotalPrice();
			
			order order=new order(userId, productId, productName, productQuantity, productPrice, totalPrice, address, city, state, country, mobile, pincode, paymentOption, tid, orderStatus);
			
			
			orderDao odao=new orderDao(connectionProvider.getConnection());
			boolean f=odao.orderInProcess(order);
			
			if(f) {
				cdao.removeFromCartByUserId(userId);
				out.write("done");
				HttpSession sesison=request.getSession();
				Message m=new Message("Order SuccessFully Placed. Continue Shopping.", "success", "alert-success");
				
				sesison.setAttribute("msg", m);
				flag=1;
//				response.sendRedirect("myCart.jsp");
			}else {
				out.write("failed");
				HttpSession sesison=request.getSession();
				Message m=new Message("Something Went Wrong.You Can Try Again.", "error", "alert-danger");
				
				sesison.setAttribute("msg", m);
//				response.sendRedirect("payBill.jsp");
			}
			
		}
		if(flag==1) {
			response.sendRedirect("userProfile.jsp");
		}else {
			response.sendRedirect("payBill.jsp");
		}
		
		
	}

}
