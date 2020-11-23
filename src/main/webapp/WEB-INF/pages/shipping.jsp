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
<script type="text/javascript">
	function shippingTo(id) { 
		console.log("FF");
		window.location.href = "${pageContext.request.contextPath}/shipping-to?receiverInfo=" + id;
	}
</script>
<style>
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
	background-color: #f2f2f2;
	font-family: 'open sans';
	overflow-x: hidden;
}

#btnCustom {
	text-decoration: none;
	background: #ccc;
	padding: 3px 10px;
	border: none;
	font-weight: bold;
	color: #fff;
}
</style>
<script type="text/javascript">
	function addReceiverInfo() {
		console.log("clicked");
		this.classList.toggle("active");
		var content = this.nextElementSibling;
		if (content.style.display === "block") {
			content.style.display = "none";
		} else {
			content.style.display = "block";
		}
	}
</script>
</head>
<body>
	<jsp:include page="_header.jsp" />

	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />


	<form>
		<div style="padding: 30px;">
			<c:forEach items="${paginationReceiverInfos.list}" var="item">
				<div class="reveiver-info-container">
					<div>
						<div style="text-align: center; font-weight: bold; height: 30px;">${item.receiverName}
							| ${item.receiverPhone}</div>
						<div style="margin-top: 10px; height: 95px; text-align: center;">${item.receiverAddress}</div>
						<div style="text-align: center;">
							<input style="width: auto;" class="btn-confirm-order"
								type="button" value="Giao đến địa chỉ này"
								onclick="shippingTo('${item.id}')">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- <div style="display: flex;">
			<div>Bạn muốn giao hàng đến địa chỉ khác?</div>
			<div>
				<input type="button" class="button" value="Thêm địa chỉ" onclick="addReceiverInfo()">
				<div class="content">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
						Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat.</p>
				</div>
			</div>
		</div> -->
	</form>


	<jsp:include page="_footer.jsp" />

</body>
</html>