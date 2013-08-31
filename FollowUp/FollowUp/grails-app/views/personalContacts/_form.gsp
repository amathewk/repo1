<%@ page import="net.followup.PersonalContact" %>


<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="personalContact.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${personalContactInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="personalContact.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${personalContactInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'workPhoneNumber', 'error')} ">
	<label for="workPhoneNumber">
		<g:message code="personalContact.workPhoneNumber.label" default="Work Phone Number" />
		
	</label>
	<g:textField name="workPhoneNumber" value="${personalContactInstance?.workPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'cellPhoneNumber', 'error')} ">
	<label for="cellPhoneNumber">
		<g:message code="personalContact.cellPhoneNumber.label" default="Cell Phone Number" />
		
	</label>
	<g:textField name="cellPhoneNumber" value="${personalContactInstance?.cellPhoneNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="personalContact.emailAddress.label" default="Email Address" />
		
	</label>
	<g:field type="email" name="emailAddress" value="${personalContactInstance?.emailAddress}"/>
</div>
<fieldset class="embedded"><legend><g:message code="personalContact.address.label" default="Address" /></legend>
<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.address1', 'error')} ">
	<label for="address.address1">
		<g:message code="personalContact.address.address1.label" default="Address1" />
		
	</label>
	<g:textField name="address.address1" value="${personalContactInstance?.address?.address1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.address2', 'error')} ">
	<label for="address.address2">
		<g:message code="personalContact.address.address2.label" default="Address2" />
		
	</label>
	<g:textField name="address.address2" value="${personalContactInstance?.address?.address2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.city', 'error')} ">
	<label for="address.city">
		<g:message code="personalContact.address.city.label" default="City" />
		
	</label>
	<g:textField name="address.city" value="${personalContactInstance?.address?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.country', 'error')} ">
	<label for="address.country">
		<g:message code="personalContact.address.country.label" default="Country" />
		
	</label>
	<g:textField name="address.country" value="${personalContactInstance?.address?.country}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.state', 'error')} ">
	<label for="address.state">
		<g:message code="personalContact.address.state.label" default="State" />
		
	</label>
	<g:textField name="address.state" value="${personalContactInstance?.address?.state}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personalContactInstance, field: 'address.zip', 'error')} ">
	<label for="address.zip">
		<g:message code="personalContact.address.zip.label" default="Zip" />
		
	</label>
	<g:textField name="address.zip" value="${personalContactInstance?.address?.zip}"/>
</div>
</fieldset>
