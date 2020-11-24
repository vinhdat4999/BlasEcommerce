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
<script type="text/javascript">
	function placeAgain(orderId) {
		window.location.href = "${pageContext.request.contextPath}/place-again?id=" + orderId;
	}
</script>
</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="customer-info-container">
		<h3 style="padding: 50px; padding-bottom: 0px;">Thông tin giao
			hàng</h3>
		<div>
			<div style="text-align: center; font-weight: bold;">${receiverInfo.receiverName}
				| ${receiverInfo.receiverPhone}</div>
			<div style="margin-top: 10px; text-align: center;">${receiverInfo.receiverAddress}</div>
		</div>
	</div>
	<div style="margin-bottom: 20px;">
		<input class="add-to-cart btn btn-default" type="button" value="Đặt lại đơn hàng này" onclick="placeAgain('${orderInfo.id}')">
	</div>
	<div style="width: 64%; background-color: white; margin-left: 270px;">
		<c:forEach items="${detailList}" var="item" varStatus="varStatus">
			<!-- <div class="product-preview-container"> -->
			<div class="product-preview-shopping-cart-container">
				<%-- <a id="linkp"
						href="${pageContext.request.contextPath}/product?id=${item.productId}"> --%>

				<div style="display: flex; width: 70%;">
					<div>
						<a id="linkp"
							href="${pageContext.request.contextPath}/product?id=${item.productId}">
							<img
							src="${pageContext.request.contextPath}/productImage?id=${item.productId}" />
						</a>
					</div>
					<div style="display: inline-block;"></div>
					<div style="margin-left: 30px; display: grid;">
						<a id="linkp"
							href="${pageContext.request.contextPath}/product?id=${item.productId}">
							${item.name} </a>
					</div>
				</div>
				<div style="margin-left: auto; display: flex;">
					<fmt:parseNumber var="price" integerOnly="true" type="number"
						value="${item.price}" />
					<div style="margin-right: 25px;">${price}đ</div>
					<div>x ${item.quantity}</div>
					<div style="margin-left: 60px; font-weight: bold;">
						<fmt:parseNumber var="totalPerItem" integerOnly="true"
							type="number" value="${item.price * item.quantity}" />
						${totalPerItem} đ
					</div>
				</div>
			</div>
		</c:forEach>
		<div style="display: flex; margin-top: 50px;">
			<div style="margin-left: 450px">Tổng cộng</div>
			<div style="margin-left: 50px;" class="total">${total}đ</div>
		</div>
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