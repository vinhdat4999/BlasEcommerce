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
#linkp {
	flex-direction: column;
	text-decoration: none;
	color: black;
	font-weight: bold;
}

body {
	background-color: #8080801a;
	font-family: 'open sans';
	overflow-x: hidden;
}
</style>
<script type="text/javascript">
	function desItem() {
		var quanityItem = document.getElementById("quanityItem").value;
		if (quanityItem > 1) {
			quanityItem--;
		}
		document.getElementById("quanityItem").value = quanityItem;
		return false;
	}
	function incItem() {
		var quanityItem = document.getElementById("quanityItem").value;
		quanityItem++;
		document.getElementById("quanityItem").value = quanityItem;
		return false;
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
		<form:form method="POST" modelAttribute="detailList"
			style="padding:50px;"
			action="${pageContext.request.contextPath}/cart">
			<div style="background-color: white;">
				<c:forEach items="${detailList}" var="item" varStatus="varStatus">
					<!-- <div class="product-preview-container"> -->
					<div class="product-preview-shopping-cart-container">
						<%-- <a id="linkp"
						href="${pageContext.request.contextPath}/product?id=${item.productId}"> --%>

						<div style="display: flex;">
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
									${item.productName} </a> <a class="btnDelete"
									href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?id=${item.id}">Delete
								</a>
							</div>
						</div>
						<div style="margin-left: auto; margin-right: 5%; display: flex;">
							<fmt:parseNumber var="price" integerOnly="true" type="number"
								value="${item.price}" />
							<div style="font-weight: bold; margin-right: 50px;">${price}đ</div>
							<div>
								<%-- Quantity<input id="quantity" name="quantity" type="text"
									value="${item.quanity}" /> --%>
								<div>
									<button name="btnDes" onclick="return desItem()">-</button>
									<input type="text" name="quantityItem" id="quantityItem"
										value="${item.quantity}" />
									<button name="btnInc" onclick="return incItem()">+</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div style="clear: both"></div>
			<input class="button-update-sc" type="submit" value="Update Quantity" />
			<a class="navi-item"
				href="${pageContext.request.contextPath}/shoppingCartCustomer">Enter
				Customer Info</a>
			<a class="navi-item" href="${pageContext.request.contextPath}/">Continue
				Buy</a>
		</form:form>


	</c:if>


	<jsp:include page="_footer.jsp" />

</body>
</html>