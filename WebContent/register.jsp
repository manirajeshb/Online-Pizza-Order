<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online pizza order</title>
<link rel="stylesheet" href="styles/styles.css" type="text/css" />
</head>
<body>
	<%-- The div tag is used for defining the index section of the document. --%>

	<%-- Include tag is used to import header page --%>
	<%@ include file="Header.jsp"%>
	<div id="AboutUs">
		<form action="UserController" method="post">
			<input type="hidden" name="action" value="create" />
			<table>
				<div id="register">
					<br> <strong>Register Here </strong>
				</div>
				<br>
				<br>
				<br>

				<tr>
					<td>FirstName*</td>
					<td><input type="text" name="FirstName" required></td>
				</tr>
				<tr>
					<td>LastName*</td>
					<td><input type="text" name="LastName" required></td>
				</tr>

				<tr>
					<td>Gender*</td>
					<td><input type="radio" name="gender" value="Male" required>
						Male <input type="radio" name="gender" value="Female" required>
						Female</td>
				</tr>

				<span id="welcome"> <img src="images/welcome.png"
					alt="Welcome" /></span>
				<tr>
					<td>Email*</td>
					<td><input type="email" name="Email" required></td>
				</tr>
				<tr>
					<td>Password*</td>
					<td><input type="password" name="Password" required></td>
					<td>(password must be minimum 4 characters)</td>
				</tr>
				<tr>
					<td>Confirm password*</td>
					<td><input type="password" name="ConfirmPwd" required>
					</td>
					<td>(both the passwords must match)</td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Create Account"></td>
				</tr>
			</table>
		</form>

		<div>
			<c:if test="${msg ne null }">
				<br />
				<div class="alert-danger">
					<h3>${msg}</h3>
					<span class="glyphicon glyphicon-remove"></span>
				</div>
			</c:if>
		</div>
		
		<marquee>All the fields marked with * are mandatory</marquee>
		</div>
	
	<%-- Include tag is used to import footer page --%>
	<%@ include file="Footer.jsp"%>
</body>
</html>