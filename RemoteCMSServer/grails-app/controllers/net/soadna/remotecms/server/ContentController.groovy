package net.soadna.remotecms.server

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

class ContentController extends BaseController{

	def scaffold = Content

	def listBySite = {

		def site = Site.get(params.siteId)

		// TODO: probably do not need the above line and corresponding parameter sent from gsp
		if (!site) {site =  Site.get(params.id)}

		def results = Content.findAllBySite(site, params)
		def model = [contentInstanceList: results, contentInstanceTotal: Content.findAllBySite(site).size(), site: site]

		render (view: 'list', model: model)
	}

	def createForSite = {

		def site = Site.get(Long.parseLong (params.siteId))
		def content = new Content(site: site)
		content.properties = params
		render (view: 'create', model:[contentInstance: content])
	}

	def save = {

		def contentInstance = new Content(params)
		if (contentInstance.save(flush: true)) {
			redirect(action: "show", id: contentInstance.id)
		}
		else {
			render(view: "create", model: [contentInstance: contentInstance])
		}
	}

	def show = {

		def content = Content.get(params.id)
		redirect(action: "listBySite", params: [siteId: content.site.id])
	}

	def delete = {

		def content = Content.get(params.id)
		if (content) {
			try {
				content.delete(flush: true)
				redirect(action: "listBySite", params:[siteId: content.site.id])
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "Could not delete content - constraint violation"
				redirect(action: "edit", id: params.id)
			}
		}
		else {
			flash.message = "Could not find content to delete"
			redirect(action: "listBySite", params:[siteId: content.site.id])
		}
	}

	def htmlSnippet = {

		def content = Content.get(params.id)

		render 	"""
			<br/><br/> 
			Insert the following into your web page : 
			<br/><br/> 
			&lt;span id=\"${content.divId}\" class=\"rcms\" &gt;&lt;/span&gt;
			<br/><br/> 
			<br/><br/> 
			Insert the following <i>once</i> at the bottom of your web page :
			<br/><br/> 
			
						&lt;script type="text/javascript"&gt;
				var rcmsHost = "http://cms.soa-dna.com/jsFile";
				document.write(unescape("%3Cscript src='" + rcmsHost + "' type='text/javascript'%3E%3C/script%3E"));
			&lt;/script&gt;
			<br/><br/> 
			<br/><br/> 
			"""
	}

	def retrieve = {

		def domain = params.siteDomain

		def referer = new URL(request.getHeader("Referer"))
		if (!domain) {domain = referer.host}

		log.info("Domain : ${domain}")

		def site = Site.findByDomain(domain)

		render contentInsertionJS( request.getParameterValues("divId"), params.index, site)
	}

	private def contentInsertionJS = { divIds, indices, site ->

		def js = ""

		divIds.eachWithIndex { divId, index ->
			def content = Content.findBySiteAndDivId(site, divId)

			if (!content)
			{
				log.error("Cannot retrieve content for divId: ${divId} ")
				log.error("Resolved to Site : ${site}, content : $content")
			} else {
				log.error "found for $divId : ${content.text}"
				js += "gElems[$index].innerHTML = '${content.text.encodeAsJavaScript()}';\n"
			}
		}

		return js
	}

	static def cachedJSFileResponse = null

	def jsFile = {

		log.info("accessing js file")

		response.setContentType ("text/javascript")
		def out = response.getWriter()

		if (!cachedJSFileResponse) {
			createCachedMinifiedJSFile(out)
		}
		
		out.write(cachedJSFileResponse)
		out.close()

		return null
	}

	private PrintWriter createCachedMinifiedJSFile(out) {

		log.info("Creating minified js file")
		log.info servletContext.getResource("/js/rcms.js.template")
		
		String host = grailsApplication.config.rcms.host
		def jsText = servletContext.getResource("/js/rcms.js.template").text.replace("<{rcmsHost}>", host )
		
		JavaScriptCompressor jsCompressor = new JavaScriptCompressor(new StringReader(jsText) , null)
		StringWriter stringWriter = new StringWriter ()
		jsCompressor.compress(stringWriter , (-1), true, false, false, false)

		cachedJSFileResponse = stringWriter.toString()
		return out
	}

}
