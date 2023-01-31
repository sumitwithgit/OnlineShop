<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sorry! Something went wrong....</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link href="css/mystyle.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container text-center">
		<img alt="Error" src="img/error.png" class="img-fluid">
		<p>Error : <%=exception %></p>
		<h1>Sorry! Something went Wrong...</h1>
		<a href="index.jsp" class="btn btn-outline-primary">Go To Home Page</a>
	</div>
</body>
</html>