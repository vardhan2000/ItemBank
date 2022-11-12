<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OAES</title>
</head>
<body>
	<% 
		if(session.getAttribute("username")!=null) 
		{
			response.sendRedirect("authorhome.jsp");
		}
	%>
	
	<h1>Item Bank Module</h1>
	<a href="register.jsp"> Register as Author </a> <br/>
	<a href="login.jsp">Login as Author</a>
	
</body>
</html>