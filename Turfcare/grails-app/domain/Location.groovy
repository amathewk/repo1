class Location {

	String name
	String streetAddress
	String city
	String state
	String zip
	String phone
	
	double latitude
	double longitude
	
	def storeLocatorService
	
    static constraints = {
	    name()
	    streetAddress()
	    city()
	    state()
	    zip()
	    phone()
    }
    
    
   def beforeInsert = {
       populateGeocodeAndZip()
   }

   def beforeUpdate = {
       populateGeocodeAndZip()
   }

    
   def populateGeocodeAndZip() 
   {

	    def geoAndZip = storeLocatorService.getGeoAndZip("$streetAddress, $city, $state $zip")
	    longitude = geoAndZip.longitude
	    latitude = geoAndZip.latitude
	    
	    if (!zip || zip.size() == 0)
	    {
		    zip = geoAndZip.zip
	    }
    }
    
}
