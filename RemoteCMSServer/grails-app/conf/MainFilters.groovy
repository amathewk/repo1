class MainFilters {

	def filters = {

		authCheck (controller: '*', action: '*')
		{
			before = 
			{
				if (!isContentRetrieval(controllerName, actionName))
				{
					if (!session?.user && controllerName != 'user' )
					{
						redirect(controller:'user', action:'login')
						return false
					}
					else
					{
						return true
					}
				}
			}
		}

//		nonAdminChecks (controller: '*', action: '*') 
//		{
//			before = 
//			{
//				if (!session.user.admin)
//				{
//					if (!session.site) {
//						redirect(controller:'site', action:'listByCustomerForSelection')
//						return false
//					} else if (!session.page)
//					{
//						redirect(controller:'page', action:'listBySiteForSelection')
//						return false
//					}
//				}
//				
//				return true;
//			}
//		}
	}
	
	private def isContentRetrieval(controllerName, actionName)
	{
		( controllerName == 'content' && actionName =='retrieve' )		||
		( controllerName == 'content' && actionName =='jsFile' )
	}
}