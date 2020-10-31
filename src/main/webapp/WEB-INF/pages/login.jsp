<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Login</title>
<style>
body {
	font-family: 'open sans';
	overflow-x: hidden;
}

.button {
	text-decoration: none;
	background: #189fff;
	padding: 0.4em 0.4em;
	border: none;
	color: #fff;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
}

.add-to-cart:hover, .like:hover {
	background: #18cfff;
	color: #fff;
}

.add-to-cart, .like {
	text-decoration: none;
	background: #189fff;
	padding: 1.2em 1.5em;
	border: none;
	text-transform: UPPERCASE;
	font-weight: bold;
	color: #fff;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
}

.add-to-cart:hover, .like:hover {
	background: #18cfff;
	color: #fff;
}
</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	<div class="login-container">

		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<div style="padding: 100px;">
			<form method="POST"
				action="${pageContext.request.contextPath}/j_spring_security_check">
				<table style="margin-left: auto; margin-right: 34%;">
					<tr>
						<td>User Name</td>
						<td><input name="username" /></td>
						<td rowspan="2"><input class="add-to-cart btn btn-default"
							type="submit" value="Login" /></td>
					</tr>

					<tr>
						<td>Password</td>
						<td><input type="password" name="password" /></td>
					</tr>
				</table>
			</form>
			<div style="padding-top: 50px; text-align: center;">
				<a class="button" href="${pageContext.request.contextPath}/signup">Đăng
					ký tài khoản</a> <a class="button"
					href="${pageContext.request.contextPath}/resetPassword">Quên
					mật khẩu?</a>
			</div>
		</div>

		<span class="error-message">${error }</span>

	</div>


	<jsp:include page="_footer.jsp" />

</body>
</html>