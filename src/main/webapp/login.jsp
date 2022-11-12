<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OAES</title>
</head>
<body>
	<h1>Author Login</h1>
	<form action="Login" method="post">
<!-- 		Enter username: <input type="text" name="uname"><br> -->
<!-- 		Enter password: <input type="password" name="pass"><br> -->
		
		<label for="username">Username:</label><br>
	    <input type="text" id="text" name="uname"><br>
	    <label for="password">Password:</label><br>
	    <input type="password" id="password" name="pass"><br>
		<button>Submit</button>
		
<!-- 		<input type="submit" value="login"> -->
	</form>

</body>
</html>