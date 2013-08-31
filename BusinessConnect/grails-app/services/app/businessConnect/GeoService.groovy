package app.businessConnect

import grails.converters.*

class GeoService {

    //static transactional = true

	def googleBaseURL = "http://maps.googleapis.com/maps/api/"
	
    GeoCodeResult getGeoCode(String address) {

		log.info "Calling geocode service"
		
		GeoCodeResult geoCodeResult = new GeoCodeResult(address:address)
		
		def restMethodURL = googleBaseURL + "geocode/json?"
		def reqParams = "address=" + address.encodeAsURL() + "&sensor=false"
		def restURL = restMethodURL + reqParams
		
		sleep 110 // Delay to prevent rate limit throttling
		def response = new URL(restURL).text
		log.debug " REST call response $response "

		def json = JSON.parse(response)
		// add handling for other statuses
		if ( json.status == "REQUEST_DENIED" ) {
			log.error "geo code service request denied"	
			geoCodeResult.hasNoResults = true
		} else if ( json.status == "OVER_QUERY_LIMIT" ) { 
			log.error "geo code service is over query limit"	
		} else if ( json.status == "ZERO_RESULTS" ) { 
			geoCodeResult.hasNoResults = true 
		} else {
		
			def location = json.results[0].geometry.location
			
			geoCodeResult.lat = location.lat
			geoCodeResult.lng = location.lng
			
			geoCodeResult.correctedAddress = json.results[0].formatted_address
			log.debug "Corrected address : $geoCodeResult.correctedAddress"
		}
		
		return geoCodeResult
    }
	
	DrivingDirectionResult getDrivingDirectionsResults(String startingAddress, String destinationAddress) {
		
		log.info "Calling directions service"
		
		DrivingDirectionResult drivingDirectionResult = new DrivingDirectionResult()
		
		def restMethodURL = googleBaseURL + "directions/json?"
		def reqParams = "origin=${startingAddress.encodeAsURL()}&destination=${destinationAddress.encodeAsURL()}&sensor=false"
		def restURL = restMethodURL + reqParams
		
		def response = new URL(restURL).text
		log.debug " REST call response $response "
		
		def json = JSON.parse(response)
		
		// TODO: add handling for other statuses
		if ( json.status == "REQUEST_DENIED" ) {
			log.error "geo code driving dir service request denied"	
//			DrivingDirectionResult.hasNoResults = true
		} else if ( json.status == "ZERO_RESULTS" ) { 
			log.error "geo code driving dir service returned zero results"	
//			DrivingDirectionResult.hasNoResults = true 
		} else if ( json.status == "OVER_QUERY_LIMIT" ) { 
			log.error "geo code driving dir service is over query limit"	
//			DrivingDirectionResult.hasNoResults = true 
		} else {
			if (json.routes && json.routes.legs) {
				def duration = json.routes[0].legs[0].duration.text
				def distance = json.routes[0].legs[0].distance.text
				
				drivingDirectionResult.duration = duration
				drivingDirectionResult.distance = distance
			} else {
				log.error ("Could not get distance and duration for location because either route or leg element is missing from json response")
				log.error (JSON : json)
			}
		}
		
		return drivingDirectionResult
	}
}
