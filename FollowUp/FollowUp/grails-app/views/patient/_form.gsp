<%@ page import="net.followup.Patient" %>



<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'patientId', 'error')} ">
	<label for="patientId">
		<g:message code="patient.patientId.label" default="Patient Id" />
		
	</label>
	<g:textField name="patientId" value="${patientInstance?.patientId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="patient.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${patientInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: patientInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="patient.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${patientInstance?.lastName}"/>
</div>

