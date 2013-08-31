package net.soadna.remotecms.server


class BaseController {
	
	//def beforeInterceptor = [action: this.&auth, except:['login', 'authenticate']]
	
	def auth() {
		if (!session.user)
		{
			redirect(controller:'user', action:'login')
			return false
		}
	}
	
	def hasSiteBeenSelected() {
		
		if (!session.user.admin)
		{
			if (!session.site)
			{
				redirect(controller:'site', action:'listByCustomer')
				return false
			}
		}
	}
	
}
