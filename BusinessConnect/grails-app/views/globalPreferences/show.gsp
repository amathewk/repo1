<%@ page import="app.businessConnect.GlobalPreferences" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'globalPreferences.label', default: 'GlobalPreferences')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <!-- 
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
             -->
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    	<%--
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.id.label" default="Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "id")}</td>
                        </tr>
                        --%>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.adDisplayCount.label" default="Ad Display Count" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "adDisplayCount")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.mobileAdDisplayCount.label" default="Mobile Ad Display Count" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "mobileAdDisplayCount")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.administratorEmailAddress.label" default="Administrator Email Address" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "administratorEmailAddress")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.imageDisplayHeight.label" default="Image Display Height" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "imageDisplayHeight")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="globalPreferences.imageDisplayWidth.label" default="Image Display Width" /></td>
                            <td valign="top" class="value">${fieldValue(bean: globalPreferencesInstance, field: "imageDisplayWidth")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${globalPreferencesInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <%--<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>--%>
                </g:form>
            </div>
        </div>
    </body>
</html>