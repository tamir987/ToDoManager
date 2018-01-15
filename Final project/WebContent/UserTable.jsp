<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page import="Model.Classes.User, java.util.Set, Model.Classes.Item"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Table</title>
<link rel="stylesheet" href="CSS/ToDoListUser.css">
<link rel="stylesheet" href="CSS/userTable.css">
</head>
<body>
	<%@ taglib uri="/WEB-INF/tlds/custom.tld" prefix="display"%>

	<jsp:include page="Error.jsp" />

	<%
		User user = (User) session.getAttribute("user");
		Set<Item> userItems = user.getItems();
	%>
	<h1 class="title">
		Welcome,
		<%=user.getName()%></h1>
	<div class="User-help">
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="ToUser" /> <input
				type="submit" value="User Zone" />
		</form>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="Logout" /> <input
				type="submit" value="Log out" />
		</form>
	</div>

	<div class="User-card" id="table">
		<div class="divx">
			<h1 class="titlex">List of ToDos</h1>
			<form action="Main" method="POST">
				<input type="hidden" name="action" value="ToAdd" /> <input
					type="submit" value="Add" class="addx" id="addButton" />
			</form>
		</div>
		<table>
			<thead>
				<tr>
					<th>Mission</th>
					<th>Location</th>
					<th>Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<%
				if (userItems != null) {
			%>
			<tbody>
				<%
					for (Item itemx : userItems) {
				%>
				<tr>
					<display:todotag item="<%=itemx%>" />
					<td>
						<form action="Main" method="POST">
							<input type="hidden" name="action" value="DeleteItem" /> <input
								type="hidden" name="item" value="<%=itemx.getId()%>" /> <input
								type="submit" value="Delete" class="add Add-submit" />
						</form>
						<form action="Main" method="POST">
							<input type="hidden" name="action" value="ToEditItem" /> <input
								type="hidden" name="item" value="<%=itemx.getId()%>" /> <input
								type="submit" value="Edit" class="add Add-submit" />
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
			<%
				}
			%>
		</table>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>