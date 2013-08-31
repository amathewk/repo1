<%@ page import="app.businessConnect.GlobalPreferences" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'globalPreferences.label', default: 'GlobalPreferences')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <%--<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        	--%>
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
                            <%--<g:sortableColumn property="id" title="${message(code: 'globalPreferences.id.label', default: 'Id')}" />
                            --%>
                            <g:sortableColumn property="adDisplayCount" title="${message(code: 'globalPreferences.adDisplayCount.label', default: 'Ad Display Count')}" />
                            <g:sortableColumn property="mobileAdDisplayCount" title="${message(code: 'globalPreferences.mobileAdDisplayCount.label', default: 'Mobile Ad Display Count')}" />
                            <g:sortableColumn property="administratorEmailAddress" title="${message(code: 'globalPreferences.administratorEmailAddress.label', default: 'Administrator Email Address')}" />
                            <g:sortableColumn property="imageDisplayHeight" title="${message(code: 'globalPreferences.imageDisplayHeight.label', default: 'Image Display Height')}" />
                            <g:sortableColumn property="imageDisplayWidth" title="${message(code: 'globalPreferences.imageDisplayWidth.label', default: 'Image Display Width')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${globalPreferencesInstanceList}" status="i" var="globalPreferencesInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <%--<td><g:link action="show" id="${globalPreferencesInstance.id}">${fieldValue(bean: globalPreferencesInstance, field: "id")}</g:link></td>
                            --%>
                            <td>${fieldValue(bean: globalPreferencesInstance, field: "adDisplayCount")}</td>
                            <td>${fieldValue(bean: globalPreferencesInstance, field: "mobileAdDisplayCount")}</td>
                            <td>${fieldValue(bean: globalPreferencesInstance, field: "administratorEmailAddress")}</td>
                            <td>${fieldValue(bean: globalPreferencesInstance, field: "imageDisplayHeight")}</td>
                            <td>${fieldValue(bean: globalPreferencesInstance, field: "imageDisplayWidth")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${globalPreferencesInstanceTotal}" />
            </div>
        </div>
    </body>
</html>