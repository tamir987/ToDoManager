<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="Model.Classes.Item"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit To Do</title>
<link rel="stylesheet" href="CSS/ToDoListUser.css">
<link rel="stylesheet" href="CSS/Edit.css">
</head>
<body>

	<%
		Item item = (Item) request.getAttribute("itemToUpdate");
		application.setAttribute("itemToUpdate", item);
		String mission = item.getMission();
		String location = item.getLocation();
		String future = item.getFutureDate();
	%>

	<div class="User-help">
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="ToToDo" /> <input
				type="submit" value="Go back" />
		</form>
	</div>

	<div class="User-card" id="addToTable">
		<h1 class="EditTitle">Edit</h1>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="EditItem" /> Mission : <input
				type="text" name="mission" value="<%=mission%>" /> Location : <input
				type="text" name="location" value="<%=location%>" /> When? : <input
				type="text" name="futureDate" value="<%=future%>" /> <input
				type="submit" value="Edit" class="add Add-submit" />
		</form>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>