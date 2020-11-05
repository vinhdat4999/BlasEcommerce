<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Account Info</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
<style>
body {
  font-family: 'open sans';
  overflow-x: hidden; }

</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
</head>
<body>
 
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="account-container">
 
 
       <ul>
           <li>User Name: ${pageContext.request.userPrincipal.name}</li>
           <li>Tên: ${user.firstname} ${user.lastname}</li>
           <li>SĐT: ${user.phoneNum}
           <li>Email: ${user.email}
           </li>
       </ul>
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>