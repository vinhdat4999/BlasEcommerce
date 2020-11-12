<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>

<style>
body {
	font-family: 'open sans';
	overflow-x: hidden;
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

	<div class="page-title">Product</div>

	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage}</div>
	</c:if>
<%-- 
	<form:form modelAttribute="productForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<tr>
				<td>Code</td>
				<td style="color: red;"><input id="id" name="id" type="text"
					readonly value="${productId}" /></td>
			</tr>
			<tr>
				<td>Name *</td>
				<td><input id="name" name="name" type="text" /></td>
				<td><form:errors class="error-message" />${nameError}</td>
			</tr>

			<tr>
				<td>Price *</td>
				<td><input id="priceSt" name="priceSt" type="text" /></td>
				<td><form:errors class="error-message" />${priceError}</td>
			</tr>
			<tr>
				<td>Category</td>
				<td><select name="category" id="category">
						<c:forEach items="${categoryList}" var="item">
							<option>${item.category}</option>	
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input id="description" name="description" type="text" /></td>
				<td><form:errors path="description" class="error-message" /></td>
			</tr>
			<tr>
				<td>Image</td>
				<td><img id="productImg" name="productImg"
					src="${pageContext.request.contextPath}/productImage?id=${productForm.id}"
					width="100" /></td>
				<td><form:errors class="error-message" />${imageError}</td>
				<td></td>
			</tr>
			<tr>
				<td>Upload Image</td>
				<td><input type="file" id="image" name="image" /></td>
				<td></td>
			</tr>


			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> <input type="reset"
					value="Reset" /></td>
			</tr>
		</table>
	</form:form>


 --%>
	<form:form name="productDetail" modelAttribute="productForm"
		method="POST" enctype="multipart/form-data">
		<div>
			<div style="display: flex; border-style: groove;">
				<div style="padding: 30px; width: 60%;">
					<div class="group-container">
						<div class="title-container">Mã sản phẩm</div>
						<div>
							<input style="color: red;" readonly type="text" id="id"
								name="id" value="${productId}">
						</div>
						<div>
							<form:errors path="id" class="error-message" />
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Tên sản phẩm</div>
						<div>
							<input style="width: 430px;" type="text" name="name" id="name">
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
							<input id="priceSt" name="priceSt" type="text"/>
						</div>
						<div>
							<form:errors class="error-message" />${priceError}
						</div>
					</div>
					<div class="group-container">
						<div class="title-container">Mô tả</div>
						<div>
							<textarea rows="5" name="description" id="description" cols="50"></textarea>
						</div>
					</div>
				</div>
				<div style="display: inline;" class="group-container">
					<div style="text-align: center; width: 100%;"
						class="title-container">Ảnh chính sản phẩm</div>
					<div class="preview-thumbnail nav nav-tabs">
						<img style="height: 150px;"
							src="${pageContext.request.contextPath}/productImage?id=${productForm.id}" />
						<%-- <form:input type="file" name="image" path="image" /> --%>
						<input type="file" id="image" name="image">
					</div>
				</div>
			</div>
			<div style="display: inline;" class="group-container">
				<div style="text-align: center; width: 100%;"
					class="title-container">Ảnh phụ sản phẩm</div>
				<div style="display: inline;" class="preview-thumbnail nav nav-tabs">
					<form:form modelAttribute="myUploadForm" method="POST" action=""
						enctype="multipart/form-data">
							<div>
								<form:input path="fileDatas" type="file" />
							</div>
							<div>
								<form:input path="fileDatas" type="file" />
							</div>
							<div>
								<form:input path="fileDatas" type="file" />
							</div>
							<div>
								<form:input path="fileDatas" type="file" />
							</div>
							<div>
								<form:input path="fileDatas" type="file" />
							</div>
					</form:form>
				</div>
			</div>
			<div style="padding-bottom: 100px;">
				<input class="add-to-cart btn btn-default" type="submit" value="Thêm" />
			</div>
		</div>
	</form:form>

	<jsp:include page="_footer.jsp" />

</body>
</html>