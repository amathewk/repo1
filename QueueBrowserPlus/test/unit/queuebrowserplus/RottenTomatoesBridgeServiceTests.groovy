package queuebrowserplus

import org.hibernate.Session;

import grails.test.mixin.*

import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration
import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventListener
import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor
import org.junit.*

import queuebrowserplus.items.QueueItem

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RottenTomatoesBridgeService)
@Mock(RTItem)
class RottenTomatoesBridgeServiceTests {


    void testPopulateRatingsAlsoCreatesRatingsPlaceholders() {

        RTItem rtItem = new RTItem(netflixId: "123", netflixTitle: "Fargo", rtId: "12", rtTitle: "Fargo", rtRatings: new RTRatings(), )
        rtItem.save(flush: true)

        List<QueueItem> items = [new QueueItem(id: "123", title:"Fargo", releaseYear:1996),
                new QueueItem(id: "345", title:"catsbgone", releaseYear:1988)
        ]

		service.populateRatings(items)
		//		println ToStringBuilder.reflectionToString(items[0].rtRatings)
		//		println ToStringBuilder.reflectionToString(items[1].rtRatings)
		//		println ToStringBuilder.reflectionToString(items[2].rtRatings)
		//		println ToStringBuilder.reflectionToString(items[3].rtRatings)
		assertNotNull(items[0].rtRatings)
		assertNull(items[1].rtRatings)

        def list = RTItem.list()
        assertEquals 2, RTItem.count()
	}

	void testRefreshRatings() {

		RTItem dieHard = new RTItem(netflixId : "1", netflixTitle : "Die Hard", rtRatings: new RTRatings(criticsScore: 10));
		RTItem dieHard2 = new RTItem(netflixId : "2", netflixTitle : "Die Hard 2", rtRatings: new RTRatings(criticsScore: 10));
		RTItem dieHard3 = new RTItem(netflixId : "3", netflixTitle : "Die Hard 3: With a Vengeance");
		RTItem dieHard4 = new RTItem(netflixId : "4", netflixTitle : "A Good Day To Die Hard");

		[dieHard2].each {it.save(flush: true, failOnError: true)}
		Thread.sleep(5000) // Create delay for expiration of previous entry
		[dieHard, dieHard3, dieHard4].each {it.save(flush: true, failOnError: true)}

		List items = RTItem.list()

		dieHard = items.find {it.netflixTitle == "Die Hard"}
		dieHard2 = items.find {it.netflixTitle == "Die Hard 2"}
		dieHard3 = items.find {it.netflixTitle == "Die Hard 3: With a Vengeance"}
		dieHard4 = items.find {it.netflixTitle == "A Good Day To Die Hard"}

		assertEquals 4, items.size()
		assertNotNull dieHard.rtRatings
		assertNotNull dieHard2.rtRatings
		assertNull dieHard3.rtRatings
		assertNull dieHard4.rtRatings
		assertEquals 10, dieHard.rtRatings.criticsScore
		assertEquals 10, dieHard2.rtRatings.criticsScore

		service.expPeriod = 3*1000
		service.refreshRatings()

		items = RTItem.list()
		dieHard = items.find {it.netflixTitle == "Die Hard"}
		dieHard2 = items.find {it.netflixTitle == "Die Hard 2"}
		dieHard3 = items.find {it.netflixTitle == "Die Hard 3: With a Vengeance"}
		dieHard4 = items.find {it.netflixTitle == "A Good Day To Die Hard"}

		assertEquals 4, items.size()

		assertNotNull dieHard.rtRatings
		assertNotNull dieHard2.rtRatings
		assertNotNull dieHard3.rtRatings
		assertNotNull dieHard4.rtRatings

		assertEquals "Die Hard", dieHard.netflixTitle
		assertTrue 10.equals(dieHard.rtRatings.criticsScore)
		assertFalse 10.equals(dieHard2.rtRatings.criticsScore)
	}

}