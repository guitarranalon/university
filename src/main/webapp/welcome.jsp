<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestión de estudiantes</title>
</head>
<body>
	<table>
	<tr>
		<th>Nombre</th>
		<th>Email</th>
		<th></th>
	</tr>
	  <c:forEach items="${studentList}" var="student">
	    <tr>
	      <%-- <td>id: ${student.id}</td>--%>
	      <td>${student.name}</td>
	      <td>${student.email}</td>
	      <td>
	      	<form method="post" action="EditStudentServlet">
	      		<input type="hidden" id="studentId" name="studentId" value="${student.id}" />
	      		<input type="hidden" id="studentName" name="studentName" value="${student.name}" />
	      		<input type="hidden" id="studentEmail" name="studentEmail" value="${student.email}" />
	      		<input type="submit" value="Editar" />
	      	</form>
	      	<form method="post" action="DeleteStudentServlet">
	      		<input type="hidden" id="studentId" name="studentId" value="${student.id}" />
	      		<input type="submit" value="Borrar" />
	      	</form>
	      </td>
	    </tr>
	  </c:forEach>
	</table>
	<form method="post" action="AddStudentServlet">
		<label for="nameTxt">Nombre</label>
		<input type="text" id="nameTxt" name="nameTxt" />
		<label for="emailInput">Email</label>
		<input type="email" id="emailInput" name="emailInput" />
		<input type="submit" value="Añadir estudiante" />
	</form>
</body>
</html>