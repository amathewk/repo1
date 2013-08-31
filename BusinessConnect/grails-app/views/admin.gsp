

<ul>
	<li class="controller"><g:link controller="entry" action="searchForm">search</g:link></li>
</ul>

Upload Form: <br />
		<g:if test="${flash.message}">
            <div class="messagebox">
               ${flash.message}
            </div>
   		</g:if>	
        <g:form controller="entry" action="batchUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="entriesFile" />
		<input type="submit" />
	</g:form>
