<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	    <%--
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.css" />
		<script src="http://code.jquery.com/jquery-1.6.2.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0b2/jquery.mobile-1.0b2.min.js"></script>
	    --%>
	    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.css" />
		<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.js"></script>
		<script src="${resource(dir:'js/jquery',file:'jquery.mobile.pagination.js')}"></script>
	    <link rel="stylesheet" href="${resource(dir:'css/jquery',file:'jquery.mobile.pagination.css')}" />
	</head>
	<body style="font: 75% Lucida Grande, Trebuchet MS;margin:0"><%--
		<ul data-role="listview" data-theme="g">
			<g:each in="${entryInstanceList}" var="entry" >
				<li>
					
					 <a href="${createLink(action: 'show', controller: 'entry', id:entry.id) }">${entry.name} ${entry.distanceFromAddress} [${entry.durationFromAddress}] </a>
					  	
					<a href="http://maps.google.com/maps?saddr=${lat},${lng}&daddr=${entry.correctedAddress}">${entry.name} [${entry.distanceFromAddress} miles] </a>
				</li>
			</g:each>
		</ul>
		<ul data-role="pagination">
			<g:if test="${prevOffset != ''}">
				<li class="ui-pagination-prev"><a href="${createLink(controller:'entry', action:'searchM', params:[max: max, offset: prevOffset, lat: lat, lng: lng])}">Prev</a></li>
			</g:if>
			<g:if test="${nextOffset != ''}">
				<li class="ui-pagination-next"><a href="${createLink(controller:'entry', action:'searchM', params:[max: max, offset: nextOffset, lat: lat, lng: lng])}">Next</a></li>
			</g:if>	
		</ul>
		--%><br/>
	<%--
	   <g:render template="/entry/searchFormTemplate"></g:render>
	--%>
		<%@ page import="app.businessConnect.Entry"%>
		<g:if test="${flash.message}">
			<div class="message">
			<br/>${flash.message}
			<br/>${searchCommand?.address}
			<br/>${message}
			<br/>${seachCriteria?.address}
			</div>
		</g:if> 
		<g:hasErrors bean="${searchCommand}">
			<div class="errors">
				<g:renderErrors bean="${searchCommand}" as="list" />
			</div>
		</g:hasErrors> 
		<g:form url="[controller: 'entry', action: 'searchMByAddress']"
			name="search" update="${[success: 'results']}" method="get" style="width: 400px">
			<div class="dialog">
			<table>
				<tbody>
					<tr class="prop">
						<td valign="top" class="name" style="text-align: right; vertical-align: middle;">
							<label for="address">Enter an address or zip code : </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean: seachCriteria, field: 'address', 'errors')}">
							<g:textField name="address" value="${seachCriteria?.address}" />
						</td>
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
		<div class="buttons">
			<span class="button">
				<g:submitButton name="search" class="save" value="Search" />
			</span>
		</div>
		</g:form>
		<g:each in="${adEntries}" var="ad">
			<a href="${ad.adLink}" target="_blank" style="padding : 10px 10px 10px 10px" >
				${ad.adLine}
			</a>
		</g:each>
	</body>
</html>