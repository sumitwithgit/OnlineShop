<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.onlineshop.dao.categoryDao"%>
<%@page import="com.onlineshop.entities.category"%>
<%@page import="com.onlineshop.Helper.Message"%>
<%@page errorPage="Error_page.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
	<style type="text/css">
		.primary-background{
			background-color: #0905ff73;
		}
		body{
			background-color: #04040473;
		}
	</style>
</head>
<body>
<!-- navbar -->
	<jsp:include page="homeNavbar.jsp"></jsp:include>
<!-- navbar end -->



<%
	Message m=(Message)session.getAttribute("msg");
	if(m!=null)
	{
		%>
			<div class="alert <%=m.getCssClass() %>" role="<%=m.getType()%>">
			 <%=m.getContent() %>
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		<%
		session.removeAttribute("msg");
	}
	
%>


<br><Br>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col-md-6 offset-md-3">
					<div class="card">
					<form action="registerUser" method="post" enctype="multipart/form-data">
						<div class="card-header text-center primary-background text-white">
							<span class="fa fa-user-plus fa-3x"></span>
							<h3>Registration Form</h3>
						</div>
						<div class="card-body text-center">
								<div class="form-group">
									<input type="text" class="form-control" name="name" placeholder="Enter Name">
								</div>
								<div class="form-group">
									<input type="email" class="form-control" name="email" placeholder="Enter Email">
								</div>
								<div class="form-group">
									<input type="password" class="form-control" name="password" placeholder="Enter Password">
								</div>
								<div class="form-group">
									<input type="number" class="form-control" name="mobile" placeholder="Enter mobile">
								</div>
						</div>
						<div class="card-footer primary-background text-center">
							<button class="btn btn-outline-light" type="submit">Register</button>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>















	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
	
	</script>
</body>
</html>