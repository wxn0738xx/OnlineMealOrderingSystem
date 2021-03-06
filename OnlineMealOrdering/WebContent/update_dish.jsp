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
			<a class="navbar-brand" href="../index.html">Online Meal Ordering
				System</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#"
						onclick="javascript:history.back(-1);">Home <span
							class="sr-only">(current)</span>
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
		<section class="py-5">
			<div class="container">
				<h1>Update Dish</h1>
			</div>
		</section>
		
		<!-- Page Features -->
		<form id="UpdateDish-form" role="form" action="UpdateDishService"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label>Dish Name</label> <input type="text" class="form-control"
					id="dishName" name="dishName" value="${dishName} " required>

				<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
			</div>
			<div class="form-group">
				<label>Dish Price</label> <input type="text" class="form-control"
					id="dishPrice" name="dishPrice" value="${price}" required>
				<small id="dishPriceHelp" class="form-text text-muted">Ensure
					enter valid number.</small>
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">Description</label>
				<textarea class="form-control" name="description" id="description"
					rows="3" placeholder="description">${description}</textarea>
			</div>
			<input id="dishId" type="hidden" name="dishId" value="${dishId}">
			<input id="filename" type="hidden" name="filename"
				value="${filename}">
			<div>
				<label>Upload Image：</label> <input type="file" name="file"
					id="file"><br>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<br /> <br />
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 " style="background-color: #800080;">
		<div class="container">
			<p class="m-0 text-center text-white">SWEN90007 Software Design
				and Architecture</p>
		</div>
	</footer>
	
	<!-- Bootstrap core JavaScript -->
	<script src="pages/vendor/jquery/jquery.min.js"></script>
	<script src="pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
