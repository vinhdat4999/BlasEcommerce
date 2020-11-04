<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
 <style>
body {
  font-family: 'open sans';
  overflow-x: hidden; }

</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/productStyle.css">
 
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <fmt:setLocale value="en_US" scope="session"/>
  
   <div class="page-title">Order List</div>
 
   <div>Total Order Count: ${paginationResult.totalRecords}</div>
  
   <table border="1" style="width:100%">
       <tr>
           <th>Order Num</th>
           <th>Order Date</th>
           <th>Customer Name</th>
           <th>Customer Address</th>
           <th>Customer Email</th>
           <th>Amount</th>
           <th>View</th>
       </tr>
       <c:forEach items="${paginationResult.list}" var="orderInfo">
           <tr>
               <td>${orderInfo.id}</td>
               <td>${orderInfo.orderTime}</td>
               <%-- <td>${orderInfo.receiverName}</td>
               <td>${orderInfo.receiverAddressId}</td>
               <td>${orderInfo.receiverEmail}</td> --%>
               <fmt:parseNumber var="intValue" integerOnly="true" 
                       type="number" value="${orderInfo.total}" />
               <td><c:out value = "${intValue}" /> đ</td>
               <td><a href="${pageContext.request.contextPath}/order?id=${orderInfo.id}">
                  View</a></td>
           </tr>
       </c:forEach>
   </table>
   <c:if test="${paginationResult.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationResult.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
          
       </div>
   </c:if>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>