<%@ page import="net.followup.Patient" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:javascript library="jquery" />
    <title>Search Form</title>
</head>

<body>

%{--<g:form name="searchPatients" url="[controller: 'search', action:'patient1']" update="result" >
    <g:textField name="patientId" />
    <g:textField name="firstName" />
    <g:textField name="lastName" />
    <g:submitButton name="search" value="Search"/>
</g:form>
--}%


<g:formRemote name="searchPatients" url="[controller: 'search', action: 'patient']" update="result">

    <div style="padding-left: 20px">
        <div style="padding-top: 20px">
            <label for="patientId">Patient ID</label>
            <g:textField name="patientId"/>


            <div style="padding: 20px">OR</div>

            <label for="firstName">First Name</label>
            <g:textField name="firstName"/>

            <label style="padding-left: 10px" for="lastName">Last Name</label>
            <g:textField name="lastName"/>
        </div>
        <div style="padding-top: 20px">
            <g:submitButton name="search" value="Search"/>
        </div>
    </div>
</g:formRemote>

<div id="result"/>

</body>
<head>
    <meta name="layout" content="main">
    %{--<g:set var="entityName" value="${message(code: 'patient.label', default: 'Patient')}" />--}%
    %{--<title><g:message code="default.list.label" args="[entityName]" /></title>--}%
</head>
</html>