<%@ page import="net.followup.PersonalContact" %>
%{--<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'personalContact.label', default: 'PersonalContact')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-personalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>--}%
		<div id="create-personalContact" class="content scaffold-create" role="main">
			<h1>Personal Contact</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${personalContactInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${personalContactInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			%{--<g:form action="save" >--}%
            <g:formRemote name="createPersonalContactForm" url="[action:'save', controller:'PersonalContacts']"  update="layoutBody" >
                <g:hiddenField name="id" value="${personalContactInstance?.id}"></g:hiddenField>
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="Save" />
				</fieldset>
            </g:formRemote>
			%{--</g:form>--}%
		</div>
%{--
	</body>
</html>
--}%
