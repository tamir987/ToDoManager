<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ToDo Manager</title>
<link rel="stylesheet" href="CSS/Login.css">
</head>
<body bgcolor="#E6E6FA">

	<jsp:include page="Error.jsp" />
	<div class="login-card">
		<h1>ToDo Manager</h1>
		<form action="Main" method="POST">
			<input type="hidden" name="action" value="Login" /> User Name : <input
				type="text" name="userName" /> Password : <input type="password"
				name="password" /> <input type="submit" value="Login"
				class="login login-submit" />
		</form>
		<div class="login-help">
			<form action="Main" method="POST">
				<input type="hidden" name="action" value="ToRegister" /> <input
					type="submit" value="Register" />
			</form>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
</body>
</html>