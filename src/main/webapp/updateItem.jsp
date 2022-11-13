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
	
	<h1>Update Item</h1>
	<h3>Select the type of question you want to update</h3>
	
	<form action="UpdateItem" method="post" >
		<select name="type" id="type">
		    <option value="sub">Subjective</option>
		    <option value="mcq">MCQ</option>
		</select>
		<br>
		<button>Submit</button>
	</form>
	

	<br> <br>
	<form action="Logout">
		<input type="submit" value="Logout" >
	</form>
</body>
</html>