<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
<style>
#linkp {
	flex-direction: column;
	text-decoration: none;
}

body {
	font-family: 'open sans';
	overflow-x: hidden;
}
</style>
</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">Order Info</div>

	<div class="customer-info-container">
		<h3>Customer Information:</h3>
		<ul>
			<%-- <li>Name: ${orderInfo.receiverName}</li>
			<li>Email: ${orderInfo.receiverEmail}</li>
			<li>Phone: ${orderInfo.receiverPhone}</li>
			<li>Address: ${orderInfo.receiverAddressId}</li> --%>
			<li>AddressInfoID: ${orderInfo.receiverInfoId}</li>
		</ul>
		<h3>Order Summary:</h3>
		<ul>
			<%--<li>Total:
           <span class="total">
           <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
           </span></li> --%>
		</ul>
	</div>

	<br />
	<div class="container">

		<c:forEach items="${detailList}" var="item">
			<div class="product-preview-container">
				<a id="linkp"
					href="${pageContext.request.contextPath}/product?id=${item.productId}">
					<ul>
						<fmt:parseNumber var="quantityValue" integerOnly="true"
							type="number" value="${item.quantity}" />
						<li><img class="product-image"
							<%-- src="${pageContext.request.contextPath}/productImageMain?productId=${item.productId}" /> --%>
							src="${pageContext.request.contextPath}/productImage?id=${item.productId}" />
						<%-- <li>${item.productName}</li> --%>
						<li>${item.price}X${quantityValue}=${item.price * quantityValue}đ</li>
					</ul>
				</a>
			</div>
		</c:forEach>

	</div>
	<c:if test="${paginationResult.totalPages > 1}">
		<div class="page-navigator">
			<c:forEach items="${paginationResult.navigationPages}" var="page">
				<c:if test="${page != -1 }">
					<a href="orderList?page=${page}" class="nav-item">${page}</a>
				</c:if>
				<c:if test="${page == -1 }">
					<span class="nav-item"> ... </span>
				</c:if>
			</c:forEach>

		</div>
	</c:if>

	<jsp:include page="_footer.jsp" />

</body>
</html>