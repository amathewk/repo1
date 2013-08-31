<div id="list-patient" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="['Patient']" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="patientId" title="${message(code: 'patient.patientId.label', default: 'Patient Id')}" />

            <g:sortableColumn property="firstName" title="${message(code: 'patient.firstName.label', default: 'First Name')}" />

            <g:sortableColumn property="lastName" title="${message(code: 'patient.lastName.label', default: 'Last Name')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${patientInstanceList}" status="i" var="patientInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="select" id="${patientInstance.id}">${fieldValue(bean: patientInstance, field: "patientId")}</g:link></td>

                <td>${fieldValue(bean: patientInstance, field: "firstName")}</td>

                <td>${fieldValue(bean: patientInstance, field: "lastName")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${patientInstanceTotal}" />
    </div>
</div>