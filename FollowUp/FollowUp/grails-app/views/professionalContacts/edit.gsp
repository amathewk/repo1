<%@ page import="net.followup.ProfessionalContact" %>
<div id="edit-professionalContact" class="content scaffold-edit" role="main">
    <h1>Edit</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${professionalContactInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${professionalContactInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:formRemote url="[controller:'ProfessionalContacts']"  update="layoutBody"  name="updateProfessionalContactForm">
        <g:hiddenField name="id" value="${professionalContactInstance?.id}"/>
        <g:hiddenField name="version" value="${professionalContactInstance?.version}"/>
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitToRemote class="save" action="update" update="layoutBody"
                              value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            <g:submitToRemote class="delete" action="delete" update="layoutBody"
                              value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate=""
                              onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:formRemote>
</div>