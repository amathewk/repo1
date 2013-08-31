<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="${createLink(controller: 'search', action: 'patientForm')}"><img src="${resource(dir: 'images', file: 'followUpLogo.png')}" alt="Grails"/></a>
		%{--<div id="grailsLogo" role="banner"><a href="/FollowUp">Follow Up Plan</a>--}%
            <g:if test="${session['patientId']}">
                <div style="float: right; padding: 10px">
                    <div>
                        <div style="float: left">
                            <div>ID : ${session['patientId']}</div>
                            <div>${session['lastName']}, ${session['firstName']}</div>
                        </div>
                        <div style="float: right;font-size: small;font-style: italic"><a href="${createLink(controller: 'search', action: 'patientForm')}">change</a>
                            <span class="menuButton" style="padding-left: 30px"><g:remoteLink name="appointments" update="layoutBody" controller="appointments" action="list"><button>Appointments</button></g:remoteLink> </span>
                        </div>
                    </div>
                </div>
            </g:if>
            <div style="float: none; ">
                <g:include view="/followUp/menu.gsp"/>
            </div>
        </div>
        <div id="layoutBody">
            <g:layoutBody/>
        </div>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>


		<r:layoutResources />
	</body>
</html>
