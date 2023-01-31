<%@page import="java.util.ArrayList"%>
<%@page import="com.onlineshop.entities.product"%>
<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page import="com.onlineshop.dao.productDao"%>
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
<title>View And Edit Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<style type="text/css">
.primary-background {
	background-color: #0905ff73 !important;
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
		       					<option value="grocery">Grocery</option>
		       					<option value="mobile">Mobile</option>
		       					<option value="fashion">Fashion</option>
		       					<option value="electronics">Electronics</option>
		       					<option value="apploances">Appliances</option>
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
	
	
	
	<div class="container-fluid">
	<h5 class="text-center p-4">All Products </h5>
		<div class="row">
			<table class="table">
				<thead class="primary-background text-white">
					<tr>
						<th>Product Id</th>
						<th>Product Name</th>
						<th>Product Description</th>
						<th>Product Photo</th>
						<th>Product Price</th>
						<th>Product Category</th>
						<th>Operation</th>
					</tr>
				</thead>
				<tbody>
						<%
						productDao pdao=new productDao(connectionProvider.getConnection());
						ArrayList<product> plist=pdao.viewAllProduct();
						
						for(product p:plist)
						{
							%>
							<tr>
									<td><%=p.getpId() %></td>
									<td><%=p.getpName() %></td>
									<td><%=p.getpDescription() %></td>
									<td><img src="cImg/<%=p.getpPhoto() %>" alt="" width="100px" height="100px"/></td>
									<td><%=p.getpPrice() %></td>
									<td><%=p.getpCategory() %></td>
									<td>
										<a class="btn btn-danger" href="deleteProduct?id=<%=p.getpId()%>"><i class="fa fa-trash"></i></a>
										<a id="<%=p.getpId() %>" data-toggle="modal" data-target="#editProduct" class="btn btn-primary editProduct"><i class="fa fa-pencil"></i></a>
									</td>
							</tr>
								<%
							}
						%>
				</tbody>
			</table>
		</div>
	</div>
	
	
	
		<!-- modal for edit product -->
	
	
								<!-- Modal -->
								<div class="modal fade" id="editProduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								   <form action="editProductServlet" method="post" enctype="multipart/form-data" id="editProductForm">
								    <div class="modal-content">
								      <div class="modal-header primary-background text-white">
								        <h5 class="modal-title" id="exampleModalLabel">Edit Product</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								        	<div class="form-group">
								        		<input name="name" type="text" class="form-control" placeholder="Enter Name"/>
								        	</div>
								        	<div class="form-group">
								        		<textarea rows="5" cols="60" class="form-control" name="description" placeholder="Enter Description"></textarea>
								        	</div>
								        	<div class="form-group">
								        		<input name="photo" type="file" class="form-control"/>
								        	</div>
								        	<div class="form-group">
								        		<input name="price" type="number" class="form-control" placeholder="Enter Price"/>
								        	</div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								        <button class="btn btn-primary">Save changes</button>
								      </div>
								    </div>
								   </form>
								  </div>
								</div>
									
									
									<!-- end modal for edit product -->
	

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>



					