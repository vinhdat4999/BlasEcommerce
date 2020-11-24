<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/productStyle.css">

<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div style="padding-bottom: 30px; padding-top: 20px; font-size: 22px;">Tổng
		số đơn hàng: ${paginationResult.totalRecords}</div>

	<div>
		<div
			style="padding-left: 40px; padding-right: 40px; padding-bottom: 10px; display: flex; font-weight: bold">
			<div style="width: 29%;">Mã đơn hàng</div>
			<div style="width: 8%;">Thời gian đặt</div>
			<div style="width: 26%;">Sản phẩm</div>
			<div style="margin-left: 31px; width: 22%;">Tổng tiền</div>
			<div style="width: 6%;">Trạng thái</div>
		</div>
		<div style="padding: 40px; padding-top: inherit;">
			<c:forEach items="${paginationResult.list}" var="orderInfo">
				<div class="product-preview-shopping-cart-container"
					style="display: flex;">
					<div style="width: 29%;">
						<a style="text-decoration: none;"
							href="${pageContext.request.contextPath}/order?id=${orderInfo.id}">${orderInfo.id}</a>
					</div>
					<div style="width: 17%;">${orderInfo.orderTime}</div>
					<div style="width: 38%;">${orderInfo.description}</div>
					<fmt:parseNumber var="intValue" integerOnly="true" type="number"
						value="${orderInfo.total}" />
					<div style="width: 12%;">
						<c:out value="${intValue}" />
						đ
					</div>
					<div style="width: 15%;">${orderInfo.status}</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<c:if test="${paginationResult.totalPages > 1}">
		<div class="pagination">
			<div style="display: flex;">
				<c:forEach items="${paginationResult.navigationPages}" var="page">
					<c:if test="${page != -1 }">
						<c:if test="${page != pageNow }">
							<a href="orderList?page=${page}" class="nav-item">${page}</a>
						</c:if>
						<c:if test="${page == pageNow }">
							<a class="active" href="orderList?page=${page}" class="nav-item">${page}</a>
						</c:if>
					</c:if>
					<c:if test="${page == -1 }">
						<!-- <span class="nav-item"> ... </span> -->
						<div class="nav-item" style="margin: 5px;">...</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</c:if>
	<jsp:include page="_footer.jsp" />

</body>
</html>