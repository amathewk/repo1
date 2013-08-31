class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
		"/"(controller: "entry", action	:"searchForm")
		"/m"(view:'/mobile/mobileSearch')
		"500"(view:'/error')
	}
}
