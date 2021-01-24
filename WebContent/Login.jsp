<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online pizza order</title>
<link rel="stylesheet" href="styles/styles.css" type="text/css"/>
</head>
<body>
<%-- The div tag is used for defining the index section of the document. --%>

<%-- Include tag is used to import header page --%>
<%@ include file="Header.jsp" %>
<div id="login">
<form action="UserController" method="post">
<input type="hidden" name="action" value="login"/>

<span id="welcome">
<img src="images/welcome.png" alt="Welcome"/></span>
<table>

<h2> Login here </h2>
<tr>
<td>Email</td>
<td>

<input type="email" name="email" required/></td>
</tr>

<tr>
<td>Password</td>
<td>
<input type="password" name="password" required/></td>
</tr>

 
</table>
<div id=login1>
<input type="submit" value="login" id="submit"/></div>

</form>
</div>
<div>
<c:if test="${msg ne null }">
<br/>
            <div class="alert alert-danger"><b> ${msg}</b> <span class="glyphicon glyphicon-remove"></span>
            </div>
            </c:if>
</div>

<%-- Include tag is used to import footer page --%>
<%@ include file="Footer.jsp" %>
</body>
</html>