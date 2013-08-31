<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
    	<span style="float:right; padding:25px 25px 0px 0px">
    		<sec:ifLoggedIn>
    			Welcome, <sec:loggedInUserInfo field="username"/>
    			&nbsp;	
	    		<g:link controller="logout" absolute="false" elementId="logoff"><button>Log Off</button></g:link>
    		</sec:ifLoggedIn>
    		<sec:ifNotLoggedIn>
	    		<g:link controller="login" absolute="false" elementId="login"><button>Log In</button></g:link>
    		</sec:ifNotLoggedIn>
    	</span>
        <div class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="logo"><img src="${resource(dir:'images',file:'logo.png')}" alt="Business Connect" border="0" /></div>
        <g:layoutBody />
        <g:render template="/common/footer"></g:render>
    </body>
</html>