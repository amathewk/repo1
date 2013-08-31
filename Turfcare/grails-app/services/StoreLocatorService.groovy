import java.lang.Math


class StoreLocatorService {

    boolean transactional = true

    
    def searchByAddress(String address) 
    {

	def allLocations = Location.findAll()	    
	
	sortByClosestLocation(allLocations, address)
    }
    
    def searchByZip(String zip) 
    {

	def locations = new LinkedHashSet()
	
	(5..0).each {
	  def newLocations = Location.findAllByZipLike(zip.substring(0,it) + '%')
	  locations.addAll newLocations
	}
	
	locations
    }
        
    def getGeo(address) 
    {

	def node = getGMapInfo(address)
	
	String coords = node.Response.Placemark.Point.coordinates.text()
	
	// TODO add error handling for invalid addresses
	
	def tokens = coords.split(",")
	def longi = tokens[0]
	def lati = tokens[1]
	
	return [longitude:Double.parseDouble(longi), latitude: Double.parseDouble(lati)]
    }

    
    def getGeoAndZip(address)
    {
	    
	def node = getGMapInfo(address)
	
	String coords = node.Response.Placemark.Point.coordinates.text()
	
	// TODO add error handling for invalid addresses
	
	def tokens = coords.split(",")
	def longi = tokens[0]
	def lati = tokens[1]
	
	def zipCode = node.Response.Placemark.AddressDetails.Country.AdministrativeArea.SubAdministrativeArea.Locality.PostalCode.PostalCodeNumber.text()
	
	return [zip:zipCode, longitude:Double.parseDouble(longi), latitude: Double.parseDouble(lati)]
    }

    def getGMapInfo(address)
    {
	
	address = address.replaceAll(" ", "+")
	
	def url = new URL("http://maps.google.com/maps/geo?q=${URLEncoder.encode(address,'UTF-8')}&output=xml&sensor=false&key=ABQIAAAAk-D4ojlECZ8VT4DtwYOaixRDuspR2irGW89-3H7vfoqNSe3RkxRB-cIQoIy8CWOFUOI4Rx0nDPw5tQ")
	
	(new XmlParser()).parseText(url.text)
	    
    }
    
    def sortByClosestLocation(def locations, def address)
    {
	
	def geo = getGeo(address)
	def closest = distance([latitude:locations[0].latitude, longitude:locations[0].longitude], geo) 
	def closestLoc = locations[0]
	
	def locationsWithDistance = []
	
	locations.each {
	double dist = distance([latitude:it.latitude, longitude:it.longitude], geo)
	locationsWithDistance << [location: it, distance: dist]
	}
	
	locationsWithDistance = locationsWithDistance.sort {o1, o2 ->
	    o1.distance - o2.distance
	}
	
	return locationsWithDistance.location
    }

    def distance(def geo1, def geo2)
    {
	    
	def A = geo1.latitude/57.29577951
	def B = geo1.longitude/57.29577951
	def C = geo2.latitude/57.29577951
	def D = geo2.longitude/57.29577951
	
	return 3963.1*Math.acos(Math.sin(A)*Math.sin(C)+Math.cos(A)*Math.cos(C)*Math.cos(B-D))
    }


}



