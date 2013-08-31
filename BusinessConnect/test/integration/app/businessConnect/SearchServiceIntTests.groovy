package app.businessConnect

import grails.test.*

class SearchServiceIntTests extends GroovyTestCase {
	
	def searchService
	def geoService
	
	def dummyGeoCode = [
		lat : 40.146937, 
		lng : -82.944403, 
		address : "440 Polaris Pkwy, Westerville, OH 43082, USA",
		correctedAddress : "440 Polaris Pkwy, Westerville, OH 43082, USA"
	]
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    
    void testSearchReturnsResults() {
		def rangeInMiles = 50
		def searchResponse = searchService.search(dummyGeoCode as GeoCodeResult, rangeInMiles, 0, 100)
		def results = searchResponse.results

		assertNotNull ( results )
    	assertTrue ( results.size() > 0 )
    	
    	boolean hiltonFound = false
    	boolean stateLodgeFound = false
    	
    	results.each { entry ->
			if ( "Hilton".equalsIgnoreCase ( entry.name ) ) {
				hiltonFound = true
			}
			if ( "State Lodge".equalsIgnoreCase ( entry.name ) ) {
				stateLodgeFound = true
			}
    	}
    	
    	assertTrue( "Hilton not found in results", hiltonFound )
    	assertFalse( "Burgers found in results", stateLodgeFound )
    }

	void testSearchReturnsResultsLargeRange() {
		def rangeInMiles = 500
		def searchResponse = searchService.search(dummyGeoCode as GeoCodeResult, rangeInMiles, 0, 100)
		def results = searchResponse.results
		assertNotNull(results)
		assertTrue(results.size() > 0)
		println results.size()
		boolean stateLodgeFound = false
		results.each { entry ->
			if ( "State Lodge".equalsIgnoreCase ( entry.name ) ) {
				stateLodgeFound = true
			}
			println entry
		}
		assertTrue("Burgers not found in results", stateLodgeFound)
		assertNotNull(searchResponse.correctedAddress)
    }
	
	void testSearchReturnsNoResultsSmallRange() {
		def rangeInMiles = 1
		def searchResponse = searchService.search(dummyGeoCode as GeoCodeResult, rangeInMiles, 0, 100)
		def results = searchResponse.results
		assertNotNull(results)
		assertEquals(0, results.size())
	}
	
	void testSearchReturnsNoResultsFarawayOrigin() {
		def rangeInMiles = 1
		def searchResponse = searchService.search([lat: 20.0, lng: -90.0] as GeoCodeResult, rangeInMiles, 0, 100)
		def results = searchResponse.results
		assertNotNull(results)
		assertEquals(0, results.size())
	}
	
}
