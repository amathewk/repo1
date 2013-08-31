

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Location</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Location List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Location</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${locationInstance}">
            <div class="errors">
                <g:renderErrors bean="${locationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:locationInstance,field:'name')}"/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="streetAddress">Street Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'streetAddress','errors')}">
                                    <input type="text" id="streetAddress" name="streetAddress" value="${fieldValue(bean:locationInstance,field:'streetAddress')}"/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="city" name="city" value="${fieldValue(bean:locationInstance,field:'city')}"/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state">State:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'state','errors')}">
                                    <input type="text" id="state" name="state" value="${fieldValue(bean:locationInstance,field:'state')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zip">Zip:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'zip','errors')}">
                                    <input type="text" id="zip" name="zip" value="${fieldValue(bean:locationInstance,field:'zip')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone">phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:locationInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:locationInstance,field:'phone')}"/>
                                </td>
                            </tr> 

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
