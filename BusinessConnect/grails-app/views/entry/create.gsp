

<%@ page import="app.businessConnect.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <resource:include components="autoComplete, dateChooser" autoComplete="[skin: 'default']" />
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
            <g:hasErrors bean="${entryInstance}">
            <div class="errors">
                <g:renderErrors bean="${entryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save"  enctype="multipart/form-data" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                        	<sec:ifAnyGranted roles="ROLE_ADMIN">
	                        	<tr class="prop">
	                                <td valign="top" class="name">
	                                  <label for="member"><g:message code="entry.member.label" default="Member" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'member', 'errors')}">
	                                    <g:select name="member.username" from="${app.businessConnect.User.nonAdminUsers()}" optionValue="username" optionKey="username" value="${entryInstance?.member?.username}" noSelection="['null': '']" />
	                                </td>
	                            </tr>
                        	</sec:ifAnyGranted>
                        	
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="entry.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${entryInstance?.name}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="streetAddress"><g:message code="entry.streetAddress.label" default="Street Address" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'streetAddress', 'errors')}">
                                    <g:textField name="streetAddress" value="${entryInstance?.streetAddress}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city"><g:message code="entry.city.label" default="City" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'city', 'errors')}">
                                    <g:textField name="city" value="${entryInstance?.city}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state"><g:message code="entry.state.label" default="State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'state', 'errors')}">
                                	<g:select name="state" from="['AL','AK','AS','AZ','AR','CA','CO','CT','DE','DC','FL','GA','GU','HI','ID','IL','IN','IA','KS','KY','LA','ME','MD','MH','MA','MI','FM','MN','MS','MO','MT','NE','NV','NH','NJ','NM','NY','NC','ND','MP','OH','OK','OR','PW','PA','PR','RI','SC','SD','TN','TX','UT','VT','VA','VI','WA','WV','WI','WY']"></g:select><%--
                                    <g:textField name="state" value="${entryInstance?.state}"  />
                                    <richui:autoComplete name="name" action="${createLinkTo('dir': 'entry/states')}" />
                                --%></td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zip"><g:message code="entry.zip.label" default="Zip" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'zip', 'errors')}">
                                    <g:textField name="zip" value="${entryInstance?.zip}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber"><g:message code="entry.phoneNumber.label" default="Phone Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'phoneNumber', 'errors')}">
                                    <g:textField name="phoneNumber" value="${entryInstance?.phoneNumber}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="faxNumber"><g:message code="entry.faxNumber.label" default="Fax Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'faxNumber', 'errors')}">
                                    <g:textField name="faxNumber" value="${entryInstance?.faxNumber}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="generalInfo"><g:message code="entry.generalInfo.label" default="General Info" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'generalInfo', 'errors')}">
                                    <g:textField name="generalInfo" value="${entryInstance?.generalInfo}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productsAndServices"><g:message code="entry.productsAndServices.label" default="Products And Services" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'productsAndServices', 'errors')}">
                                    <g:textField name="productsAndServices" value="${entryInstance?.productsAndServices}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentTypesAccepted"><g:message code="entry.paymentTypesAccepted.label" default="Payment Types Accepted" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'paymentTypesAccepted', 'errors')}">
                                    <g:textField name="paymentTypesAccepted" value="${entryInstance?.paymentTypesAccepted}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ad"><g:message code="entry.ad.label" default="Advertisement Image Upload" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'ad', 'errors')}">
		                            <input id="ad" name="ad" type="file" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="adLink"><g:message code="entry.adLink.label" default="Advertisement Link" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'adLink', 'errors')}">
                                    <g:textField name="adLink" value="${entryInstance?.adLink}" />
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
