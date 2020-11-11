<%@page import="com.blas.blasecommerce.model.ProductModel"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/productStyle.css">
<style>
.zoom {
	padding: 50px;
	transition: transform .2s;
	margin: 0 auto;
}

.zoom:hover {
	transform: scale(2);
	margin-top: 65px;
	height: 210px;
}

#linkp {
	flex-direction: column;
	text-decoration: none;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>eCommerce Product Detail</title>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
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
	<c:if test="${productNotFound != null}">
		<div>
			<h2>Sản phẩm này không tồn tại hoặc đã bị xóa</h2>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/">Về trang mua sắm</a>
		</div>
	</c:if>
	<c:if test="${productNotFound == null}">
		<div class="container">
			<div class="card">
				<div class="container-fliud">
					<div class="wrapper row">
						<div class="preview col-md-6">
							<div class="preview-pic tab-content">
								<div class="zoom">
									<div class="tab-pane active" id="pic-1">
										<img class="product-image"
											<%-- src="${pageContext.request.contextPath}/productImageMain?productId=${productInfo.id}" /> --%>
									src="${pageContext.request.contextPath}/productImage?id=${productInfo.id}" />
									</div>
								</div>
							</div>
							<ul class="preview-thumbnail nav nav-tabs">
								<c:forEach items="${list}" var="item">
									<li style="padding: 10px; border-style: groove;"><img
										style="margin-bottom: 40px;" class="product-image"
										src="${pageContext.request.contextPath}/image?id=${item.id}" />
									</li>
								</c:forEach>
							</ul>

						</div>
						<div class="details col-md-6">
							<h3 class="product-title">${productInfo.name}</h3>
							<!-- <div class="rating">
							<div class="stars">
								<span class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span> <span
									class="fa fa-star checked"></span> <span class="fa fa-star"></span>
								<span class="fa fa-star"></span>
								<span class="review-no"><a
									style="text-decoration: none;" href="#">Xem 41 đánh giá</a></span>
							</div>
						</div> -->
							<p class="product-description">${productInfo.description}</p>
							<h4 class="price">
								</li> <span>${price}đ</span>
							</h4>

							<!-- <h5 class="sizes">
							sizes: <span class="size" data-toggle="tooltip" title="small">s</span>
							<span class="size" data-toggle="tooltip" title="medium">m</span>
							<span class="size" data-toggle="tooltip" title="large">l</span> <span
								class="size" data-toggle="tooltip" title="xtra large">xl</span>
						</h5>
						<h5 class="colors">
							colors: <span class="color orange not-available"
								data-toggle="tooltip" title="Not In store"></span> <span
								class="color green"></span> <span class="color blue"></span>
						</h5> -->
							<form method="post">
								<div>
									<div style="margin-bottom: 50px;">
										<p class="label">Số Lượng</p>
										<div>
											<button name="btnDes" onclick="return desItem()">-</button>
											<input type="text" name="quanityItem" id="quanityItem"
												value="1" />
											<button name="btnInc" onclick="return incItem()">+</button>
										</div>
									</div>
									<input type="submit" class="add-to-cart btn btn-default"
										value="Thêm vào giỏ hàng">
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>