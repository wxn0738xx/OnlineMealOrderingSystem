<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<button class="btn btn-primary" align="right"
								 onclick="display_alert()" value="Display alert box" >
								Chat with manager</button>
					<li class="nav-item active"><a class="nav-link" href="manager_menu.jsp">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="manager_viewOrder.jsp">View Order <span class="sr-only"></span>
					</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="index.html">Log out <span class="sr-only">(current)</span>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<!-- Jumbotron Header -->
		<header class="jumbotron my-4">
			<!-- display resturant name and manager name -->
			<%
				session.getAttribute("manager");
			%>
			<h1 class="display-3">${manager.getMenu().getResturantName()}</h1>
			<p>welcome: ${manager.name}</p>
			<table>
				<tr>
					<!-- create new dish button -->
					<td><a href="add_dish.html" class="btn btn-primary">Create
							New Dish</a></td>
					<td>
						<!-- delete menu button -->
						<form action="DeleteMenuService" method="post">
							<input name="menuId" type="hidden"
								value="${manager.getMenu().getMenuId()}">
							<button type="submit" class="btn btn-primary" align="right"
								onclick="return confirm('Are you sure you want to Delete?');">
								Delete Menu</button>
						</form>
					</td>
					<!-- save all changes -->
					<td>
						<button id="sender" name="sender" class="btn btn-primary"
							align="right" onclick="return confirm('All Changes Saved!');">
							Save</button>
					</td>
				</tr>
			</table>
		</header>

		<!-- Page Features -->
		<div class="row text-center">
			<!-- iterate to get all dishes -->
			<%
				session.getAttribute("manager");
			%>
			<c:forEach items="${manager.getMenu().getAllDishes()}" var="dataItem">
		
				<div class="col-lg-3 col-md-6 mb-4">
					<div class="card">
						<!-- define file path and display corresponding pictures -->
						<%
							String path = request.getContextPath();
								String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
										+ path + "/";
						%>
						<img class="card-img-top"
							src="<%=basePath%>/Dishes_img/${dataItem.filePath}" onerror="javascript:this.src='./pages/img/default.png'" alt="">
						<!-- display all dishes name, price and description -->
						<div class="card-body">
							<p class="card-title">Dish Name: ${dataItem.dishName}</p>
							<p class="card-text">Dish Price: ${dataItem.price}</p>
							<p class="card-text">Description: ${dataItem.description}</p>
						</div>
						<div class="card-footer">
							<!-- Update dish -->
							<form action="UpdateDishService" method="get">
								<!-- use hidden dish id to pass value -->

								<input type="hidden" name="dishName"
									value="${dataItem.dishName}"> <input type="hidden"
									name="dishId" value="${dataItem.dishId}"> <input
									type="hidden" name="price" value="${dataItem.price}"> <input
									type="hidden" name="description"
									value="${dataItem.description}"> <input type="hidden"
									name="filename" value="${dataItem.filePath}">
								<tr>
									<button type="submit" class="btn btn-primary" align="right">
										Update Dish</button>
								</tr>
							</form>
							<tr>
								<br />
							</tr>
							<!-- Delete dish -->
							<form action="DeleteDishService" method="post">
								<!-- use hidden dish id to pass value -->
								<input type="hidden" name="dishId" value="${dataItem.dishId}">
								<tr>
									<button type="submit" class="btn btn-primary" align="right"
										onclick="return confirm('Are you sure you want to Delete?');">
										Delete Dish</button>
								</tr>
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
	function display_alert()
	  {
	  alert("You don't have right to use this functionality!")
	  }
		function delDish(dishID) {
			var isDel = confirm("Are you sure to delete this dish?")
			if (isDel) {
				locaion.href = "${pageContext.request.contextPath}/DeleteDishService?dishId="
						+ dishId;
			}
		}
		// hide save button
		function ConfirmSubmit(sender) {
			/*  sender.disabled = true; */
			/* var displayValue = sender.style. */
			sender.style.display = 'none'

			if (confirm('Seguro que desea entregar los paquetes?')) {
				sender.disabled = false
				return true;
			}

			sender.disabled = false;
			/*         sender.style.display = displayValue;
			 */return false;
		}
		//show save button
		function ShowButton(sender) {
			sender.disabled = true;
			/* var displayValue = sender.style. */
			sender.style.display = 'none'

		}
	</script>

</body>

</html>
