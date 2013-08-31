<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usage Tracker</title>
</head>
<body>
	<br/>
	<br/>
	<br/>
		<center style="font-weight: bold;"> E-FORESEE </center>
	<br/>
	<br/>
	<div align="center">
		Please enter your client ID :
		<br/>
		<br/>
		<form method="POST" action="track.dna">
			<input type="text" size="20" name="clientId" />
			<input type="submit" value="Get Stats" />
		</form>
	</div>
	<br/>
	<br/>
	<center><i>demo id = 1001</i></center>
</body>
</html>