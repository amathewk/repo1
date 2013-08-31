class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "MyQueue", action: "queue")
		"500"(view:'/error')
	}
}
