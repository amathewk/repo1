
<%@ page import="net.soadna.remotecms.server.Content" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'content.label', default: 'Content')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="site" action="list">Site List</g:link></span>
            <span class="menuButton"><g:link class="create" action="createForSite" params="[siteId:site.id]">New Content</g:link></span>
        </div>
        <div class="body">
        	<div style="padding: 10px">
	            SITE:<g:link controller="site" title="Switch Site"><span class="title" style="padding: 0px 15px">${site.domain}</span></s></g:link>
        	</div>
            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <div>
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="divId" title="${message(code: 'content.divId.label', default: 'Div Id')}" />
                            <g:sortableColumn property="text" title="${message(code: 'content.text.label', default: 'Text')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contentInstanceList}" status="i" var="contentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td class="listName">
	                            	<g:link action="edit" id="${contentInstance.id}">${fieldValue(bean: contentInstance, field: "divId")}</g:link>
	                            	<br/>
	                            	<br/>
	                            	<span style="margin-top:25px">
		                            	<g:link action="edit" id="${contentInstance.id}" class="edit" title="edit">&nbsp;</g:link>
		                            	<g:link action="delete" id="${contentInstance.id}" class="delete" title="delete" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >&nbsp;</g:link>
										<modalbox:createLink action="htmlSnippet" id="${contentInstance.id}" title="How to insert this content" width="500"><span class="info">&nbsp;</span></modalbox:createLink>
	                            	</span>
	                            </td>
	                            <td>
	                            	<g:link action="edit" id="${contentInstance.id}">${fieldValue(bean: contentInstance, field: "text")}</g:link>
	                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contentInstanceTotal}" id="${site.id}" />
            </div>
        </div>
    </body>
</html>
