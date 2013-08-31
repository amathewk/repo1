package app.businessConnect

import app.businessConnect.Entry

class SearchService {

    static transactional = true

	def geoService
	 
	def search (Double lat, Double lng, int rangeInMiles, int offset, int pageSize) {
		log.debug "latitude = " + lat
		log.debug "longitude = " + lng
		log.debug "offset : $offset"
		log.debug "pageSize : $pageSize"
		
		double latitudeRange = rangeInMiles / 69.11
		double milesPerDegreeForLongitude = Math.abs( 69.11 * Math.cos ( lat ) )
		double longitudeRange = rangeInMiles / milesPerDegreeForLongitude
		
		log.debug "latitudeRange " + latitudeRange
		log.debug "longitudeRange " + longitudeRange
		
		def results = Entry.findAllByLatitudeBetweenAndLongitudeBetween (lat - latitudeRange, lat + latitudeRange, lng - longitudeRange , lng + longitudeRange)
		int totalCount = results.size()
		log.debug "totalcount : $totalCount"
		results.each { Entry entry ->
			entry.populateDistanceFrom (lat, lng)
		}
		results.sort { result ->
			result.distanceFromAddress as double
		}
		results = getPaginatedResultsPage (results,pageSize,offset)
		log.debug "paginated sorted results: ${results.collect{it.id + ':' + it.name + ':' + it.distanceFromAddress}}"
		return new SearchResult ( results : results, totalResultsCount: totalCount )
	}
	
    def search ( GeoCodeResult originGeoCode, int rangeInMiles, int offset, int pageSize ) {
		def searchResults = search (originGeoCode.lat, originGeoCode.lng, rangeInMiles, offset, pageSize )
		return new SearchResult ( results : searchResults.results, correctedAddress : originGeoCode.correctedAddress, totalResultsCount: searchResults.totalResultsCount )
    }
	
	private def getPaginatedResultsPage	(results, pageSize, offset) {
		int upper = offset+pageSize
		upper = upper < results.size() ? upper : results.size()
		results[offset..<upper]
	}
}