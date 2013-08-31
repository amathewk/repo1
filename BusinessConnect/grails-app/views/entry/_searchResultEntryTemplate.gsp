<g:if test="${it}">
<span style="color:blue">
	${fieldValue(bean: it, field: "name")}
</span>
<br/>
${it.fullAddress} 
<br/>
<b>${it.phoneNumber}</b>
<br/>
<g:if test="${it.webLink}">
	<a target="_blank" href="${it.webLink}">Website</a>
</g:if>
<br/>
<br/>
</g:if>