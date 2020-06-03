 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="icon" type="${pageContext.request.contextPath}/static/image/png" href="LoginForm/images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/LoginForm/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/LoginForm/css/main.css">
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
				<h4 style="color:red;"><%= request.getAttribute("error") %></h4>
					<img src="${pageContext.request.contextPath}/static/LoginForm/images/img-01.png" alt="IMG">
				</div>

				<form class="login100-form validate-form" name='f' action="${pageContext.request.contextPath}/login" method='POST' >
					<span class="login100-form-title">
						Demand Planning Login
					</span>

					<div class="wrap-input100 validate-input" >
						<input class="input100" type="text" name="ssoId" placeholder="User Name">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Login
						</button>
					</div>

					<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
	
	

	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/LoginForm/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/LoginForm/vendor/select2/select2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/LoginForm/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
	<script src="LoginForm/js/main.js"></script>

</body>
</html>