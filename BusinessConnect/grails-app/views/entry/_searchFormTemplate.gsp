
<%@ page import="app.businessConnect.Entry"%>

<g:if test="${flash.message}">
	<div class="message">
	${flash.message}
	</div>
</g:if> <g:hasErrors bean="${searchCommand}">
	<div class="errors"><g:renderErrors bean="${searchCommand}"
		as="list" /></div>
</g:hasErrors> 
<g:formRemote url="[controller: 'entry', action: 'search']"
	name="search" update="${[success: 'results']}" style="width: 400px">
	<div class="dialog">
	<table>
		<tbody>

			<tr class="prop">
				<td valign="top" class="name"
					style="text-align: right; vertical-align: middle;"><label
					for="address">Enter an address or zip code : </label></td>
				<td valign="top"
					class="value ${hasErrors(bean: seachCriteria, field: 'address', 'errors')}">
				<g:textField name="address" value="${seachCriteria?.address}" /></td>
			</tr>
			<tr>
				<td valign="top" class="name" style="text-align: right; vertical-align: middle;">
					<label for="range">Range : </label>
				</td>
				<td valign="top" class="value ${hasErrors(bean: seachCriteria, field: 'range', 'errors')}"><g:select
					name="range" from="${[5,10,15,20,25,50,75,100,150,200,250,300,500]}" value="${seachCriteria?.range}" />
				&nbsp;miles
				</td>
			</tr>

		</tbody>
	</table>
	</div>
	<div class="buttons"><span class="button"><g:submitButton
		name="search" class="save" value="Search" update="results" /></span></div>
</g:formRemote>
