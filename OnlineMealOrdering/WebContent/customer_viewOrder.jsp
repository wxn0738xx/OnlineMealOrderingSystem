<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="java.util.* " %>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<link href="pages/css/viewOrder.css" rel="stylesheet">
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
					<li class="nav-item active"><a class="nav-link" href="customer_viewResturant.jsp">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="index.html">Log out <span class="sr-only">(current)</span>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="shopping-cart">
		<h1>Shopping Cart</h1>
		<div class="column-labels">
			<label class="product-image">Image</label> <label
				class="product-details">Product</label> <label class="product-price">Price</label>
			<label class="product-quantity">Quantity</label> <label
				class="product-removal">Remove</label> <label
				class="product-line-price">Total</label>
		</div>

		<%
			session.getAttribute("customer");
		
		%>
		
		<%
			session.getAttribute("resturantName");
		
		%>
		
		
	<!-- 	<form> -->
<%
ArrayList<String> obj = new ArrayList<String>();

						%>

		<c:forEach items="${dishList}" var="dataItem"> 
<!-- form action="AddOrderService" method="get"> -->


		<div class="product">
			<div class="product-image">
			<%
							String path = request.getContextPath();
								String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
										+ path + "/";
						%>
				<img src="<%=basePath%>/Dishes_img/${dataItem.filePath}"  onerror="javascript:this.src='./pages/img/default.png'">
			</div>
			<div class="product-details">
				<div class="product-title">${dataItem.dishName}</div>
				<p class="product-description">${dataItem.description}</p>
			</div>
			<%-- <div class="product-price">${dataItem.price}</div> --%>
			<div class="product-quantity">
				<input hidden type="number" value="1" min="1" name="quantity">
				
				 <div class="product-line-price">${dataItem.price}</div>
			</div>
			<div class="product-removal">
			<!-- <button class="remove-product" >Remove</button> -->
			<input type="hidden" name="dishId" value="${dataItem.dishId}">
			
			
				<form action="CustomerRemoveDishService" method="get">
								<!-- use hidden dish id to pass value -->
								<input type="hidden" name="dishId" value="${dataItem.dishId}">
								<input type="hidden" name="price" value="${dataItem.price}">
								<input type="hidden" name="filePath" value="${dataItem.filePath}">
								<input type="hidden" name="dishName" value="${dataItem.dishName}">
								<input type="hidden" name="description" value="${dataItem.description}">
								<tr>
									<button type="submit" class="btn btn-primary" align="right"
										onclick="return confirm('Are you sure you want to Delete?');">
										Remove</button>
								</tr>
							</form>
				</div>
              

			</div>
		

	<!-- 	</form> -->
	</c:forEach>


	
			
			
		</div> 
	<form action="AddOrderService" method="get">
	<div class="form-group">
				<label>Address</label> <input type="text" class="form-control"
					id="address" name="address" placeholder="Enter Address"
					required="required">
			</div>
		<br>
		<button type="submit" class="checkout">Confirm Order</button>
		</br> 
	
		</form>
		
		<br>
		<button class="btn btn-primary" onclick="window.location.href=' customer_viewMenu.jsp '" >Continue
			Ordering</button>
		</br>


</body>
<br>
<!-- Footer -->
<footer class="py-5 " style="background-color: #800080;">
	<div class="container">
		<p class="m-0 text-center text-white">SWEN90007 Software Design
			and Architecture</p>
	</div>
	<!-- /.container -->
</footer>
<script>



function delDish(dishID) {
	var isDel = confirm("Are you sure to delete this dish?")
	if (isDel) {
		locaion.href = "${pageContext.request.contextPath}/AddOrderService?dishId="
				+ dishId;
	}
}

	$(document).ready(
			function() {

				/* Set rates + misc */
				var taxRate = 0.05;
				var shippingRate = 15.00;
				var fadeTime = 300;

				/* Assign actions */
				$('.product-quantity input').change(function() {
					updateQuantity(this);
				});

				$('.product-removal button').click(function() {
					removeItem(this);
				});

				/* Recalculate cart */
				function recalculateCart() {
					var subtotal = 0;

					/* Sum up row totals */
					$('.product').each(
							function() {
								subtotal += parseFloat($(this).children(
										'.product-line-price').text());
							});

					/* Calculate totals */
					var tax = subtotal * taxRate;
					var shipping = (subtotal > 0 ? shippingRate : 0);
					var total = subtotal + tax + shipping;
					

					/* Update totals display */
					$('.totals-value').fadeOut(fadeTime, function() {
						$('#cart-subtotal').html(subtotal.toFixed(2));
						$('#cart-tax').html(tax.toFixed(2));
						$('#cart-shipping').html(shipping.toFixed(2));
						$('#cart-total').html(total.toFixed(2));
						if (total == 0) {
							$('.checkout').fadeOut(fadeTime);
						} else {
							$('.checkout').fadeIn(fadeTime);
						}
						$('.totals-value').fadeIn(fadeTime);
					});
				}

				/* Update quantity */
				function updateQuantity(quantityInput) {
					/* Calculate line price */
					var productRow = $(quantityInput).parent().parent();
					var price = parseFloat(productRow
							.children('.product-price').text());
					var quantity = $(quantityInput).val();
					var linePrice = price * quantity;

					/* Update line price display and recalc cart totals */
					productRow.children('.product-line-price').each(function() {
						$(this).fadeOut(fadeTime, function() {
							$(this).text(linePrice.toFixed(2));
							recalculateCart();
							$(this).fadeIn(fadeTime);
						});
					});
				}

				/* Remove item from cart */
				function removeItem(removeButton) {
					/* Remove row from DOM and recalc cart total */
					var productRow = $(removeButton).parent().parent();
					productRow.slideUp(fadeTime, function() {
						productRow.remove();
						recalculateCart();
					});
				}

			});
</script>