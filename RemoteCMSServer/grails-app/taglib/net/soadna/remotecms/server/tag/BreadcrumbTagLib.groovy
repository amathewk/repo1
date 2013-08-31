package net.soadna.remotecms.server.tag

class BreadcrumbTagLib {
	
	def breadcrumb = {
	
		if (session.site) {
			
			session.site.refresh()
			out << "<a title='switch site' href='$request.contextPath/site/listByCustomerForSelection'>${session.site.domain}</a>"
			if (session.page) {
				session.page.refresh()
				out << " <a title='switch page' href='$request.contextPath/page/listBySiteForSelection'>${session.page.path}</a>"
			}
		}
	}
}
