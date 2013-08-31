<div id="create-selfCoping" class="content scaffold-create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${selfCopingInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${selfCopingInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:formRemote name="createSelfCopingForm" url="[action:'save', controller:'SelfCopings']"  update="layoutBody" >
        <g:hiddenField name="id" value="${selfCopingInstance?.id}"></g:hiddenField>
        <fieldset class="form">
            <div class="fieldcontain">
                <label for="text">
                    Self Coping
                </label>
                <g:textArea name="text" value="${selfCopingInstance?.text}"/>
            </div>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="Save"/>
        </fieldset>
    </g:formRemote>
</div>
