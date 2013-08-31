package app.businessConnect

import grails.test.*
import java.net.URLEncoder

import org.junit.Assert;

class GeoServiceIntTests extends GrailsUnitTestCase {
	
	def geoService = new GeoService()
	
    protected void setUp() {
        super.setUp()
		String.metaClass.encodeAsURL = {
			return URLEncoder.encode(delegate, "UTF-8")
		}
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testGetGeocode() {
		def address = "1600 Amphitheatre Parkway, Mountain View, CA"
		def geoCode = geoService.getGeoCode(address)
		assertEquals(37, geoCode.lat as Integer)
		assertEquals(-122, geoCode.lng as Integer)
    }

	void testGetGeocodeBadAddress() {
		def address = "1600 Amphitheatre Parkway, Little View, LL"
		def geoCode = geoService.getGeoCode(address)
		assertTrue geoCode.hasNoResults
    }
	
	void testGetGeocodePartialAddress() {
		def address = "440 P P Ste. 500, Wesville, KK 43082"
		GeoCodeResult geoCode = geoService.getGeoCode(address)
		assertFalse geoCode.hasNoResults
		assertNotNull geoCode.correctedAddress
		assertTrue( geoCode.correctedAddress.contains("Westerville") )
	}
	
}