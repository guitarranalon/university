<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit student</title>
</head>
<body>
	<form method="post" action="SaveEditStudent">
	    <input type="hidden" id="studentId" name="studentId" value="${editingStudent.id}" />
    	<input type="text" id="studentName" name="studentName" value="${editingStudent.name}" />
    	<input type="email" id="studentEmail" name="studentEmail" value="${editingStudent.email}" />
    	<input type="submit" value="Guardar" />
	</form>
</body>
</html>