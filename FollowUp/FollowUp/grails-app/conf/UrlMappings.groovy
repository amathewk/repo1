class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

//		"/"(controller: "patient", action:"list")
		"/"(controller: "search", action:"patientForm")
		"/ltd"(controller: "debug", action:"loadTestData")
		"500"(view:'/error')
	}
}
