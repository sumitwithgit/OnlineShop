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
 * Servlet implementation class editProductServlet
 */
@WebServlet("/editProductServlet")
@MultipartConfig
public class editProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProductServlet() {
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
		String name=request.getParameter("name");
		String description=request.getParameter("description");
		Part part=request.getPart("photo");
		String photoName=part.getSubmittedFileName();
		
		int price=Integer.parseInt(request.getParameter("price"));
		
		String path=getServletContext().getRealPath("")+"cImg"+File.separator+photoName;
		
		product product=new product(id, name, description, photoName, price);
		
		productDao pdao=new productDao(connectionProvider.getConnection());
		
		boolean f=pdao.editProduct(product,id);
		
		if(f) {
			File file=new File(path);
			part.write(path);
			
			out.write("done");
//			Message m=new Message("Product Updated Successfully", "success", "alert-success");
//			HttpSession session=request.getSession();
//			session.setAttribute("msg", m);
//			
//			response.sendRedirect("viewAndEdit.jsp");
		}else {
			out.write("failed");
//			Message m=new Message("Something Went Wrong", "error", "alert-danger");
//			HttpSession session=request.getSession();
//			session.setAttribute("msg", m);
//			
//			response.sendRedirect("viewAndEdit.jsp");
		}
		
	}

}
