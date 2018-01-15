<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
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