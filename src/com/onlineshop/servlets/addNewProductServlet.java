package com.onlineshop.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.onlineshop.Helper.Message;
import com.onlineshop.dao.productDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.product;

/**
 * Servlet implementation class addNewProductServlet
 */
@WebServlet("/addNewProductServlet")
@MultipartConfig
public class addNewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		String pCategory=request.getParameter("pCategory");
		String pName=request.getParameter("pName");
		String pDescription=request.getParameter("pDescription");
		int pPrice=Integer.parseInt(request.getParameter("pPrice"));
		
		
		Part part=request.getPart("pPhoto");
		String photoName=part.getSubmittedFileName();
		
		String path=getServletContext().getRealPath("")+"cImg"+File.separator+photoName;
		
		
		product product=new product(pName, pDescription, photoName, pPrice, pCategory);
		
		productDao cdao=new productDao(connectionProvider.getConnection());
		
		boolean f=cdao.insertProduct(product);
		
		if(f) {
			File file=new File(path);
			part.write(path);
			
			out.write("done");
			Message m=new Message("Product inserted Successfully.", "success", "alert-success");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			
			response.sendRedirect("viewAndEdit.jsp");
			
		}else {
			out.write("failed");
			Message m=new Message("Somethiing Went Wrong.", "error", "alert-danger");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			
			response.sendRedirect("adminProfile.jsp");
		}
	}

}
