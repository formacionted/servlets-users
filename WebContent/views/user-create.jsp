<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="mt-5 mb-5 container">

		<h1>User detail ${user.id}</h1>
		<hr />

		<div class="row">
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/profile"
					method="POST">

					<div class="form-group">
						<label for="email"><b>Email</b></label> <input type="text"
							placeholder="Enter Username" name="email" value="${user.email}"
							required>
					</div>
					<div class="form-group">

						<label for="password"><b>Password</b></label> <input
							type="password" placeholder="Enter Password" name="password"
							required>
					</div>

					<input type="hidden" name="id" value="${user.id}" />

					<button type="submit" class="btn btn-primary mt-3">Save</button>
				</form>
			</div>
		</div>
		<a href="${pageContext.request.contextPath}/profile">Back to List</a>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>