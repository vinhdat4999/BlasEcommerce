<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <style>
body {
  font-family: 'open sans';
  overflow-x: hidden; }

</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<title>Enter Customer Information</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />
 
<div class="page-title">Enter Customer Information</div>

  <form:form method="POST" modelAttribute="customerForm"
      action="${pageContext.request.contextPath}/shoppingCartCustomer">
 	<table>
           <tr>
               <td>Name *</td>
               <td><form:input path="lastname" /></td>
               <td><form:errors path="lastname" class="error-message" />${nameError}</td>
           </tr>
 
           <tr>
               <td>Email</td>
               <td><form:input path="email" /></td>
               <td><form:errors path="email" class="error-message" />${emailError}</td>
           </tr>
 
           <tr>
               <td>Phone *</td>
               <td><form:input path="phoneNum" /></td>
               <td><form:errors path="phoneNum" class="error-message" />${phoneError}</td>
           </tr>
 
           <%-- <tr>
               <td>Address *</td>
               <td><form:input path="addressId" /></td>
               <td><form:errors path="addressId" class="error-message" />${addressError}</td>
           </tr> --%>
 
           <tr>
               <td>&nbsp;</td>
           </tr>
       </table>
            
      <a class="navi-item"
          href="${pageContext.request.contextPath}/cart">Edit Cart
      </a>
      <!-- Send/Save -->
      <input type="submit" value="Send" class="button-send-sc" />
  </form:form>
 
  <div class="container">
 
      <c:forEach items="${detailList}" var="item">
          <div class="product-preview-container">
              <ul>
	               <fmt:parseNumber var="priceValue" integerOnly="true" 
	                       type="number" value="${item.price}" />
	               <fmt:parseNumber var="quantityValue" integerOnly="true" 
	                       type="number" value="${item.quantity}" />
                  <li><img class="product-image"
                      src="${pageContext.request.contextPath}/productImage?id=${item.productId}" /></li>
                  <li>${item.productName}</li>
                  <li>${priceValue} X ${quantityValue} = ${priceValue * quantityValue}đ
                  </li>
              </ul>
          </div>
      </c:forEach>
 
  </div>
  
  <div class="customer-info-container">
  	  <fmt:parseNumber var="totalValue" integerOnly="true" 
          type="number" value="${totalAmount}" />
      <h2 align="right">Total: ${totalValue}đ</h2>
  </div>
  
   <jsp:include page="_footer.jsp" />
 
 
</body>
</html>