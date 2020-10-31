<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart Finalize</title>
 <style>
body {
  font-family: 'open sans';
  overflow-x: hidden; }

</style>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
<link rel="icon" href="${pageContext.request.contextPath}/blas.ico">
</head>
<body>
   <jsp:include page="_header.jsp" />
 
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Finalize</div>
  
   <div class="container">
       <h2>Thank you for Order</h2>
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>