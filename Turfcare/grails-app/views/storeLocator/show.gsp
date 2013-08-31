<html>
    <head>
        <title>Store Locator</title>
    </head>
    <body>
	<h1>Store search</h1>
	    <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
	    
	    <h3>Verion 1 - full address - dependent on Google service.</h3>
		    
            <g:form action="submit" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="streetAddress">Street Address:</label>
                                </td>
				<td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="streetAddress" name="streetAddress" value=""/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
				<td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="city" name="city" value=""/>
                                </td>
                            </tr> 

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state">State:</label>
                                </td>
				<td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="state" name="state" value=""/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zip">Zip:</label>
                                </td>
				<td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="zip" name="zip" value=""/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons" style="padding-left:100px;padding-top:20px">
                    <span class="button"><input class="save" type="submit" value="Search" /></span>
                </div>
            </g:form>
	    
	    <hr/>
	    <h3>Verion 2 - zip code only - custom algorithm - not dependent on Google service.</h3>
	    
	    <g:form action="submitZipOnly" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zip">Zip:</label>
                                </td>
				<td valign="top" class="value ${hasErrors(bean:locationInstance,field:'city','errors')}">
                                    <input type="text" id="zip" name="zip" value=""/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons" style="padding-left:100px;padding-top:20px">
                    <span class="button"><input class="save" type="submit" value="Search" /></span>
                </div>
            </g:form>
    </body>	
</html>