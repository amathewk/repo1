
<%@ page import="net.followup.ProfessionalContact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'professionalContact.label', default: 'ProfessionalContact')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-professionalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-professionalContact" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list professionalContact">
			
				<g:if test="${professionalContactInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="professionalContact.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${professionalContactInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="professionalContact.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${professionalContactInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.organization}">
				<li class="fieldcontain">
					<span id="organization-label" class="property-label"><g:message code="professionalContact.organization.label" default="Organization" /></span>
					
						<span class="property-value" aria-labelledby="organization-label"><g:fieldValue bean="${professionalContactInstance}" field="organization"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.workPhoneNumber}">
				<li class="fieldcontain">
					<span id="workPhoneNumber-label" class="property-label"><g:message code="professionalContact.workPhoneNumber.label" default="Work Phone Number" /></span>
					
						<span class="property-value" aria-labelledby="workPhoneNumber-label"><g:fieldValue bean="${professionalContactInstance}" field="workPhoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.cellPhoneNumber}">
				<li class="fieldcontain">
					<span id="cellPhoneNumber-label" class="property-label"><g:message code="professionalContact.cellPhoneNumber.label" default="Cell Phone Number" /></span>
					
						<span class="property-value" aria-labelledby="cellPhoneNumber-label"><g:fieldValue bean="${professionalContactInstance}" field="cellPhoneNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.emailAddress}">
				<li class="fieldcontain">
					<span id="emailAddress-label" class="property-label"><g:message code="professionalContact.emailAddress.label" default="Email Address" /></span>
					
						<span class="property-value" aria-labelledby="emailAddress-label"><g:fieldValue bean="${professionalContactInstance}" field="emailAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${professionalContactInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="professionalContact.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${professionalContactInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${professionalContactInstance?.id}" />
					<g:link class="edit" action="edit" id="${professionalContactInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
