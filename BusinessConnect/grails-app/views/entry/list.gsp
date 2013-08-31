<%@ page import="app.businessConnect.Entry" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <sec:ifAnyGranted roles="ROLE_MEMBER, ROLE_ADMIN">
	            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
	        </sec:ifAnyGranted>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                        	<%--
                            <g:sortableColumn property="id" title="${message(code: 'entry.id.label', default: 'Id')}" />
                        	 --%>
                        
                        	<sec:ifAnyGranted roles="ROLE_ADMIN">
	                            <g:sortableColumn property="member.username" title="${message(code: 'entry.member.label', default: 'Member')}" />
    						</sec:ifAnyGranted>
    			                        
                            <g:sortableColumn property="name" title="${message(code: 'entry.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="streetAddress" title="${message(code: 'entry.streetAddress.label', default: 'Street Address')}" />
                        
                            <g:sortableColumn property="city" title="${message(code: 'entry.city.label', default: 'City')}" />
                        
                            <g:sortableColumn property="state" title="${message(code: 'entry.state.label', default: 'State')}" />
                        
                            <g:sortableColumn property="zip" title="${message(code: 'entry.zip.label', default: 'Zip')}" />
                            
                            <g:sortableColumn property="correctedAddress" title="${message(code: 'entry.correctedAddress.label', default: 'Formatted Address')}" />
                        
                            <th>${message(code: 'entry.ad.label', default: 'Ad Image')}</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${entryInstanceList}" status="i" var="entryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
		
							<%--                        
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "id")}</g:link></td>
                        	 --%>
                        	
                        	<sec:ifAnyGranted roles="ROLE_ADMIN">
	                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "member.username")}</g:link></td>
                        	</sec:ifAnyGranted>
                            
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "name")}</g:link></td>
                        
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "streetAddress")}</g:link></td>
                        
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "city")}</g:link></td>
                        
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "state")}</g:link></td>
                        
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "zip")}</g:link></td>
                            
                            <td><g:link action="show" id="${entryInstance.id}">${fieldValue(bean: entryInstance, field: "correctedAddress")}</g:link></td>
                        
                            <td>
                            	<g:if test="${entryInstance.ad?.size() > 0}" >
	                            	<a href="${createLink(action:'showImage', controller:'entry', id:entryInstance.id)}" target="_blank" style="text-decoration: underline;">ad-image</a> 
                            	</g:if>
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${entryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
