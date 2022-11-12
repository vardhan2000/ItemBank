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
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
		response.setHeader("Pragma", "no-cache" );
		
		response.setHeader("Expires", "0");
	
		if(session.getAttribute("username")==null) 
		{
			response.sendRedirect("home.jsp");
		}
	%>
	
	<a href="addItem.jsp"> Add Item </a> <br>
	<a href="updateItem.jsp"> Update Item </a> <br>
	<a href="deleteItem.jsp"> Delete Item </a>
	
	<br><br>
	
	<form action="Logout">
		<input type="submit" value="Logout" >
	</form>
</body>
</html>