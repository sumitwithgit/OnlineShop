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
import com.onlineshop.dao.userDao;
import com.onlineshop.db.connectionProvider;
import com.onlineshop.entities.user;

/**
 * Servlet implementation class editPersonalDetails
 */
@WebServlet("/editPersonalDetails")
@MultipartConfig
public class editPersonalDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPersonalDetails() {
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
		int mobile=Integer.parseInt(request.getParameter("mobile"));
		Part part=request.getPart("file");
		String picName=part.getSubmittedFileName();
		
		String path=getServletContext().getRealPath("")+"img"+File.separator+picName;
		
		user user=new user(id, name, mobile, picName);
		
		
		userDao udao=new userDao(connectionProvider.getConnection());
		
		boolean f=udao.updatePersonalDetails(user);
		
		if(f) {
			File file=new File(path);
			
			part.write(path);
			
			out.write("done");
			Message m=new Message("Personal Details Updated Successfully.You need to Login Again.", "success", "alert-success");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("editDetails.jsp");
			
		}else {
			out.write("done");
			Message m=new Message("Something Went Wrong...!!!Try Again", "error", "alert-danger");
			HttpSession session=request.getSession();
			session.setAttribute("msg", m);
			response.sendRedirect("editDetails.jsp");
		}
	}

}
