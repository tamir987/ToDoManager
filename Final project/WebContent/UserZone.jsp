<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="Model.Classes.User, java.util.Set, Model.Classes.Item"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Zone</title>
<link rel="stylesheet" href="CSS/ToDoListUser.css">
<link rel="stylesheet" href="CSS/UserZone.css">
</head>
<body>
	<jsp:include page="Error.jsp" />
	<%
		User user = (User) session.getAttribute("user");
	%>

	<h1 class="title">
		Welcome,
		<%=user.getName()%></h1>

	<div class="User-help">
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="ToToDo" /> <input
				type="submit" value="Go back" />
		</form>
	</div>

	<div class="User-card" id="addToTable">
		<div class="divZone">
			<h1 class="titleZone">Edit user info</h1>
			<form action="Main" method="POST">
				<input type="hidden" name="action" value="DeleteUser" /> <input
					type="submit" value="Delete User" class="delUser" id="delButton" />
			</form>
		</div>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="UserZone" /> Name : <input
				type="text" name="name" value="<%=user.getName()%>" /> Password :
			<input type="text" name="password" value="<%=user.getPassword()%>" />
			Confirm Password : <input type="text" name="confirmPassword"
				value="<%=user.getPassword()%>" /> <input type="submit"
				value="Change" class="add Add-submit" />
		</form>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>