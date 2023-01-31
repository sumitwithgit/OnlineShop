<%@page import="com.onlineshop.dao.userDao"%>
<%@page import="com.onlineshop.entities.user"%>
<%@page import="com.onlineshop.entities.product"%>
<%@page import="java.util.List"%>
<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page errorPage="Error_page.jsp"%>
<%@page import="com.onlineshop.dao.productDao"%>
<div class="row">
	<%	
	user user=(user)session.getAttribute("user");
	productDao pdao = new productDao(connectionProvider.getConnection());
	String cName = request.getParameter("cName");

	List<product> product = null;
	if (cName.equals("AllCategory")) {
		product = pdao.viewAllProduct();
	}  else {
		product = pdao.getAllProductByCategory(cName);
	} 
	if(product.size()==0)
	{
		out.write("<h3 class='display-3 text-center'>No Product in this category.</h3>");
		return;
	}
	for (product p : product) {
	%>
	<div class="col-md-4 mt-2">
		<div class="card" >
			<img class="card-img-top d-flex" src="cImg/<%=p.getpPhoto()%>"
				alt="Card image cap" style="object-fit: cover; height: 300px;">
			<div class="card-body">
				<b class="card-title"><%=p.getpName()%></b>
				<p class="card-text"><%=p.getpDescription()%></p>
			</div>
			<div class="card-footer primary-background text-center">
					
				<%
					if(user==null)
					{
						%>
						<a href="login.jsp" class="btn btn-outline-light"><i class="fa fa-cart-plus"></i> Add To Cart</a>
						<%
					}else
					{	
						%>
						<a href="addCart?pid=<%=p.getpId() %>&uid=<%=user.getId() %>&price=<%=p.getpPrice() %>" class="btn btn-outline-light"><i class="fa fa-cart-plus"></i> Add To Cart</a>												
						<%
					}
				%>
				<a href="#" class="btn btn-outline-light"><i class="fa fa-inr"></i> <%=p.getpPrice() %></a>
					
			</div>
		</div>
	</div>
	<%
	}
	%>
</div>