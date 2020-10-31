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
body {
  font-family: 'open sans';
  overflow-x: hidden; }

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

	<form:form modelAttribute="productForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<tr>
				<td>Code *</td>
				<td style="color: red;"><c:if
						test="${not empty productForm.id}">
						<form:hidden path="id" />
                       ${productForm.id}
                  </c:if> <c:if test="${empty productForm.id}">
						<form:input path="code" />
						<form:hidden path="newProduct" />
					</c:if></td>
				<td><form:errors path="id" class="error-message" /></td>
			</tr>
			<tr>
				<td>Name *</td>
				<td><form:input path="name" /></td>
				<td><form:errors class="error-message" />${nameError}</td>
			</tr>

			<tr>
				<td>Price *</td>
				<td><input id="priceSt" name="priceSt" type="text"
					value="${priceSt}" /></td>
				<td><form:errors class="error-message" />${priceError}</td>
			</tr>

			<tr>
				<td>Description</td>
				<td><form:input path="description" /></td>
				<td><form:errors path="description" class="error-message" /></td>
			</tr>
			<tr>
				<td>Image</td>
				<td>
					<ul class="preview-thumbnail nav nav-tabs">
						<c:forEach items="${listLink}" var="item">
							<li style="padding-left: 50px;">
								<img style="margin-bottom: 40px;" class="product-image"
								<%-- src="${pageContext.request.contextPath}/productImage?id=${item.id}" /> --%>
								src="${pageContext.request.contextPath}/productImage?id=${productForm.id}" />
								<form:input type="file" name="image" path="image" /> <a
								style="margin-right: 50px; margin-left: -50px;"
								class="add-to-cart btn btn-default"
								href="${pageContext.request.contextPath}/editProduct?id=${prodInfo.id}">Xóa</a>
							</li>

						</c:forEach>
				</td>
			</tr>
			<%-- <tr>
				<td>Upload Image</td>
				<td><form:input type="file" path="image" /></td>
				<td></td>
			</tr> --%>


			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> <input type="reset"
					value="Reset" /></td>
			</tr>
		</table>
	</form:form>




	<jsp:include page="_footer.jsp" />

</body>
</html>