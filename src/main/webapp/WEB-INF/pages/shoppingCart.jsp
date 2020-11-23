<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Shopping Cart</title>

<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
<style>
#btnCustom:hover{
	background: #18cfff; 
	cursor: pointer;
	color: #fff;
}
#linkp {
	flex-direction: column;
	text-decoration: none;
	color: black;
	font-weight: bold;
}

.total {
	color: red;
	font-size: 25px;
	margin-left: auto;
	margin-right: 82px;
}

body {
	background-color: #8080801a;
	font-family: 'open sans';
	overflow-x: hidden;
}

#btnCustom {
	width: 30px;
	text-decoration: none;
	background: #ccc;
	padding: 3px 10px;
	border: none;
	font-weight: bold;
	color: #fff;
}
</style>
<script type="text/javascript">
	function Delete(productId) { 
		window.location.href = "${pageContext.request.contextPath}/shoppingCartRemoveProduct?id=" + productId;
	}
	function desItem(productId) {
		window.location.href = "${pageContext.request.contextPath}/desItem?id=" + productId;
	}
	function incItem(productId) {
		window.location.href = "${pageContext.request.contextPath}/incItem?id=" + productId;
	}
	function update() {
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="_header.jsp" />

	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />


	<c:if test="${empty detailList}">
		<h2>Hiện tại chưa có món hàng nào trong giỏ hàng</h2>
		<a href="${pageContext.request.contextPath}/">Mua sắm ngay</a>
	</c:if>

	<c:if test="${not empty detailList}">
		<form:form method="POST" modelAttribute="detailList" id="cartForm"
			style="padding:50px;"
			action="${pageContext.request.contextPath}/cart">
			<div style="display: flex;">
				<div style="width: 64%; background-color: white;">
					<c:forEach items="${detailList}" var="item" varStatus="varStatus">
						<!-- <div class="product-preview-container"> -->
						<div class="product-preview-shopping-cart-container">
							<%-- <a id="linkp"
						href="${pageContext.request.contextPath}/product?id=${item.productId}"> --%>

							<div style="display: flex; width: 65%;">
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
										${item.productName} </a>
										<input style="width: 70px; height: 40px;" class="btnDelete" type="button" value="Xóa" onclick="Delete('${item.id}')">
								</div>
							</div>
							<div style="margin-left: auto; margin-right: 5%; display: flex;">
								<fmt:parseNumber var="price" integerOnly="true" type="number"
									value="${item.price}" />
								<div style="font-weight: bold; margin-right: 50px;">${price}đ</div>
								<div>
									<div>
										<input id="btnCustom" type="button" value="-" onclick="desItem('${item.id}')">
										<input type="text" name="quantityItem" id="quantityItem"
											value="${item.quantity}" onblur="update()" /> 
										<input id="btnCustom" type="button" value="+" onclick="incItem('${item.id}')">
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div style="width: 2%"></div>
				<div style="width: 34%; background-color: white;">
					<div style="height: 200px;">
						<div style="display: flex; padding: 10px;">
							<div style="margin-left: 10px;">Thông tin nhận hàng</div>
							<div style="margin-left: auto; margin-right: 10px;">
								<a style="text-decoration: none;"
									href="${pageContext.request.contextPath}/shipping">Thay đổi</a>
							</div>
						</div>
						<div style="margin: 50px;">
							<div style="text-align: center; font-weight: bold;">${receiverInfo.receiverName}
								| ${receiverInfo.receiverPhone}</div>
							<div style="margin-top: 10px;">${receiverInfo.receiverAddress}</div>
						</div>
					</div>
					<div
						style="background-color: #f2f2f2; height: 15px; width: initial;"></div>
					<div style="display: flex; margin-top: 50px;">
						<div style="margin-left: 100px">Thành tiền</div>
						<div class="total">${total}đ</div>
					</div>
					<div style="margin-top: 120px; margin-bottom: 50px;">
						<a class="btn-confirm-order"
							href="${pageContext.request.contextPath}/checkout">Tiến hành
							đặt hàng</a>
					</div>
				</div>
			</div>
			<div style="clear: both"></div>
		</form:form>


	</c:if>


	<jsp:include page="_footer.jsp" />

</body>
</html>