package queuebrowserplus

import java.util.logging.Logger;




import grails.test.mixin.*
import org.junit.*
import static org.junit.Assert.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RTItem)
class RTItemTests {

    void testExpired() {
		RTItem rtItem = new RTItem();
//		rtItem.save()
		rtItem.lastUpdated = new Date();
		println rtItem.lastUpdated
		
		Thread.sleep(3000)
		assertEquals(false, rtItem.isExpired())
		RTItem.expirationInSecs = 3
		assertEquals(true, rtItem.isExpired())
		RTItem.expirationInSecs = 60
		assertEquals(false, rtItem.isExpired())
    }
}
