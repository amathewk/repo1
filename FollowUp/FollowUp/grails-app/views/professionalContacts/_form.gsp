<%@ page import="net.followup.ProfessionalContact" %>



<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="professionalContact.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${professionalContactInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="professionalContact.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${professionalContactInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'organization', 'error')} ">
	<label for="organization">
		<g:message code="professionalContact.organization.label" default="Organization" />
		
	</label>
	<g:textField name="organization" value="${professionalContactInstance?.organization}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'workPhoneNumber', 'error')} ">
	<label for="workPhoneNumber">
		<g:message code="professionalContact.workPhoneNumber.label" default="Work Phone Number" />
		
	</label>
	<g:textField name="workPhoneNumber" value="${professionalContactInstance?.workPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'cellPhoneNumber', 'error')} ">
	<label for="cellPhoneNumber">
		<g:message code="professionalContact.cellPhoneNumber.label" default="Cell Phone Number" />
		
	</label>
	<g:textField name="cellPhoneNumber" value="${professionalContactInstance?.cellPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="professionalContact.emailAddress.label" default="Email Address" />
		
	</label>
	<g:field type="email" name="emailAddress" value="${professionalContactInstance?.emailAddress}"/>
</div>
<fieldset class="embedded"><legend><g:message code="professionalContact.address.label" default="Address" /></legend>
<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.address1', 'error')} ">
	<label for="address.address1">
		<g:message code="professionalContact.address.address1.label" default="Address1" />
		
	</label>
	<g:textField name="address.address1" value="${professionalContactInstance?.address?.address1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.address2', 'error')} ">
	<label for="address.address2">
		<g:message code="professionalContact.address.address2.label" default="Address2" />
		
	</label>
	<g:textField name="address.address2" value="${professionalContactInstance?.address?.address2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.city', 'error')} ">
	<label for="address.city">
		<g:message code="professionalContact.address.city.label" default="City" />
		
	</label>
	<g:textField name="address.city" value="${professionalContactInstance?.address?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.country', 'error')} ">
	<label for="address.country">
		<g:message code="professionalContact.address.country.label" default="Country" />
		
	</label>
	<g:textField name="address.country" value="${professionalContactInstance?.address?.country}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.state', 'error')} ">
	<label for="address.state">
		<g:message code="professionalContact.address.state.label" default="State" />
		
	</label>
	<g:textField name="address.state" value="${professionalContactInstance?.address?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professionalContactInstance, field: 'address.zip', 'error')} ">
	<label for="address.zip">
		<g:message code="professionalContact.address.zip.label" default="Zip" />
		
	</label>
	<g:textField name="address.zip" value="${professionalContactInstance?.address?.zip}"/>
</div>
</fieldset>
