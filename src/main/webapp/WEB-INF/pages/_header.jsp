<%@page import="com.blas.blasecommerce.util.NumberOfProduct"%>
<%@page import="com.blas.blasecommerce.util.NameLogging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/productStyle.css">
<style>
.dropbtn {
	background-color: #4CAF50;
	color: white;
	padding: 16px;
	font-size: 16px;
	border: none;
}

.btnlogin {
	width: fit-content;
	position: relative;
	display: inline-block;
	margin-right: 10%;
}

.badge {
	position: absolute;
	top: 5px;
	right: 3%;
	padding: 5px 10px;
	border-radius: 50%;
	background: red;
	color: white;
}

.dropdown {
	position: relative;
	display: inline-block;
	margin-right: 10%;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	min-width: 180px;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}
/*.dropdown:hover .dropdown-content {
	display: block;
}*/
.div4:hover .dropdown-content {
	display: block;
}

/*.dropdown:hover .dropbtn {
	background-color: #3e8e41;
} */
#div4 {
	cursor: pointer;
	float: right;
}

#div4>div {
	float: left;
}

#div1 {
	display: inline-block;
	width: fit-content;
}

#div2 {
	right: 0px;
	text-align: right;
	display: inline-block;
	width: 20%;
	right: 10px;
}

#myul {
	list-style-type: none;
	padding: 0;
	overflow: hidden;
	border: 1px solid #e7e7e7;
	background-color: #f3f3f3;
}

#myli {
	float: left;
}

#myli a {
	display: block;
	color: #666;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

#myli a:hover:not(.active) {
	color: white;
	background-color: #4CAF50;
}

#myli a:active {
	color: rgb(255, 255, 255);
	background-color: #38d43d;
}

.search-box {
	align-content: center;
	height: 35px;
	padding-left: 15px;
	width: 400px;
	border-radius: 5px;
	border: none;
	margin-left: 100px;
}
</style>
<script type="text/javascript">
function  validation(){
    var searchContent = document.getElementById("content").value;
    if(searchContent==""){
        return false;
    }
    return true;
}
</script>
</head>
<div class="header-container">
	<form action="${pageContext.request.contextPath}/search">
		<div style="display: flex;" class="site-name">
			<!-- <div id="div1" align="left"> -->
			<div id="div1">
				<a href="${pageContext.request.contextPath}/"><img height="50"
					src="<c:url value='/images/BLAS.png'/>" /></a>
			</div>
			<div style="padding-top: inherit;">
				<input value="${searchContent}" class="search-box" type="text" name="content" id="content"
					placeholder="Tìm sản phẩm..." style="border-radius: 30px;">
				<button class="add-to-cart btn btn-default" type="submit" onclick="return validation()">Tìm kiếm
				</button>
			</div>
			<div style="width: fit-content; margin-left: auto; margin-right: 5%;">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<div class="dropdown">
						<div class="div4class" id="div4">
							<div>
								<a href="${pageContext.request.contextPath}/login"></a> <img
									height="50" src="<c:url value='/images/account.png'/>" />
							</div>
							<div style="top: 50%;">
								<%
									String tempName = new NameLogging().getNameLogging();
								pageContext.setAttribute("nameLogging", tempName);
								%>
								<p>Chào ${nameLogging}</p>
							</div>
						</div>
						<div class="dropdown-content">
							<a href="${pageContext.request.contextPath}/orderList">Đơn
								hàng của tôi</a> <a
								href="${pageContext.request.contextPath}/accountInfo">Tài
								khoản của tôi</a> <a
								href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
						</div>
					</div>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<div class="btnlogin">
						<div id="div4">
							<div>
								<a href="${pageContext.request.contextPath}/login"><img
									height="50" src="<c:url value='/images/login.png'/>" /></a>
							</div>
						</div>
					</div>
				</c:if>
				<div id="div2" align="right">
					<a href="${pageContext.request.contextPath}/shoppingCart"><img
						height="50" src="<c:url value='/images/cart.png'/>" /></a>
					<%
						String numbersOfProductInCart = new NumberOfProduct().getNumbersOfProductInCart() + "";
					pageContext.setAttribute("numbersOfProductInCart", numbersOfProductInCart);
					%>
					<span class="badge">${numbersOfProductInCart}</span>
				</div>
			</div>
		</div>
	</form>

</div>