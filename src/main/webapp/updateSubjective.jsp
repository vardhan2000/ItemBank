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
	
	<h3>Update subjective question</h3>
	
	<form action="UpdateSubjective" method="post" >
		<label for="id">Question ID:</label><br>
	    <input type="text" id="id" name="id"><br> <br>
	    
	    <label for="domain">Question Domain:</label><br>
	    <input type="text" id="domain" name="domain"><br>
	    <label for="text">Question text:</label><br>
	    <input type="text" id="text" name="text"><br>
	    
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