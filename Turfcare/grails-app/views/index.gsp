<html>
    <head>
        <title>Welcome to Grails</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <h1 style="margin-left:20px;color:green:">Administration Console</h1>
        <!--<p style="margin-left:20px;width:80%;">OPTIONS:</p> -->
        <div class="dialog" style="margin-left:20px;width:60%">
            <ul>
              <g:each var="c" in="${grailsApplication.controllerClasses}">
                    <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.name}s</g:link></li>
              </g:each>
            </ul>
        </div>
    </body>
</html>