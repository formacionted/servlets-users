<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	
	<div class = "mt-5 mb-5 container">
	
		<h1>Todo detail ${todo.id}</h1>
		<hr/>
		
		<div class = "row">
			<div class = "col-md-4">
				<form action = "${pageContext.request.contextPath}/todos" method="POST">
				
					<div class = "form-group">
						<input type = "text" class = "form-control" name = "description" placeholder = "Enter Name" value = "${todo.description}"/>
					</div>

<div class="form-check">
	<c:choose>
		<c:when test="${todo.done}">
		  <input class="form-check-input" type="checkbox" name="done" id="defaultCheck1" checked>
		</c:when>
		<c:otherwise>
		  <input class="form-check-input" type="checkbox" name="done" id="defaultCheck1">
		</c:otherwise>
	</c:choose>
  <label class="form-check-label" for="defaultCheck1">
    Done
  </label>
</div>
					<input type = "hidden" name = "id" value = "${todo.id}"/>
				
					<button type = "submit" class = "btn btn-primary mt-3">Save</button>
				</form>
			</div>
		</div>
		<a href = "${pageContext.request.contextPath}/todos">Back to List</a>
	</div>
	
<jsp:include page="footer.jsp"></jsp:include>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>