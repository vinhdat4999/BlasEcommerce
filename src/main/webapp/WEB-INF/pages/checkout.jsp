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
	text-decoration: none;
	background: #ccc;
	padding: 3px 10px;
	border: none;
	font-weight: bold;
	color: #fff;
}
</style>
<script type="text/javascript">
	function update() {
		console.log("ds");
		document.getElementById("cartForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="_header.jsp" />

	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />
	<form:form method="POST" modelAttribute="detailList" id="cartForm"
		style="padding:50px;" action="${pageContext.request.contextPath}/checkout">
		<div style="display: flex;">
			<div style="width: 64%; background-color: white;">
				<div style="font-weight: bold; text-align: left; padding: 50px; font-size: 20px;">
					Chọn hình thức thanh toán 	
				</div>
				<div style="text-align: left; padding: 60px;">
					<div>
						<input type="radio" name="paymentMethod" class="paymentMethod" checked="checked">Thanh toán bằng tiền mặt
					</div>
					<div>
						<input type="radio" name="paymentMethod" class="paymentMethod" disabled="disabled">Thanh toán bằng thẻ quốc tế Visa, Master, JCB
					</div>
					<div>
						<input type="radio" name="paymentMethod" class="paymentMethod" disabled="disabled">Thẻ ATM nội địa/Internet Banking (Miễn phí thanh toán)
					</div>
				</div>
			</div>
			<div style="width: 2%"></div>
			<div style="width: 34%; background-color: white;">
				<div style="height: 200px;">
					<div style="display: flex; padding: 10px;">
						<div style="margin-left: 10px;">Thông tin nhận hàng</div>
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
				<div style="margin-bottom: 50px;">
					<input type="submit" class="btn-confirm-order" value="Đặt mua">
				</div>
			</div>
		</div>
		<div style="clear: both"></div>
	</form:form>

	<jsp:include page="_footer.jsp" />

</body>
</html>