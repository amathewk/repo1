
<%@ page import="net.soadna.remotecms.server.Site" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'site.label', default: 'Site')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'site.id.label', default: 'Id')}" />
                        
                            <th><g:message code="site.customer.label" default="Customer" /></th>
                   	    
                            <g:sortableColumn property="domain" title="${message(code: 'site.domain.label', default: 'Domain')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${siteInstanceList}" status="i" var="siteInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="select" id="${siteInstance.id}">${fieldValue(bean: siteInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: siteInstance, field: "customer")}</td>
                        
                            <td>${fieldValue(bean: siteInstance, field: "domain")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${siteInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
