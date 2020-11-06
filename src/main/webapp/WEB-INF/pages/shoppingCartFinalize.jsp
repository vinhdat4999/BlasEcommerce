<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Shopping Cart Finalize</title>
<style>
body {
	font-family: 'open sans';
	overflow-x: hidden;
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

	<div class="container">
		<h2>BLAS cám ơn bạn đã đặt hàng</h2>
		<a style="text-decoration: none;" href="${pageContext.request.contextPath}/">Tiếp tục mua sắm</a>
	</div>

	<jsp:include page="_footer.jsp" />

</body>
</html>