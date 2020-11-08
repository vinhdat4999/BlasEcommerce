<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
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

	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage}</div>
	</c:if>

	<form:form method="POST">
		<h3>Mã xác thực đã được gửi đến email ${email}. Mã xác thực sẽ hết
			hạn trong 20 phút</h3>
		<table style="text-align: left;">
			<tr>
				<td><input type="text" name="code"></td>
				<td><input type="password" name="password"></td>
				<td><input type="password" name="retype"></td>
			</tr>
			<tr>
				<td><input type="submit" value="OK" /></td>
			</tr>
		</table>
	</form:form>
	<jsp:include page="_footer.jsp" />

</body>
</html>