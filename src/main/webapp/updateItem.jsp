<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IOAES</title>
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
	
	<form>
		<label for="domain">Question type:</label><br>
	    <select name="type" id="type">
	        <option value="sub">Subjective</option>
	        <option value="mcq">MCQ</option>
	    </select>
	    <br>
	
	    <label for="domain">Question Domain:</label><br>
	    <input type="domain" id="domain" name="domain"><br>
	    <label for="text">Question text:</label><br>
	    <input type="text" id="text" name="text"><br>
	
	    <!-- <label for="options">Number of options:</label><br>
	    <input type="number" id="options" name="options" min="1" max="5"><br>
	    <label for="text">Option 1:</label><br>
	    <input type="text" id="text" name="text"><br>
	    <label for="text">Option 2:</label><br>
	    <input type="text" id="text" name="text"><br>
	    <label for="text">Option 3:</label><br>
	    <input type="text" id="text" name="text"><br> -->
	
	    <label for="answer">Answer:</label><br>
	    <input type="answer" id="answer" name="answer"><br>
	
	    <button>Submit</button>
	</form>
	    
	<br/> <br/>
	<form action="Logout">
		<input type="submit" value="Logout" >
	</form>
</body>
</html>