<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Administrator zone</title>
<link
	href="https://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css"
	rel="stylesheet" media="screen">
<link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="Bootstrap/css/styles.css">
<link href="Bootstrap/css/stats.css" rel="stylesheet">
<link rel="stylesheet" href="Bootstrap/css/morris.css">

<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

</head>
<body onload="load()">
	<%
		int num = ((Set) application.getAttribute("sessions")).size();

		int Windows = (int) application.getAttribute("Windows");
		int Mac = (int) application.getAttribute("Mac");
		int Unix = (int) application.getAttribute("Unix");
		int Android = (int) application.getAttribute("Android");
		int IPhone = (int) application.getAttribute("IPhone");
		int UnKnown = (int) application.getAttribute("UnKnown");
		int IE = (int) application.getAttribute("IE");
		int safari = (int) application.getAttribute("safari");
		int Opera = (int) application.getAttribute("Opera");
		int chrome = (int) application.getAttribute("chrome");
		int Netscape = (int) application.getAttribute("Netscape");
		int firefox = (int) application.getAttribute("firefox");
		int UnKnownB = (int) application.getAttribute("UnKnownB");
	%>

	<form action="Main" method="POST" id="Refresh">
		<input type="hidden" name="action" value="ToAdmin" />
	</form>
	<input type="hidden" value="<%=num%>" id="sesId">
	<input type="hidden" value="<%=Windows%>" id="winId">
	<input type="hidden" value="<%=Mac%>" id="macId">
	<input type="hidden" value="<%=Unix%>" id="unixId">
	<input type="hidden" value="<%=Android%>" id="andId">
	<input type="hidden" value="<%=IPhone%>" id="iphoneId">
	<input type="hidden" value="<%=UnKnown%>" id="unknId">
	<input type="hidden" value="<%=IE%>" id="ieId">
	<input type="hidden" value="<%=safari%>" id="safId">
	<input type="hidden" value="<%=Opera%>" id="oprId">
	<input type="hidden" value="<%=chrome%>" id="chrId">
	<input type="hidden" value="<%=Netscape%>" id="netId">
	<input type="hidden" value="<%=firefox%>" id="firId">
	<input type="hidden" value="<%=UnKnownB%>" id="ubId">

	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<div class="logo">
						<h1>
							<a href="#" onclick="foo();return false;">ToDo Manager's
								Admin zone</a>
						</h1>
					</div>
				</div>
				<div class="col-md-5">
					<div class="row">
						<div class="col-lg-12">
							<div class="input-group form">

								<form action="Main" method="POST">
									<input type="hidden" name="action" value="ToLogin" /> <input
										type="submit" value="Go back" class="btn btn-primary" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page-content">
		<div class="content-box-large">
			<div class="panel-heading">
				<div class="panel-title">
					number of session is
					<%=num%></div>
				<div class="panel-options">
					<a href="#" onclick="foo();return false;" data-rel="collapse"><i
						class="glyphicon glyphicon-refresh"></i></a>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2">
						<div id="hero-bar" style="height: 230px;"></div>
					</div>
					<div class="col-md-3">
						<div id="hero-donut" style="height: 230px;"></div>
					</div>
					<div class="col-md-3">
						<div id="hero-donut2" style="height: 230px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp" />
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- jQuery UI -->
	<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.min.js"></script>

	<script src="Bootstrap/js/raphael-min.js"></script>
	<script src="Bootstrap/js/morris.min.js"></script>
	<script src="Bootstrap/js/stats.js"></script>
	<script type="text/javascript">
		function load() {
			setTimeout(foo, 10000);
		}

		function foo() {
			document.getElementById("Refresh").submit();
		}
	</script>

</body>
</html>
