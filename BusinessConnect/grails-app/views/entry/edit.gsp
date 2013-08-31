

<%@ page import="app.businessConnect.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${entryInstance}">
            <div class="errors">
                <g:renderErrors bean="${entryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" enctype="multipart/form-data" >
                <g:hiddenField name="id" value="${entryInstance?.id}" />
                <g:hiddenField name="version" value="${entryInstance?.version}" />
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
                                    <g:textField name="state" value="${entryInstance?.state}" />
                                </td>
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
		                            <g:if test="${entryInstance.ad?.size() > 0}" >
	                            		<a href="${createLink(action:'showImage', controller:'entry', id:entryInstance.id)}" target="_blank" style="text-decoration: underline;">ad-image</a> 
                            		</g:if>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="adLink"><g:message code="entry.adLink.label" default="Ad Link" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'adLink', 'errors')}">
                                    <g:textField name="adLink" value="${entryInstance?.adLink}" />
                                </td>
                            </tr>
                        
                           <!-- 
                             <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="longitude"><g:message code="entry.longitude.label" default="Longitude" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'longitude', 'errors')}">
                                    <g:textField name="longitude" value="${fieldValue(bean: entryInstance, field: 'longitude')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="latitude"><g:message code="entry.latitude.label" default="Latitude" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: entryInstance, field: 'latitude', 'errors')}">
                                    <g:textField name="latitude" value="${fieldValue(bean: entryInstance, field: 'latitude')}" />
                                </td>
                            </tr>
                             -->
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
