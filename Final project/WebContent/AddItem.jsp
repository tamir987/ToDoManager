<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>To Do List</title>
<link rel="stylesheet" href="CSS/ToDoListUser.css">
<link rel="stylesheet" href="CSS/Edit.css">
</head>
<body>

	<jsp:include page="Error.jsp" />

	<div class="User-help">
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="ToToDo" /> <input
				type="submit" value="Go back" />
		</form>
	</div>

	<div class="User-card" id="addToTable">
		<h1 class="EditTitle">Add ToDo</h1>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="AddItem" /> Mission : <input
				type="text" name="mission" /> Location : <input type="text"
				name="location" /> When? : <input type="text" name="futureDate" />
			<input type="submit" value="Add" class="add Add-submit" /> <input
				type="reset" value="Reset" class="add Add-submit">
		</form>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>