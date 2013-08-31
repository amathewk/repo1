
<%@ page import="app.businessConnect.Entry" %>

<div class="body">
	<h1>Results for ${session.originGeoCodeResult.correctedAddress}</h1>
	<g:if test="${flash.message}">
		<div class="message">
		${flash.message}
		</div>
	</g:if>
	<div class="list" id="grid">
		<center>
			<div class="paginateButtons" style="border-left:none; border-right:none"><g:paginate total="${entryInstanceTotal}" max="5" params="[range : searchCommand.range, address : searchCommand.address]" /></div>
		</center>
		<table class="ajax" style="border:none; width:600px; margin:20px">
			<tbody>
				<g:each in="${entryInstanceList}" status="i" var="entryInstance">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
						<td style="border-left: none">
							<g:render template="searchResultEntryTemplate" bean="${entryInstance}" />
						</td>
		
						<td style="border: none;">
							<a style="color:blue; font-weight: normal;" target="_blank" href="http://maps.google.com/maps?saddr=${session.originGeoCodeResult.correctedAddress}&daddr=${entryInstance.correctedAddress}">
								directions [${entryInstance.distanceFromAddress} miles] 
							</a>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<center>
			<div class="paginateButtons" style="border-left:none; border-right:none"><g:paginate total="${entryInstanceTotal}" max="5" params="[range : searchCommand.range, address : searchCommand.address]" /></div>
		</center>
	</div>
</div>