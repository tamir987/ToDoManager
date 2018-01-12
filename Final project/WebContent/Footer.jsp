<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
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
		long time = (long) request.getAttribute("time");
		long militime = (System.currentTimeMillis() - time);
		double elapsed = militime / 1000.0;
	%>
	<div class="info-msg">
		<i class="fa fa-times-circle"></i> The response took
		<%=elapsed%>
		seconds.
	</div>
</body>
</html>