<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error</title>
<link rel="stylesheet" href="CSS/Error.css">
</head>
<body>
	<%
		String error = (String) request.getAttribute("errorMessage");
		if (error != null) {
	%>
	<div class="error-msg">
		<i class="fa fa-times-circle"></i>
		<%=error%>
	</div>
	<%
		}
	%>
</body>
</html>