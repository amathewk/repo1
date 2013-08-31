<%@ page import="net.followup.ProfessionalContact" %>
%{--<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'professionalContact.label', default: 'ProfessionalContact')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-professionalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>--}%
<div id="create-professionalContact" class="content scaffold-create" role="main">
    <h1>Professional Contact</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${professionalContactInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${professionalContactInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
%{--<g:form action="save" >--}%
    <g:formRemote name="createProfessionalContactForm" url="[action:'save', controller:'ProfessionalContacts']"  update="layoutBody" >
        <g:hiddenField name="id" value="${professionalContactInstance?.id}"></g:hiddenField>
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
