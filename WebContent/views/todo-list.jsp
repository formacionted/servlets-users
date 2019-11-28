<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Directory</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
	<div class = "mt-5 mb-5 container">
		
		<h1>Todo List</h1>
		<hr/>
		
		<p>${NOTIFICATION}</p>
		
		<p>
			<button class = "btn btn-primary" onclick="window.location.href = 'views/todo-create.jsp'">Add TODO</button>
		</p>
	
		<table class = "table table-striped table-bordered">
			
			<tr class = "thead-dark">
				<th>Description</th>
				<th>Done</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${todoList}" var="todo">
			
				<tr>
					<td>${todo.description}</td>
					<td>${todo.done}</td>
					<td> 
						<a href = "${pageContext.request.contextPath}/todos?action=u&id=${todo.id}">Edit</a> 
						| 
						<a href = "${pageContext.request.contextPath}/todos?action=d&id=${todo.id}">Delete</a> 
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
		
	</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>