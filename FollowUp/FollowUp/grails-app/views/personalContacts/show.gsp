
<%@ page import="net.followup.PersonalContact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'personalContact.label', default: 'PersonalContact')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-personalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-personalContact" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list personalContact">
			
				<g:if test="${personalContactInstance?.patient}">
				<li class="fieldcontain">
					<span id="patient-label" class="property-label"><g:message code="personalContact.patient.label" default="Patient" /></span>
					
						<span class="property-value" aria-labelledby="patient-label"><g:link controller="patient" action="show" id="${personalContactInstance?.patient?.id}">${personalContactInstance?.patient?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="personalContact.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${personalContactInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="personalContact.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${personalContactInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.workPhoneNumber}">
				<li class="fieldcontain">
					<span id="workPhoneNumber-label" class="property-label"><g:message code="personalContact.workPhoneNumber.label" default="Work Phone Number" /></span>
					
						<span class="property-value" aria-labelledby="workPhoneNumber-label"><g:fieldValue bean="${personalContactInstance}" field="workPhoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.cellPhoneNumber}">
				<li class="fieldcontain">
					<span id="cellPhoneNumber-label" class="property-label"><g:message code="personalContact.cellPhoneNumber.label" default="Cell Phone Number" /></span>
					
						<span class="property-value" aria-labelledby="cellPhoneNumber-label"><g:fieldValue bean="${personalContactInstance}" field="cellPhoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.emailAddress}">
				<li class="fieldcontain">
					<span id="emailAddress-label" class="property-label"><g:message code="personalContact.emailAddress.label" default="Email Address" /></span>
					
						<span class="property-value" aria-labelledby="emailAddress-label"><g:fieldValue bean="${personalContactInstance}" field="emailAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personalContactInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="personalContact.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${personalContactInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${personalContactInstance?.id}" />
					<g:link class="edit" action="edit" id="${personalContactInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
