<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
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
					<li class="nav-item active"><a class="nav-link"
						href="index.html">Main Page <span class="sr-only">(current)</span>
					</a></li>

				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Features -->
	<form id="AddMenu-form" role="form" action="RegisterService"
		method="post">

		<fieldset>
			<div class="form-group">
				<label>Username</label> <input type="text" class="form-control"
					id="username" name="username" placeholder="Enter Username" required>
			</div>

			<div class="form-group">
				<label>Phone Number</label> <input type="text" class="form-control"
					id="phonenumber" name="phonenumber"
					placeholder="Enter Phone Number" required>
			</div>

			<div class="form-group">
				<label>Name</label> <input type="text" class="form-control"
					id="name" name="name"
					placeholder="Enter Name" required>
			</div>

			<div class="form-group">
				<label>Password</label> <input type="password" class="form-control"
					id="password" name="password" placeholder="Enter Password" required>
			</div>

			<div class="form-group">
				<label>Confirm Password</label> <input type="password"
					class="form-control" id="confirm_password" name="confirm_password"
					placeholder="Confirm Password" required>
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</fieldset>

	</form>



	<!-- Footer -->
	<footer class="py-5 " style="background-color: #800080;">
		<div class="container">
			<p class="m-0 text-center text-white">SWEN90007 Software Design
				and Architecture</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="pages/vendor/jquery/jquery.min.js"></script>
	<script src="pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
<script>
	var password = document.getElementById("password"), confirm_password = document
			.getElementById("confirm_password");

	function validatePassword() {
		if (password.value != confirm_password.value) {
			confirm_password.setCustomValidity("Passwords Don't Match");
		} else {
			confirm_password.setCustomValidity('');
		}
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
</script>
</html>