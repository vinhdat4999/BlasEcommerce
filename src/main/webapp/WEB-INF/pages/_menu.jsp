<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="menu-container">
    <div>
	   	<security:authorize  access="hasRole('ROLE_MANAGER')">
	         <a href="${pageContext.request.contextPath}/createProduct">
	                        Create Product
	         </a>
	         |
	   </security:authorize>
   </div>
  
</div>