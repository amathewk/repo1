<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logon</title>
</head>
<body>
<img src="images/MetricsDNA.png" alt="Metrics DNA"/>
<form method="post" action="login">
<h3>Account Login</h3>
<table>
<tr><td>Domain Name:</td><td><input type="text" name="domainname"/></td></tr>
<tr><td>Account Number:</td><td><input type="text" name="username"/></td></tr>
<tr><td>Password:</td><td><input type="password" name="userpassword"/></td></tr>
<tr><td colspan="2"><input type="submit" value="log on"/></td></tr>
<tr><td><a href="forgot.jsp">Forgot username or password?</a></td></tr>
</table>
</form>
</body>
</html>