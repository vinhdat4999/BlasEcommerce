<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<script type="text/javascript">
	window.onload = function() {
		var category = "${productForm.category}";
		console.log(category);
		document.productDetail.category.value = category;
	};
	function deleteImage(imageId) {
		var txt;
		var r = confirm("Bạn có chắc muốn xóa ảnh này không?");
		if (r == true) {
			window.location.href = "${pageContext.request.contextPath}/delete-image?imageId="
					+ imageId;
		}
	}
</script>
<style>
li {
	float: left;
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

.group-container {
	display: flex;
	padding: 5px;
}

body {
	font-family: 'open sans';
	overflow-x: hidden;
}

.title-container {
	width: 200px;
	text-align: left;
}
</style>

<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage}</div>
	</c:if>

	<form:form name="productDetail" modelAttribute="productForm"
		method="POST" enctype="multipart/form-data">
		<div>
			<div style="display: flex; border-style: groove;">
				<div style="padding: 30px; width: 60%;">
					<div class="group-container">
						<div class="title-container">Mã sản phẩm</div>
						<div>
							<input style="color: red;" readonly type="text" id="productId"
								name="productId" value="${productForm.id}">
						</div>
						<div>
							<form:errors path="id" class="error-message" />
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Tên sản phẩm</div>
						<div>
							<input style="width: 430px;" type="text" name="name" id="name"
								value="${productForm.name}">
						</div>
						<div>
							<form:errors class="error-message" />${nameError}
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Danh mục sản phẩm</div>
						<div>
							<select name="category" id="category">
								<c:forEach items="${categoryList}" var="item">
									<option>${item.category}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Giá</div>
						<div>
							<input id="priceSt" name="priceSt" type="text" value="${priceSt}" />
						</div>
						<div>
							<form:errors class="error-message" />${priceError}
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Mô tả</div>
						<div>
							<%-- <form:input path="description" /> --%>
							<textarea rows="5" name="description" id="description" cols="50">${productForm.description}</textarea>

						</div>
					</div>
				</div>
				<div style="display: inline;" class="group-container">
					<div style="text-align: center; width: 100%;"
						class="title-container">Ảnh chính sản phẩm</div>
					<div class="preview-thumbnail nav nav-tabs">
						<img style="height: 150px;"
							src="${pageContext.request.contextPath}/productImage?id=${productForm.id}" />
						<form:input type="file" name="image" path="image" />
					</div>
				</div>
			</div>
			<div style="display: inline;" class="group-container">
				<div style="text-align: center; width: 100%;"
					class="title-container">Ảnh phụ sản phẩm</div>
				<div style="display: inline;" class="preview-thumbnail nav nav-tabs">
					<form:form modelAttribute="myUploadForm" method="POST" action=""
						enctype="multipart/form-data">
						<c:forEach items="${listLink}" var="item">
							<div style="padding: 10px; padding-left: 50px;">
								<img style="height: 150px;"
									src="${pageContext.request.contextPath}/image?id=${item.id}" />
								<form:input path="fileDatas" type="file" />
								<input type="button"
									style="margin-right: 50px; margin-left: -50px;"
									class="add-to-cart btn btn-default"
									onclick="deleteImage('${item.id}')" value="Xóa">
							</div>

						</c:forEach>
						<div style="padding: 50px;">
							Thêm ảnh mới <form:input path="fileDatas" type="file" />	
						</div>
					</form:form>
				</div>
			</div>
			<div style="padding-bottom: 100px;">
				<input class="add-to-cart btn btn-default" type="submit" value="Chỉnh sửa" />
			</div>
		</div>
	</form:form>




	<jsp:include page="_footer.jsp" />

</body>
</html>