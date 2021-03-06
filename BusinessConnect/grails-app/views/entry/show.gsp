
<%@ page import="app.businessConnect.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.id.label" default="Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "id")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.name.label" default="Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "name")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.streetAddress.label" default="Street Address" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "streetAddress")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.city.label" default="City" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "city")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.state.label" default="State" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "state")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.zip.label" default="Zip" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "zip")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.phoneNumber.label" default="Phone Number" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "phoneNumber")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.faxNumber.label" default="Fax Number" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "faxNumber")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.generalInfo.label" default="General Info" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "generalInfo")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.productsAndServices.label" default="Products And Services" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "productsAndServices")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.paymentTypesAccepted.label" default="Payment Types Accepted" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "paymentTypesAccepted")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.ad.label" default="Advertisement Image" /></td>
                            <td valign="top" class="value">
                            	<g:if test="${entryInstance.ad?.size() > 0}" >
	                            	<a href="${createLink(action:'showImage', controller:'entry', id:entryInstance.id)}" target="_blank" style="text-decoration: underline;">ad-image</a> 
                            	</g:if>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.adLink.label" default="Advertisement Link" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "adLink")}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.longitude.label" default="Longitude" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "longitude")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="entry.latitude.label" default="Latitude" /></td>
                            <td valign="top" class="value">${fieldValue(bean: entryInstance, field: "latitude")}</td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
	            <div class="buttons">
	                <g:form>
	                    <g:hiddenField name="id" value="${entryInstance?.id}" />
	                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
	                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	                </g:form>
	            </div>
			</sec:ifAnyGranted>
        </div>
    </body>
</html>
