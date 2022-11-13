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
	
	<h3>Update objective question</h3>
	
		<form>
	    <label for="domain">Question Domain:</label><br>
	    <input type="text" id="domain" name="domain"><br>
	    <label for="text">Question text:</label><br>
	    <input type="text" id="text" name="text"><br><br>
	
	    <label for="text">Option 1:</label><br>
	    <input type="text" id="text" name="opt1"><br>
	    <label for="text">Option 2:</label><br>
	    <input type="text" id="text" name="opt2"><br>
	    <label for="text">Option 3:</label><br>
	    <input type="text" id="text" name="opt3"><br>
	    <label for="text">Option 4:</label><br>
	    <input type="text" id="text" name="opt4"><br> <br>
	
	    <label for="answer">Answer:</label><br>
	    <input type="text" id="answer" name="answer"><br>
	
	    <button>Submit</button>
	</form>

	<br> <br>
	<form action="Logout">
		<input type="submit" value="Logout" >
	</form>
	
</body>
</html>