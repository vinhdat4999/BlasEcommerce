<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Account Info</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
<style>
body {
	font-family: 'open sans';
	overflow-x: hidden;
}
</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
</head>
<body>


	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div class="account-container">


		<ul>
			<li>Tên: ${user.firstname} ${user.lastname}</li>
			<li>SĐT: ${user.phoneNum}
			<li>Email: ${user.email}</li>
		</ul>
	</div>
	<div style="font-size: 20px; color: red; padding-bottom: 20px;">Sổ
		địa chỉ</div>
	<div style="display: flex; padding-left: 220px;">
		<c:forEach items="${paginationReceiverInfos.list}" var="item">
			<div class="reveiver-info-account-info-container">
				<div>
					<div style="text-align: center; font-weight: bold; height: 30px;">${item.receiverName}
						| ${item.receiverPhone}</div>
					<div style="margin-top: 10px; height: 95px; text-align: center;">${item.receiverAddress}</div>
				</div>
			</div>
		</c:forEach>
	</div>


	<jsp:include page="_footer.jsp" />

</body>
</html>