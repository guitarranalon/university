<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Uniovi</title>
</head>
<body>
	<h1>User login</h1>
	<form action="LoginServlet" method="post">
		<div><label id="txtName">Name</label><input type="text" id="txtName" name="txtName" /></div>
		<div><label id="txtPass">Password</label><input type="password" id="txtPass" name="txtPass" /></div>
		
		<div><input type="submit" value="Login" /><input type="reset" /></div>
	</form>
</body>
</html>