<%@ page import="net.soadna.remotecms.server.Tag"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
	<title>pick</title>
	<resource:autoComplete skin="default" />
</head>
<body>

<g:form>
    <richui:autoComplete name="name" action="${createLinkTo('dir': 'tag/searchAJAX')}" typeAhead="true" />
</g:form>


</body>
</html>