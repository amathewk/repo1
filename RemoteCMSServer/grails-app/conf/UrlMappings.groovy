class UrlMappings {
	static mappings = {
		"/contentJs"(controller:"content", action:"retrieve")
		"/jsFile"(controller:"content", action:"jsFile")
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(controller:"site", action:"list")
		"500"(view:'/error')
	}
}
