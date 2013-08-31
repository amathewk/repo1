
<%@ page import="net.followup.PersonalContact" %>
%{--
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'personalContact.label', default: 'PersonalContact')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-personalContact" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
--}%
<div style="text-align: center;padding:5px">
    <h3 style="padding: 5px">Personal Contacts</h3>
    <g:include view="/menu/personalContactsMenu.gsp"/>
</div>

		<div id="personalContactsContent" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'personalContact.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'personalContact.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="workPhoneNumber" title="${message(code: 'personalContact.workPhoneNumber.label', default: 'Work Phone Number')}" />
					
						<g:sortableColumn property="cellPhoneNumber" title="${message(code: 'personalContact.cellPhoneNumber.label', default: 'Cell Phone Number')}" />
					
						<g:sortableColumn property="emailAddress" title="${message(code: 'personalContact.emailAddress.label', default: 'Email Address')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${personalContactInstanceList}" status="i" var="personalContactInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td>${fieldValue(bean: personalContactInstance, field: "firstName")}</td>
					
						<td><g:remoteLink action="edit" update="personalContactsContent" params="[id:personalContactInstance.id]" >${fieldValue(bean: personalContactInstance, field: "lastName")}</g:remoteLink>   </td>
					
						<td>${fieldValue(bean: personalContactInstance, field: "workPhoneNumber")}</td>
					
						<td>${fieldValue(bean: personalContactInstance, field: "cellPhoneNumber")}</td>
					
						<td>${fieldValue(bean: personalContactInstance, field: "emailAddress")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${personalContactInstanceTotal}" />
			</div>
		</div>
%{--
	</body>
</html>
--}%
