package app.businessConnect

class GeoCodeResult {

	def lat
	def lng
	def address
	String correctedAddress
	
	boolean hasNoResults = false
	
	boolean hasResults() {
		return ! hasNoResults
	}
	
	String toString() {
		return "latitude : $lat, longitude : $lng, address : $address, correctedAddress : $correctedAddress"	
	}
}