class StoreLocatorController {

	def defaultAction = "show"
	def storeLocatorService
	
	def show = {
		
		return "" 
	}
	
	def submit = {
		def locations = storeLocatorService.searchByAddress("${params.streetAddr}, ${params.city}, ${params.state} ${params.zip}")
		render (template:'location', collection: locations)
	}
	
	def submitZipOnly = {
		def locations = storeLocatorService.searchByZip("${params.zip}")
		render (template:'location', collection: locations)
	}
}
