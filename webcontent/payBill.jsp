<%@page import="com.onlineshop.entities.cart"%>
<%@page import="com.onlineshop.dao.cartDao"%>
<%@page import="com.onlineshop.db.connectionProvider"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.onlineshop.dao.categoryDao"%>
<%@page import="com.onlineshop.entities.category"%>
<%@page import="com.onlineshop.Helper.Message"%>
<%@page import="com.onlineshop.entities.user"%>
<%@page errorPage="Error_page.jsp"%>

 <%
	user user=(user)session.getAttribute("user");
 	
	if(user!=null)
	{
		if(user.getUserType().equals("admin"))
		{
			Message m=new Message("This Page for Users. Please User login","error","alert-danger");
			session.setAttribute("msg1", m);
			response.sendRedirect("login.jsp");
		}
	} 
	else{
		Message m=new Message("You need to Login First...Please Login.","error","alert-danger");
		session.setAttribute("msg1", m);
		response.sendRedirect("login.jsp");
	} 
	
	
	
%>
 
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to PayBill Page Of OnlineShopping</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"/>
<style type="text/css">
		.primary-background{
			background-color: #0905ff73 !important;
		}
		body{
			background-color: #cfcfcf73;
		}
	</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
  <a class="navbar-brand" href="myCart.jsp"><i class="fa-solid fa-arrow-circle-left"></i> Back </a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      		
    </ul>
    	
    	
    
    	<button type="button" class="btn btn-outline-light ml-2" data-toggle="modal" data-target="#userProfile"><%=user.getName() %></button>
      	<a href="logoutServlet" class="btn btn-outline-light ml-2">Logout</a>
	
   		
			<!-- Modal -->
			<div class="modal fade" id="userProfile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header primary-background">
			        <h5 class="modal-title" id="exampleModalLabel">Welcome, <%=user.getName() %></h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <div class="container text-center">
			        	<img alt="profilePic" src="img/<%=user.getProfile() %>" width="150px" height="150px">
			        	<h4><%=user.getName() %></h4>
			        </div>
			       	<div class="container userProfile">
			       		<table class="table text-center">
			       				<tr>
			       					<th>ID</th>
			       					<td><%=user.getId() %></td>
			       				</tr>
			       				<tr>
			       					<th>Name</th>
			       					<td><%=user.getName() %></td>
			       				</tr>
			       				<tr>
			       					<th>Email</th>
			       					<td><%=user.getEmail() %></td>
			       				</tr>
			       				<tr>
			       					<th>Password</th>
			       					<td><%=user.getPassword() %></td>
			       				</tr>
			       				<tr>
			       					<th>Mobile</th>
			       					<td><%=user.getMobile() %></td>
			       				</tr>
			       		</table>
			       	</div>
			       	
			       	<div class="container editProfile" style="display: none;">
							<h5>You Can Edit Now...</h5>
			       			<form action="editServlet" method="post" enctype="multipart/form-data">
			       			<table class="table">
			       				<tr>
			       					<td><input type="hidden" name="type" value="<%=user.getUserType() %>" /></td>
			       				</tr>
			       				<tr>
			       					<th>ID</th>
			       					<td><input type="text" value="<%=user.getId() %>" name="id" readonly="readonly"/></td>
			       				</tr>
			       				<tr>
			       					<th>Name</th>
			       					<td><input type="text" value="<%=user.getName() %>" name="name" class="form-control"/></td>
			       				</tr>
			       				<tr>
			       					<th>Email</th>
			       					<td><%=user.getEmail() %></td>
			       				</tr>
			       				<tr>
			       					<th>Password</th>
			       					<td><input type="password" value="<%=user.getPassword() %>" name="password" class="form-control"/></td>
			       				</tr>
			       				<tr>
			       					<th>Mobile</th>
			       					<td><input type="number" value="<%=user.getMobile() %>" name="mobile" class="form-contorl"/></td>
			       				</tr>
			       				<tr>
			       					<th>Select Pic</th>
			       					<td><input type="file" name="newPic" value="<%=user.getProfile() %>" class="form-contorl"/></td>
			       				</tr>
			       				<tr>
			       					<th>Enter Address</th>
			       					<td><textarea rows="3" cols="40" name="address"><%=user.getAddress() %></textarea></td>
			       				</tr>
			       				<tr>
			       					<th>Enter City</th>
			       					<td><input type="text" name="city" class="form-control" value="<%=user.getCity()%>"/></td>
			       				</tr>
			       				<tr>
			       					<th>Enter State</th>
			       					<td><input type="text" name="state" class="form-control" value="<%=user.getState()%>"/></td>
			       				</tr>
			       				<tr>
			       					<th>Enter Country</th>
			       					<td><input type="text" name="country" class="form-control" value="<%=user.getCountry()%>"/></td>
			       				</tr>
			       				<tr>
			       					<th>Enter Pincode</th>
			       					<td><input type="number" name="pincode" class="form-control" value="<%=user.getPincode()%>"/></td>
			       				</tr>
			       			</table>
			       			<div class="container text-center">
			       				<button class="btn btn-primary">Save Changes</button>
			       			</div>
			       		</form>
			       	</div>
			    </div>
			      
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-primary" id="editbtn">Edit</button>
			      </div>
			  </div>
			</div>
       	</div>	
   		
  </div>
