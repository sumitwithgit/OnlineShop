<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page import="com.onlineshop.entities.category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.onlineshop.dao.categoryDao"%>
<%@page errorPage="Error_page.jsp"%>
<%@page import="com.onlineshop.Helper.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Shopping Shop</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
	<style type="text/css">
		.primary-background{
			background-color: #0905ff73 !important;
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

<!-- menubar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand active" href="#" onclick="getPost('AllCategory',this)">All Categories</a>
  <%
  	categoryDao cdao=new categoryDao(connectionProvider.getConnection());
	ArrayList<category> clist=cdao.getAllCategory();
		for(category c:clist)
		{
		   %>
		     <a class="navbar-brand c-link" onclick="getPost('<%=c.getcName() %>',this)" href="#"><%=c.getcName() %></a>
		   <%
		}
   %>
</nav>
<!-- menubar end -->	

<%
	
	Message m=(Message)session.getAttribute("msg");
	if(m!=null)
	{
		%>
			<div class="alert <%=m.getCssClass() %>" role="<%=m.getType()%>" >
			 <%=m.getContent() %>
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		<%
		session.removeAttribute("msg");
	}
	
%>



	<main>
		<div class="container ">
			<div class="row mt-4">
					<div class="container text-center" id="loader">
						<i class="fa fa-refresh fa-4x fa-spin"></i>
						<h3>Loading...</h3>
					</div>
					<div class="container-fluid" id="showPost"></div>
				</div>
			</div>
	</main>








	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
		function getPost(catName,temp){
			$(".c-link").removeClass('active');
			
			
			$.ajax({
				url:"posts.jsp",
				data:{cName:catName},
				success:function(data, textStatus, jqHXR){
					$("#loader").hide();
					$("#showPost").html(data);
					$(temp).addClass('active');
				}
			})
		}
	
		$(document).ready(function(){
			let allPostRef=$('.c-link')[0];
			getPost('AllCategory',allPostRef);
		});	
	</script>
</body>
</html>