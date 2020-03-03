<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.domain.Menu" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Online Meal Ordering System</title>
<!-- Bootstrap core CSS -->
<link href="pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="pages/css/heroic-features.css" rel="stylesheet">
</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark  fixed-top "
		style="background-color: #800080;">
		<div class="container">
			<a class="navbar-brand" href="pages/index.html">Online Meal
				Ordering System</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="customer_viewResturant.jsp">Home <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="index.html">Log out <span class="sr-only">(current)</span>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<header class="jumbotron my-4">

		<%
			session.getAttribute("customer");
		%>

		<h1 class="display-3">
			<p>My Order History</p>

		</h1>
		<p>welcome: ${customer.name}</p>
		<%-- ${customer.getMenu()} --%>




	</header>
	<div class="row text-center">
	
		<c:forEach items="${customer.getAllHistoryOrder()}" var="dataItem">
		
		
			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card">
					<!-- define file path and display corresponding pictures -->


					<!-- display all dishes name, price and description -->
					
					<div class="card-body">
						<p class="card-title">resturant name:${Menu.getResturantName(dataItem.getMenuId())}</p>
					</div>
					
					
					<c:forEach items="${dataItem.getAllDish()}" var="data">
					<div class="card-body">
						<p class="card-title">dishes:${data.getDishName()} </p>
						
					</div>
					</c:forEach>
					
					
					<div class="card-body">
						<p class="card-title">total price:${dataItem.getTotalPrice()} </p>
					</div>
					<div class="card-body">
						<p class="card-title">address:${dataItem.getAddress()} </p>
					</div>
					<div class="card-footer">
						<!-- view menu -->
						<form action="CancelOrderService" method="post">
							<input name="orderId" type="hidden"
								value="${dataItem.getOrderId()}"> 
							<button type="submit" class="btn btn-primary" align="right"
								onclick="return confirm('Are you sure you want to cancel this order?');">
								Cancel Order</button>
						</form>

						</tr>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	
	<!-- Footer -->
	<footer class="py-5 " style="background-color: #800080;">
		<div class="container">
			<p class="m-0 text-center text-white">SWEN90007 Software Design
				and Architecture</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script>
		function delDish(dishID) {
			var isDel = confirm("Are you sure to cancel this Order?")
			if (isDel) {
				locaion.href = "${pageContext.request.contextPath}/CancelOrderService?orderId="
						+ orderId;
			}
		}
	</script>


</body>

</html>
