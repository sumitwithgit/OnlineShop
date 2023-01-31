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
import com.onlineshop.dao.productDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.product;

/**
 * Servlet implementation class deleteProduct
 */
@WebServlet("/deleteProduct")
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		productDao pdao=new productDao(connectionProvider.getConnection());
		
		boolean f=pdao.deleteProduct(id);
		
		if(f) {
			out.write("done");
			HttpSession session=request.getSession();
			Message m=new Message("Item Deleted Successfully.", "success", "alert-success");
			session.setAttribute("msg", m);
			
			response.sendRedirect("viewAndEdit.jsp");
			
			
		}else {
			out.write("failed");
			HttpSession session=request.getSession();
			Message m=new Message("Something Went Wrong", "error", "alert-danger");
			session.setAttribute("msg", m);
			
			response.sendRedirect("viewAndEdit.jsp");
		}
		
		
	}

}