</nav>
   
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
		
		
		<div class="container mt-3">
		<div class="row">
				<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th>S No.</th>
							<th>Product Id</th>
							<th>Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Total Price</th>
						</tr>
					</thead>
					<tbody>
							<%
								cartDao cdao1=new cartDao(connectionProvider.getConnection());
								ArrayList<cart> clist=cdao1.getAllProductByUserIdFromCart(user.getId());
								int SNo=0;
								for(cart c:clist)
								{
									%>
										<tr>
											<td><%=SNo=SNo+1 %></td>
											<td><%=c.getProductId() %></td>
											<td><%=c.getProductName() %></td>
											<td><%=c.getProductQuantity() %> </td>
											<td><%=c.getProductPrice() %></td>
											<td><%=c.getTotalPrice() %></td>
										</tr>
									<%
								}
							%>
					</tbody>
				</table>
						
			</div>
						<%
				    		cartDao cdao=new cartDao(connectionProvider.getConnection());
				    		int totalAmount=cdao.totalPaybleAmount(user.getId());
				    		if(totalAmount>0)
				    		{
				    			%>
							    	<div class="text-right"><b>Total Amount : <i class="fa fa-inr"></i> <%=totalAmount %></b></div>
				    			<%
				    		}
				    	%>
					
		</div>
		
		
		
	<form action="GenerateBill?userId=<%=user.getId() %>&orderStatus=processing" method="post" id="generateBill">
		<div class="container mt-2">
			<h3>Please Enter Your Details</h3>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
					    <label for="address">Enter address</label>
					    <input type="text" required="required" value="<%=user.getAddress() %>" name="address" class="form-control" id="address" aria-describedby="emailHelp" placeholder="Enter Address">
					 </div>
					 <div class="form-group">
					    <label for="state">Enter State</label>
					    <input type="text" required="required" class="form-control" value="<%=user.getState() %>" name="state" id="state" aria-describedby="emailHelp" placeholder="Enter State">
					 </div>
					 <div class="form-group">
					    <label for="mobile">Enter Mobile</label>
					    <input type="number" required="required" class="form-control" value="<%=user.getMobile() %>" name="mobile" id="mobile" aria-describedby="emailHelp" placeholder="Enter Mobile">
					 </div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
					    <label for="city">Enter City</label>
					    <input type="text" required="required" class="form-control" value="<%=user.getCity() %>" name="city" id="city" aria-describedby="emailHelp" placeholder="Enter City">
					 </div>
					 <div class="form-group">
					    <label for="country">Enter Country</label>
					    <input type="text" required="required" class="form-control" value="<%=user.getCountry() %>" name="country" id="country" aria-describedby="emailHelp" placeholder="Enter country">
					 </div>
					 <div class="form-group">
					    <label for="pincode">Enter PinCode</label>
					    <input type="number" required="required" class="form-control" id="pincode" value="<%=user.getPincode() %>" name="pincode" aria-describedby="emailHelp" placeholder="Enter PinCode">
					 </div>
				</div>
			</div>
			<br>
			<hr>
			<br>
			<h3>Please Enter Payment Details</h3>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<select required="required" name="paymentOption" class="form-control">
							<option selected="selected" disabled="disabled">--Select Payment Option--</option>
							<option value="Cod">Cash On Delivery</option>
							<option value="OnlinePayment">Online Payment</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<input required="required" placeholder="Enter Transaction ID" name="tid" type="text" class="form-control"/>
					</div>
				</div>
			</div>
			<br>
			<hr>
			<br>
			<div class="form-group text-center">
				<button type="submit" class="btn btn-primary">Pay Now and Continue Shopping</button>
			</div>
		</div>
	</form>
		<br><Br>
		
		
		
		
		
		
		
		
		
		
		
		
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		let status=false;
		
			$('#editbtn').click(function(){
				if(status==false){
					$('.userProfile').hide();
					$('.editProfile').show();
					status=true;
					$(this).text("Back");
				}else{
					$('.userProfile').show();
					$('.editProfile').hide();
					status=false;
					$(this).text("Edit");
				}
			})
	});
	
	</script>	
	
	<!-- <script type="text/javascript">
		$(document).ready(function(){
			$('#generateBill').on('submit',function(event){
				event.preventDefault();
				let form=new FormData(this);
				$.ajax({
					url:'',
					method:'',
					data:form,
					success:function(data){
						console.log(data);
					},
					error:function(){
						console.log(data);
					},
					processData:false,
					contentType:false
				});
			})
		});
	</script> -->
</body>
</html>