package app.businessConnect

import grails.test.*

class EntryTests extends GrailsUnitTestCase {

	private def mockedGeoService = [
		getGeoCode : { addr ->
			[lat: 50.0,
			 lng: 50.0,
			 correctedAddress: ""
			 ]
		}
	]
	
	protected void setUp() {
        super.setUp()
		mockLogging (Entry.class)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testPopulateLatitudeAndLongitudeWhenTheyDoNotExist() {
		Entry entry = new Entry ( name : "Burgers", streetAddress : "2134 Polaris Pkwy", city : "Columbus", state : "OH", zip : "43240" )
		entry.geoService = mockedGeoService
		entry.populateLatitudeLongitudeAndCorrectedAddress ()
		assertTrue entry.latitude > 0
		assertTrue entry.longitude > 0
    }

	void testCallPopulateLatitudeAndLongitudeWhenTheyAlreadyExist() {
		Entry entry = new Entry ( name : "Burgers", streetAddress : "2134 Polaris Pkwy", city : "Columbus", state : "OH", zip : "43240", latitude : 2.2, longitude : 3.0, correctedAddress: "Raleigh, NC" )
		entry.geoService = mockedGeoService
		entry.populateLatitudeLongitudeAndCorrectedAddress ()
		assertEquals 2.2, entry.latitude
		assertEquals 3.0, entry.longitude
    }

	void testCallOverrideAndPopulateLatitudeAndLongitudeWhenTheyAlreadyExist() {
		Entry entry = new Entry ( name : "Burgers", streetAddress : "2134 Polaris Pkwy", city : "Columbus", state : "OH", zip : "43240", latitude : 2.2, longitude : 3.0 )
		entry.geoService = mockedGeoService
		entry.populateLatitudeLongitudeAndCorrectedAddress (true)
		assertFalse entry.latitude == 2.2
		assertFalse entry.longitude == 3.0
    }
	
	void testPopulateLatitudeAndLongitudeInvalidAddress() {
		Entry entry = new Entry ( name : "Burgers", streetAddress : "2134 Polaris Pkwy", city : "Columbus", state : "OH", zip : "43240" )
		entry.geoService = mockedGeoService
		entry.populateLatitudeLongitudeAndCorrectedAddress ()
		assertTrue entry.latitude > 0
		assertTrue entry.longitude > 0
	}
	
}