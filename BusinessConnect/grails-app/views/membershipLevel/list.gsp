
<%@ page import="app.businessConnect.MembershipLevel" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'membershipLevel.label', default: 'MembershipLevel')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                        
                            <%--
                            <g:sortableColumn property="id" title="${message(code: 'membershipLevel.id.label', default: 'Id')}" />
                            --%>
                            <g:sortableColumn property="name" title="${message(code: 'membershipLevel.name.label', default: 'Name')}" />
                            <g:sortableColumn property="entriesLimit" title="${message(code: 'membershipLevel.entriesLimit.label', default: 'Entries Limit')}" />
                            <g:sortableColumn property="displayAdsOnWeb" title="${message(code: 'membershipLevel.displayAdsOnWeb.label', default: 'Display Ads On Web')}" />
                            <g:sortableColumn property="displayAdsOnMobile" title="${message(code: 'membershipLevel.displayAdsOnMobile.label', default: 'Display Ads On Mobile')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${membershipLevelInstanceList}" status="i" var="membershipLevelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<%--
                            <td><g:link action="show" id="${membershipLevelInstance.id}">${fieldValue(bean: membershipLevelInstance, field: "id")}</g:link></td>
                        	 --%>
                            <td><g:link action="show" id="${membershipLevelInstance.id}">${fieldValue(bean: membershipLevelInstance, field: "name")}</g:link></td>
                            <td>${fieldValue(bean: membershipLevelInstance, field: "entriesLimit")}</td>
                            <td><g:formatBoolean boolean="${membershipLevelInstance.displayAdsOnWeb}" /></td>
                            <td><g:formatBoolean boolean="${membershipLevelInstance.displayAdsOnMobile}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${membershipLevelInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
