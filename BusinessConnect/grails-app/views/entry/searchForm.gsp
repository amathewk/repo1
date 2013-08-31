<%@ page import="app.businessConnect.Entry" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'entry.label', default: 'Entry')}" />
        <title>Search</title>
        
        <g:javascript library="jquery"></g:javascript>
        <g:javascript>
			$(document).ready(function() {
				$('.spinner')
 				    .hide()  // hide it initially
				    .ajaxStart(function() {
				        $(this).show();
				    })
				    .ajaxStop(function() {
				        $(this).hide();
				    })
				;
			    setupGridAjax();
			});
			 
			// Turn all sorting and paging links into ajax requests for the grid
			function setupGridAjax() {
			    $("#results").find(".paginateButtons a,th.sortable a").live('click', function(event) {
			        event.preventDefault();
			        var url = $(this).attr('href');
			 
			        var results = $(this).parents("#results");
			 
			        $.ajax({
			            type: 'GET',
			            url: url,
			            success: function(data) {
			                $(results).fadeOut('fast', function() {$(this).html(data).fadeIn('slow');});
			            }
			        })
			    });
			}
        </g:javascript>
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MEMBER">
            	<span class="menuButton"><g:link class="list" controller="entry" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
			</sec:ifAnyGranted> 
            <sec:ifAnyGranted roles="ROLE_ADMIN">
				<span class="menuButton"><g:link class="list" controller="user" action="list">Manage Members</g:link></span>
				<span class="menuButton"><g:link class="list" controller="membershipLevel" action="list">Manage Membership Levels</g:link></span>
				<span class="menuButton"><g:link class="list" controller="globalPreferences" action="showGlobalPreferences">Global Preferences</g:link></span>
			</sec:ifAnyGranted> 
        </div>
        <div class="body">
            <div id="formProblems"></div>
            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <center>
 			<br/>
			<g:render template="/entry/searchFormTemplate"></g:render>
 			<br/>
            </center>
            <div id="results">
            </div>
			<g:each in="${adEntries}" var="adEntry">
				<a href="${adEntry.adLink}" target="_blank" style="padding : 10px 10px 10px 10px" >
					<img alt="${adEntry.adLine}" title="${adEntry.adLine}" src="${createLink(action:'showImage', controller:'entry', id:adEntry.id)}" height="250px" width="250px">
				</a>
			</g:each>
        	</div>
    </body>
</html>
