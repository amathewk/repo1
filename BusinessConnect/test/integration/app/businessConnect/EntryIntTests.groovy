package app.businessConnect

import grails.test.*
//import app.businessConnect.User

class EntryIntTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSaveSetsLatitudeAndLongitude() {

		Entry entry = new Entry(name: "SOA", streetAddress : "920 Glenmore Way #A", city : "Westerville", state : "OH", zip : "43082", member: User.findById(1))
		boolean successfullySaved = entry.save(flush:true)
		
		assertTrue( "Should be able to save Entry. Errors : " + entry.errors, successfullySaved )
		assertEquals( 15, Entry.list().size() )
		
		Entry entry2 = Entry.findByName ("SOA")
		assertNotNull(entry2)
		
		assertEquals "SOA", entry.name
		assertEquals "SOA", entry2.name

		assertTrue entry.latitude != 0
		assertTrue entry.longitude != 0
    }
}
