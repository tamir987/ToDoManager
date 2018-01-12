<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
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