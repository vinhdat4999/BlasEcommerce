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
				<td><select name="categoryId" id="categoryId">
						<c:forEach items="${categoryList}" var="item">
							<option>${item}</option>	
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




	<jsp:include page="_footer.jsp" />

</body>
</html>