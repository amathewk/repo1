package net.soadna.remotecms.server

class SiteController extends BaseController {

	def scaffold = Site
	
	def list = {

		if (session.user.isAdmin()) {
			def results = Site.list(params)
			[siteInstanceList: results, siteInstanceTotal: results.size(), instructions: "Please pick a site"]
		} else {
			listByCustomer()
		}
	}
	
	def listByCustomer = {
		
		def results = new ArrayList()
		
		results = Site.findAllByCustomer(session.user.customer, params)
		
		def model = [siteInstanceList: results, siteInstanceTotal: results.size(), instructions: "Please pick a site"]
		
		render (view: "/site/list", model: model)
	}
	
	def show = {
		redirect(action: "list")
	}
	
	def save = {
		
		def siteInstance = new Site(params)
		siteInstance.customer = session.user.customer
		if (siteInstance.save(flush: true)) {
			redirect(action: "show", id: siteInstance.id)
		}
		else {
			render(view: "create", model: [siteInstance: siteInstance])
		}
	}
	
	def update = {

		def siteInstance = Site.get(params.id)
		siteInstance.customer = session.user.customer
		
		if (siteInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (siteInstance.version > version) {
					siteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'domain.label', default: 'Site')] as Object[], "Another user has updated this Site while you were editing")
					render(view: "edit", model: [siteInstance: siteInstance])
					return
				}
			}
			siteInstance.properties = params
			if (!siteInstance.hasErrors() && siteInstance.save(flush: true)) {
				flash.message = "\${message(code: 'default.updated.message', args: [message(code: 'domain.label', default: 'Site'), siteInstance.id])}"
				redirect(action: "show", id: siteInstance.id)
			}
			else {
				render(view: "edit", model: [siteInstance: siteInstance])
			}
		}
		else {
			flash.message = "\${message(code: 'default.not.found.message', args: [message(code: 'domain.label', default: 'Site'), params.id])}"
			redirect(action: "list")
		}
	}
	
	def delete = {
		def siteInstance = Site.get(params.id)
		if (siteInstance) {
			try {
				def domain = siteInstance.domain
				siteInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: ['Site', domain])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: ['Site', params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "\${message(code: 'default.not.found.message', args: ['Site', params.id])}"
			redirect(action: "list")
		}
	}
	
}
