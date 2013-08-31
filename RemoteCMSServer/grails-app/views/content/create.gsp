
<%@ page import="net.soadna.remotecms.server.Content" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'content.label', default: 'Content')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="site" action="list">Site List</g:link></span>
            <span class="menuButton"><g:link class="list" action="listBySite" params="[siteId: contentInstance.site.id]"><g:message code="default.site.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contentInstance}">
            <div class="errors">
                <g:renderErrors bean="${contentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <h1>${contentInstance.site.domain}</h1>
            <g:form action="save" method="post" >
            	<g:hiddenField name="site.id" value="${contentInstance?.site?.id}"></g:hiddenField>
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="divId"><g:message code="content.divId.label" default="Div Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentInstance, field: 'divId', 'errors')}">
                                    <g:textField name="divId" value="${contentInstance?.divId}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text"><g:message code="content.text.label" default="Text" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contentInstance, field: 'text', 'errors')}">
                                    <!--  g:textArea name="text" value="${contentInstance?.text}" cols="100" rows="100"/ -->
                                    <resource:richTextEditor type="full" />
                                    <richui:richTextEditor name="text" value="${contentInstance?.text}" width="525" height="300" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
