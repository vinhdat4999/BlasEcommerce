<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Product List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<link href="view/main.css" rel="stylesheet">
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<style>
#myBtn {
	display: none;
	position: fixed;
	bottom: 20px;
	right: 5px;
	z-index: 99;
	font-size: 18px;
	border: none;
	outline: none;
	background-color: #189fff;
	color: white;
	cursor: pointer;
	padding: 10px;
	border-radius: 4px;
}

#myBtn:hover {
	background-color: #18cfff;
}

.add-to-cart {
	text-decoration: none;
	background: #189fff;
	padding: 0.7em 0.7em;
	border: none;
	text-transform: UPPERCASE;
	color: #fff;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
}

body {
	font-family: 'open sans';
	overflow-x: hidden;
}

.add-to-cart:hover, .like:hover {
	background: #18cfff;
	color: #fff;
	cursor: pointer;
}

.delete-product {
	text-decoration: none;
	background: #189fff;
	padding: 1.2em 1.5em;
	border: none;
	text-transform: UPPERCASE;
	font-weight: bold;
	color: #fff;
	-webkit-transition: background .3s ease;
	transition: background .3s ease;
}

.delete-product:hover {
	background: #18cfff;
	color: #fff;
	cursor: pointer;
}

#linkp {
	flex-direction: column;
	text-decoration: none;
	color: black;
}

.sort {
	margin-left: auto;
	margin-right: 50px;
	width: fit-content;
	padding-top: 15px;
}
/* 
body {
  margin: 0;
} */
.navigation>ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	background-color: #f1f1f1;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

li a.active {
	/* background-color: #4CAF50; */
	/* color: white; */
	
}

li a:hover:not(.active) {
	background-color: #189fff;
	color: white;
}
</style>
<script type="text/javascript">
	function editProduct(productId) {
		window.location.href = "${pageContext.request.contextPath}/editProduct?id="
				+ productId;
	}
	function deleteProduct(productId) {
		var txt;
		var r = confirm("Bạn có chắc muốn xóa sản phẩm này không?");
		if (r == true) {
			window.location.href = "${pageContext.request.contextPath}/delete-product?productId="
					+ productId;
		}
	}
</script>
</head>
<body>
	<script type="text/javascript">
		var mybutton = document.getElementsByName("myBtn");
		window.onscroll = function() {
			scrollFunction()
		};
		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				for (var i = 0; i < mybutton.length; i += 1) {
					mybutton[i].style.display = 'block';
				}
			} else {
				for (var i = 0; i < mybutton.length; i += 1) {
					mybutton[i].style.display = 'none';
				}
			}
		}

		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>

	<button onclick="topFunction()" id="myBtn" name="myBtn"
		title="Go to top">Top</button>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />
	<div>
		<div class="navigation"
			style="float: left; width: 20%; margin-top: 40px;">
			<ul>
				<li><a href="${pageContext.request.contextPath}/banh">Bánh</a></li>
				<li><a href="${pageContext.request.contextPath}/keo">Kẹo</a></li>
				<li><a href="${pageContext.request.contextPath}/nuoc-ngot">Nước
						ngọt</a></li>
			</ul>
		</div>
		<%
			String type = pageContext.getRequest().getParameter("sort");
		if (pageContext.getRequest().getParameter("sort") == null) {
			pageContext.setAttribute("sortType", "Mặc định");
		} else {
			if (type.equals("price-des")) {
				pageContext.setAttribute("sortType", "Giá: cao -> thấp");
			} else {
				if (type.equals("price-inc")) {
			pageContext.setAttribute("sortType", "Giá: thấp -> cao");
				}
			}
		}
		%>
		<%
			String s1 = request.getAttribute("javax.servlet.forward.request_uri").toString();
		if (request.getAttribute("javax.servlet.forward.query_string") != null) {
			String s2 = request.getAttribute("javax.servlet.forward.query_string").toString();
			if (!s2.contains("sort=")) {
				pageContext.setAttribute("defaultSort", s1 + "?" + s2);
				pageContext.setAttribute("priceDes", s1 + "?" + s2 + "&sort=price-des");
				pageContext.setAttribute("priceInc", s1 + "?" + s2 + "&sort=price-inc");
			} else {
				s2 = s2.replace("?sort=price-des", "");
				s2 = s2.replace("?sort=price-inc", "");
				s2 = s2.replace("&sort=price-des", "");
				s2 = s2.replace("&sort=price-inc", "");
				s2 = s2.replace("sort=price-des", "");
				s2 = s2.replace("sort=price-inc", "");
				if (!s2.equals("")) {
			s2 = "?" + s2;
				}
				pageContext.setAttribute("defaultSort", s1 + s2);
				if (s2.equals("")) {
			pageContext.setAttribute("priceDes", s1 + s2 + "?sort=price-des");
			pageContext.setAttribute("priceInc", s1 + s2 + "?sort=price-inc");
				} else {
			pageContext.setAttribute("priceDes", s1 + s2 + "&sort=price-des");
			pageContext.setAttribute("priceInc", s1 + s2 + "&sort=price-inc");
				}
			}
		} else {
			if (request.getAttribute("javax.servlet.forward.servlet_path") == null) {
				pageContext.setAttribute("defaultSort", request.getAttribute("javax.servlet.forward.context_path"));
			} else {
				pageContext.setAttribute("defaultSort", s1);
			}
			pageContext.setAttribute("priceDes", s1 + "?sort=price-des");
			pageContext.setAttribute("priceInc", s1 + "?sort=price-inc");
		}
		%>
		<div style="float: left; width: 80%">
			<div class="sort">

				Sắp xếp <select name="categoryId" id="categoryId"
					onchange="location = this.value;">
					<option value="" hidden selected>${sortType}</option>
					<option value="${defaultSort}">Mặc định</option>
					<option value="${priceDes}">Giá: cao -> thấp</option>
					<option value="${priceInc}">Giá: thấp -> cao</option>
				</select>
			</div>
			<div>
				<c:forEach items="${paginationProducts.list}" var="prodInfo">
					<div class="product-preview-container">
						<a id="linkp"
							href="${pageContext.request.contextPath}/product?id=${prodInfo.id}">
							<ul>
								<li><img class="product-image"
									src="${pageContext.request.contextPath}/productImage?id=${prodInfo.id}" /></li>
								<li style="height: 75px;">${prodInfo.name}</li>
								<fmt:parseNumber var="intValue" integerOnly="true" type="number"
									value="${prodInfo.price}" />
								<li><c:out value="${intValue}" /> đ</li>
							</ul>
						</a>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<div style="margin-left: 49px;" class="action">
								<input style="width: auto;" class="add-to-cart btn btn-default"
									type="button" value="Sửa"
									onclick="editProduct('${prodInfo.id}')"> <input
									class="add-to-cart btn btn-default" type="button"
									style="background-color: red; width: auto;" value="Xóa"
									onclick="deleteProduct('${prodInfo.id}')">
							</div>
						</security:authorize>
					</div>
				</c:forEach>

				<br />
				<c:if test="${paginationProducts.totalPages > 1}">
					<div class="page-navigator">
						<c:forEach items="${paginationProducts.navigationPages}"
							var="page">
							<c:if test="${page != -1 }">
								<a href="?page=${page}" class="nav-item">${page}</a>
							</c:if>
							<c:if test="${page == -1 }">
								<span class="nav-item"> ... </span>
							</c:if>
						</c:forEach>

					</div>
				</c:if>
			</div>
		</div>
	</div>


	<jsp:include page="_footer.jsp" />

</body>
</html>