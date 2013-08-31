<%@ page import="app.businessConnect.GlobalPreferences" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'globalPreferences.label', default: 'GlobalPreferences')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${globalPreferencesInstance}">
            <div class="errors">
                <g:renderErrors bean="${globalPreferencesInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="adDisplayCount"><g:message code="globalPreferences.adDisplayCount.label" default="Ad Display Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: globalPreferencesInstance, field: 'adDisplayCount', 'errors')}">
                                    <g:select name="adDisplayCount" from="${0..40}" value="${fieldValue(bean: globalPreferencesInstance, field: 'adDisplayCount')}"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mobileAdDisplayCount"><g:message code="globalPreferences.mobileAdDisplayCount.label" default="Mobile Ad Display Count" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: globalPreferencesInstance, field: 'mobileAdDisplayCount', 'errors')}">
                                    <g:select name="mobileAdDisplayCount" from="${0..40}" value="${fieldValue(bean: globalPreferencesInstance, field: 'mobileAdDisplayCount')}"  />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="administratorEmailAddress"><g:message code="globalPreferences.administratorEmailAddress.label" default="Administrator Email Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: globalPreferencesInstance, field: 'administratorEmailAddress', 'errors')}">
                                    <g:textField name="administratorEmailAddress" value="${globalPreferencesInstance?.administratorEmailAddress}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imageDisplayHeight"><g:message code="globalPreferences.imageDisplayHeight.label" default="Image Display Height" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: globalPreferencesInstance, field: 'imageDisplayHeight', 'errors')}">
                                    <g:textField name="imageDisplayHeight" value="${fieldValue(bean: globalPreferencesInstance, field: 'imageDisplayHeight')}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imageDisplayWidth"><g:message code="globalPreferences.imageDisplayWidth.label" default="Image Display Width" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: globalPreferencesInstance, field: 'imageDisplayWidth', 'errors')}">
                                    <g:textField name="imageDisplayWidth" value="${fieldValue(bean: globalPreferencesInstance, field: 'imageDisplayWidth')}" />
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