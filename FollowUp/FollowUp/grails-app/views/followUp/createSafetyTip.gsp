<div id="create-safetyTip" class="content scaffold-create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${safetyTipInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${safetyTipInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:formRemote name="createSafetyTipForm" url="[action:'save', controller:'SafetyTips']"  update="layoutBody" >
        <g:hiddenField name="id" value="${safetyTipInstance?.id}"></g:hiddenField>
        <fieldset class="form">
            <div class="fieldcontain">
                <label for="text">
                    Safety Tip
                </label>
                <g:textArea name="text" value="${safetyTipInstance?.text}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="Save"/>
        </fieldset>
    </g:formRemote>
</div>
