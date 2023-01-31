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

/**
 * Servlet implementation class removeItemFromCart
 */
@WebServlet("/removeItemFromCart")
public class removeItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeItemFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int itemNo=Integer.parseInt(request.getParameter("itemNo"));
		
		cartDao cdao=new cartDao(connectionProvider.getConnection());
		
		boolean f=cdao.removeItemFormCart(itemNo);
		
		if(f) {
			out.write("done");
			Message m=new Message("Product Removed From Cart", "error", "alert-danger");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("myCart.jsp");
		}else {
			out.write("failed");
			Message m=new Message("Something Went Wrong.", "error", "alert-danger");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("myCart.jsp");
		}
	}

}
