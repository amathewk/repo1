
<%@ page import="net.followup.ProfessionalContact" %>
%{--
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'professionalContact.label', default: 'ProfessionalContact')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-professionalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
--}%
<div style="text-align: center;padding:5px">
    <h3 style="padding: 5px">Professional Contacts</h3>
    <g:include view="/menu/professionalContactsMenu.gsp"/>
</div>

<div id="professionalContactsContent" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="firstName" title="${message(code: 'professionalContact.firstName.label', default: 'First Name')}" />

            <g:sortableColumn property="lastName" title="${message(code: 'professionalContact.lastName.label', default: 'Last Name')}" />

            <g:sortableColumn property="organization" title="${message(code: 'professionalContact.organization.label', default: 'Organization')}" />

            <g:sortableColumn property="workPhoneNumber" title="${message(code: 'professionalContact.workPhoneNumber.label', default: 'Work Phone Number')}" />

            <g:sortableColumn property="cellPhoneNumber" title="${message(code: 'professionalContact.cellPhoneNumber.label', default: 'Cell Phone Number')}" />

            <g:sortableColumn property="emailAddress" title="${message(code: 'professionalContact.emailAddress.label', default: 'Email Address')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${professionalContactInstanceList}" status="i" var="professionalContactInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: professionalContactInstance, field: "firstName")}</td>

                <td><g:remoteLink action="edit" update="professionalContactsContent" params="[id:professionalContactInstance.id]" >${fieldValue(bean: professionalContactInstance, field: "lastName")}</g:remoteLink>   </td>

                <td>${fieldValue(bean: professionalContactInstance, field: "organization")}</td>

                <td>${fieldValue(bean: professionalContactInstance, field: "workPhoneNumber")}</td>

                <td>${fieldValue(bean: professionalContactInstance, field: "cellPhoneNumber")}</td>

                <td>${fieldValue(bean: professionalContactInstance, field: "emailAddress")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${professionalContactInstanceTotal}" />
    </div>
</div>
%{--
	</body>
</html>
--}%
