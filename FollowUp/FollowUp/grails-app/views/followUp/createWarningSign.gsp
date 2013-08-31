<div id="create-warningSign" class="content scaffold-create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${warningSignInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${warningSignInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:formRemote name="createWarningSignForm" url="[action:'save', controller:'WarningSigns']"  update="layoutBody" >
        <g:hiddenField name="id" value="${warningSignInstance?.id}"></g:hiddenField>
        <fieldset class="form">
            <div class="fieldcontain">
                <label for="text">
                    Warning Sign
                </label>
                <g:textArea name="text" value="${warningSignInstance?.text}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="Save"/>
        </fieldset>
    </g:formRemote>
</div>
