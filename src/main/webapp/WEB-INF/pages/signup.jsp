<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Login</title>
<style>
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




	<div class="login-container">

		<h2 style="text-align: center;">Đăng ký tài khoản</h2>
		<br>
		<!-- /login?error=true -->
		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<%-- <form method="POST" style="margin: auto;width: 30%;"
			action="${pageContext.request.contextPath}/j_spring_security_check"> --%>
		<form:form modelAttribute="accountForm" method="POST"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>Tên đăng nhập</td>
					<td><input name="username"></input></td>
				</tr>
				<tr>
					<td>Họ và tên</td>
					<td><input name="lastname"></input></td>
					<td>
						<form>
							<input type="radio" name="genderR" value="Nam" />Nam <input
								type="radio" name="genderR" value="Nữ" />Nữ
						</form>
					</td>
				</tr>

				<tr>
					<td>Ngày sinh</td>
					<td><select name="day">
							<option value="01">01</option>
							<option>02</option>
							<option>03</option>
							<option>04</option>
							<option>05</option>
							<option>06</option>
							<option>07</option>
							<option>08</option>
							<option>09</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
							<option>19</option>
							<option>20</option>
							<option>21</option>
							<option>22</option>
							<option>23</option>
							<option>24</option>
							<option>25</option>
							<option>26</option>
							<option>27</option>
							<option>28</option>
							<option>29</option>
							<option>30</option>
							<option>31</option>
					</select> <select name="month">
							<option value="01">Tháng 1</option>
							<option value="02">Tháng 2</option>
							<option value="03">Tháng 3</option>
							<option value="04">Tháng 4</option>
							<option value="05">Tháng 5</option>
							<option value="06">Tháng 6</option>
							<option value="07">Tháng 7</option>
							<option value="08">Tháng 8</option>
							<option value="09">Tháng 9</option>
							<option value="10">Tháng 10</option>
							<option value="11">Tháng 11</option>
							<option value="12">Tháng 12</option>
					</select> <select name="year">
							<option value="1990">1990</option>
							<option value="1991">1991</option>
							<option value="1992">1992</option>
							<option value="1993">1993</option>
							<option value="1994">1994</option>
							<option value="1995">1995</option>
							<option value="1996">1996</option>
							<option value="1997">1997</option>
							<option value="1998">1998</option>
							<option value="1999">1999</option>
							<option value="2000">2000</option>
							<option value="2001">2001</option>
							<option value="2002">2002</option>
					</select></td>
				</tr>
				<tr>
					<td>SĐT</td>
					<td><input type="text" name="phoneNum"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Địa chỉ</td>
					<td><input type="text" name="address"></td>
				</tr>
				<tr>
					<td>Mật khẩu</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Nhập lại mật khẩu</td>
					<td><input type="password" name="reTypePassword" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Đăng ký" /></td>
				</tr>
			</table>
			</form:form>
	</div>


	<jsp:include page="_footer.jsp" />

</body>
</html>