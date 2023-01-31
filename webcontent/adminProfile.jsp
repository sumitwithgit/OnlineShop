<%@page import="com.onlineshop.entities.category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page import="com.onlineshop.dao.categoryDao"%>
<%@page import="com.onlineshop.Helper.Message"%>
<%@page import="com.onlineshop.entities.user"%>
<%@page errorPage="Error_page.jsp"%>
<%
user user = (user) session.getAttribute("user");
if (user != null) {
	if (user.getUserType().equals("normal")) {
		Message m = new Message("This Page Only for Admin.Please Admin login", "error", "alert-danger");
		session.setAttribute("msg2", m);
		response.sendRedirect("login.jsp");
	}
} else {
	Message m = new Message("You need to Login First...Please Login.", "error", "alert-danger");
	session.setAttribute("msg2", m);
	response.sendRedirect("login.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<style type="text/css">
.primary-background {
	background-color: #0905ff73 !important;
}
	body{
		background-color: #cfcfcf73;
		}
</style>
</head>
<body>
	<jsp:include page="homeNavbar.jsp"></jsp:include>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="btn" href="#" data-toggle="modal" data-target="#addNewProduct"><span class="fa fa-plus"></span> Add New Product</a>
  <a class="btn" href="viewAndEdit.jsp"><span class="fa fa-eye"></span> View And Edit All Products</a>
  <a class="btn" href="orderReceived.jsp"><i class="fa fa-circle-down"></i> Orders Received</a>
  <a class="btn" href="cancelOrder.jsp"><i class="fa-solid fa-circle-xmark"></i> Cancel Orders</a>
  <a class="btn" href="deliverOrder.jsp"><i class="fa-solid fa-truck"></i> Delivered Orders</a>
</nav>
	<%
	Message m = (Message) session.getAttribute("msg");
	if (m != null) {
	%>
	<div class="alert <%=m.getCssClass()%>" role="<%=m.getType()%>">
		<%=m.getContent()%>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	session.removeAttribute("msg");
	}
	%>

	
	<!-- add new product modal -->
	
		<!-- Modal -->
		<div class="modal fade" id="addNewProduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		  <form action="addNewProductServlet" method="post" enctype="multipart/form-data">
		    <div class="modal-content">
		      <div class="modal-header primary-background text-white">
		        <h5 class="modal-title" id="exampleModalLabel">Add New Product</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		       			<div class="form-group">
		       				<select class="form-control" name="pCategory">
		       					<option>---Select Category---</option>
		       					<%
		       						categoryDao cdao=new categoryDao(connectionProvider.getConnection());
		       						ArrayList<category> clist=cdao.getAllCategory();
		       						for(category c:clist)
		       						{
		       							%>
		       								<option value="<%=c.getcName()%>"><%=c.getcName() %></option>
		       							<%
		       						}
		       					%>
		       				</select>
		       			</div>
		       			<div class="form-group">
		       				<input type="text" name="pName" class="form-control" placeholder="Enter Product Name">
		       			</div>
		       			<div class="form-group">
		       				<textarea rows="3" cols="60" class="form-control" name="pDescription" placeholder="Enter Product Description"></textarea>
		       			</div>
		       			<div class="form-group">
		       				<input type="number" name="pPrice" class="form-control" placeholder="Enter Price">
		       			</div>
		       			<div class="form-group">
		       				<input type="file" name="pPhoto" class="form-control">
		       			</div>
		       			
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		        <button type="submit" class="btn btn-primary">Save</button>
		      </div>
		    </div>
		  </form>
		  </div>
		</div>
			
	
	<!-- end of add new product modal -->
	
	
	





	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>

</body>
</html>






		       			
