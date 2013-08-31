

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Location List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Location</g:link></span>
        </div>
        <div class="body">
            <h1>Location List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />

                   	        <g:sortableColumn property="streetAddress" title="Street Address" />

			        <g:sortableColumn property="city" title="City" />
                        
                   	        <g:sortableColumn property="state" title="State" />
                        
                   	        <g:sortableColumn property="zip" title="Zip" />
				
                   	        <g:sortableColumn property="phone" title="Phone" />
                        
                   	</tr>
                    </thead>
                    <tbody>
                    <g:each in="${locationInstanceList}" status="i" var="locationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${locationInstance.id}">${fieldValue(bean:locationInstance, field:'id')}</g:link></td>
                        
			    <td><g:link action="show" id="${locationInstance.id}">${fieldValue(bean:locationInstance, field:'name')}</g:link></td>
			    
                            <td>${fieldValue(bean:locationInstance, field:'streetAddress')}</td>
                        
                            <td>${fieldValue(bean:locationInstance, field:'city')}</td>
                        
                            <td>${fieldValue(bean:locationInstance, field:'state')}</td>
                        
                            <td>${fieldValue(bean:locationInstance, field:'zip')}</td>
			    
			    <td>${fieldValue(bean:locationInstance, field:'phone')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${locationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
