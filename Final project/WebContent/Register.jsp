<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="CSS/Register.css">
</head>
<body bgcolor="#E6E6FA">

	<jsp:include page="Error.jsp" />

	<div class="Register-help">
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="ToLogin" /> <input
				type="submit" value="Go back" />
		</form>
	</div>
	<div class="Register-card">
		<h1>Register</h1>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="Register" /> Name : <input
				type="text" name="name" /> User Name : <input type="text"
				name="userName" /> Password : <input type="text" name="password" />
			Confirm password : <input type="text" name="confirmPassword" /> <input
				type="submit" class="Register Register-submit" value="Register" />
		</form>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>