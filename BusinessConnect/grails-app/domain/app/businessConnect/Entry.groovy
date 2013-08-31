package app.businessConnect

class Entry {

	private static double UNDEFINED = 500
	
	String name
	String streetAddress
	String city
	String state
	String zip
	String phoneNumber
	String faxNumber
	String webLink
	String generalInfo
	String productsAndServices
	String paymentTypesAccepted
	
	byte[] ad = null
	String adLine
	String adLink

	double longitude = UNDEFINED
	double latitude = UNDEFINED
	
	String durationFromAddress
	String distanceFromAddress
	String correctedAddress

	User member

	static transients = ['durationFromAddress', 'distanceFromAddress', 'fullAddress']
	
	def geoService
	static belongsTo = [member:User]
	static constraints = {
		id(display:false)
		name()
		streetAddress()
		city()
		state()
		zip()
		phoneNumber (nullable: true)
		faxNumber (nullable: true)
		webLink (nullable: true)
		generalInfo (nullable: true)
		productsAndServices (nullable: true)
		paymentTypesAccepted (nullable: true)

		// TODO: change max size
		ad (nullable: true, maxSize:1073741824)
		adLine (nullable: true, maxSize:50)
		adLink (nullable: true)
		correctedAddress (nullable: true)
		longitude (display : false)
		latitude (display : false, validator: 
					{ value, reference ->
						GeoCodeResult geoCodeResult = reference.geoService.getGeoCode ( reference.getFullAddress() )
						if (geoCodeResult.hasNoResults) {
							return ['custom.invalidAddress']
						}
					}
				  )
	}

	void populateLatitudeLongitudeAndCorrectedAddress () {
		populateLatitudeLongitudeAndCorrectedAddress false
	}
		
	void populateLatitudeLongitudeAndCorrectedAddress (boolean override) {
		if (!latLngSet() || override) {
			log.info "Populating latitude, longitude and the corrected address"
			GeoCodeResult geoCodeResult = geoService.getGeoCode ( getFullAddress() )
			if (geoCodeResult.hasResults()) {
				latitude = geoCodeResult.lat
				longitude = geoCodeResult.lng
				correctedAddress = geoCodeResult.correctedAddress 
			}
		}
	}
	
	void populateDistanceAndDurationFrom (String origin) {
		DrivingDirectionResult directions = geoService.getDrivingDirectionsResults (origin, getFullAddress())
		distanceFromAddress = directions.distance
		durationFromAddress = directions.duration
	}

	void populateDistanceFrom (double srcLat, double srcLng) {
//		Math.
		double degreesToRadians = 3.1415/180
		double latDelta = (latitude - srcLat)*degreesToRadians
		double lngDelta = (longitude - srcLng)*degreesToRadians
		double srcLatRadians = srcLat * degreesToRadians
		double latitudeRadians = latitude * degreesToRadians
		double earthRadius = 6371 
		double kmToMiles = 0.621371192
		
		double a = Math.pow((Math.sin(latDelta/2)),2) + Math.cos(srcLatRadians)*Math.cos(latitudeRadians)*Math.pow((Math.sin(lngDelta/2)),2)
		double c = 2 * Math.atan2(Math.pow(a,0.5), Math.pow((1-a),0.5))
		double d = earthRadius * c
		
		log.debug "Calculated Distance from $srcLat, $srcLng to $latitude, $longitude is $d km"
		
		distanceFromAddress = (d * kmToMiles).round(1)
	}

	private boolean latLngSet() {
		latitude != UNDEFINED && longitude != UNDEFINED && correctedAddress
	}
	
	String getFullAddress () {

		String streetDelim = ''
		if (streetAddress && (city || state || zip) ) {streetDelim = ','}

		String cityDelim = ''
		if (city && (state || zip) ) {cityDelim = ','}
		
		String stateDelim = ''
		if (state && zip ) {stateDelim = ','}
		
		"$streetAddress $streetDelim $city $cityDelim $state $stateDelim $zip"
	}

	def beforeInsert = {
		populateLatitudeLongitudeAndCorrectedAddress ()
	}
	
	def beforeUpdate = {
		log.info "before update called"
		populateLatitudeLongitudeAndCorrectedAddress ()
	}

}
