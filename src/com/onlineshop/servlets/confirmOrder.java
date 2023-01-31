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
import com.onlineshop.dao.orderDao;
import com.onlineshop.db.connectionProvider;

/**
 * Servlet implementation class confirmOrder
 */
@WebServlet("/confirmOrder")
public class confirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		String orderStatus=request.getParameter("orderStatus");
		
		
		if(orderStatus.equals("accept"))
		{
			orderDao odao=new orderDao(connectionProvider.getConnection());
			boolean a=odao.updateAcceptOrdersByOrderIdAndOrderStatus(orderId);
			if(a)
			{
				out.write("done");
				Message m=new Message("Order accepted.", "success", "alert-success");
				HttpSession session=request.getSession();
				
				session.setAttribute("msg", m);
				response.sendRedirect("orderReceived.jsp");
			}else 
			{
				out.write("failed");
				Message m=new Message("Something Went Wrong", "error", "alert-danger");
				HttpSession session=request.getSession();
				
				session.setAttribute("msg", m);
				response.sendRedirect("orderReceived.jsp");
			}
		}
		
		if(orderStatus.equals("reject"))
		{
			orderDao odao=new orderDao(connectionProvider.getConnection());
			boolean a=odao.updateRejectOrdersByOrderIdAndOrderStatus(orderId);
			if(a)
			{
				out.write("done");
				Message m=new Message("Order Rejected.", "success", "alert-success");
				HttpSession session=request.getSession();
				
				session.setAttribute("msg", m);
				response.sendRedirect("orderReceived.jsp");
			}else 
			{
				out.write("failed");
				Message m=new Message("Something Went Wrong", "error", "alert-danger");
				HttpSession session=request.getSession();
				
				session.setAttribute("msg", m);
				response.sendRedirect("orderReceived.jsp");
			}
		}
	}

}
