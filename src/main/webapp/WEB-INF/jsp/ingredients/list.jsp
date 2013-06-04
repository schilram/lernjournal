<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ingredients</title>
</head>
<body>

<h1>Ingredients</h1>

<table>
	<tr>
		<td width="50">Id</td>
		<td width="150">Name</td>
		<td width="450">Description</td>
		<td width="100">Flavour</td>
	</tr>
	<c:forEach items="${ingredients}" var="i">
		<tr>
			<td><c:out value="${i.id}" /></td>
			<td><c:out value="${i.name}" /></td>
			<td><c:out value="${i.description}" /></td>
			<td><c:out value="${i.flavour}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>