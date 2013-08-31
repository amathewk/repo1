package net.soadna.remotecms.server

class TagController {

	def scaffold = Tag
	
    def index = { }
	
	def pick = {}
	
	def searchAJAX = {

		def tags = Tag.findAllByLabelIlike("${params.query}%")

		//Create XML response
		render(contentType: "text/xml") {
			results() {
				tags.each { tag ->
					result()
					{
							name(tag.label)
							//Optional id which will be available in onItemSelect
							id(tag.id)
					}
				}
			}
		}
	}
	
}
