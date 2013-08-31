package queuebrowserplus



import grails.test.mixin.*

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.*

import queuebrowserplus.items.QueueItem;
import queuebrowserplus.items.QueueResult;
import static org.junit.Assert.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(NetflixBridgeService)
class NetflixBridgeServiceTests {

	static final int QUEUE_SIZE = 467

	String accessToken = "BQAJAAEDEAEhjfInAY7FBD08Db9r9FAwdu0EESW0vybJttdVZH8hKY-d7e21CM7k8LUKrcwqo9FfiAkw4n9cKYrIxokbdCC1"
	String tokenSecret = "aJknMGuzrgAK"
	String userId = "T1__JyEskfMDDgkOldxCf708Mob4K8qdn5ZvZPGdybsXA-"
	
    void testGetInstantQueue() {
		QueueResult queueResult = service.getInstantQueue(userId, accessToken, tokenSecret, 0, 50)
		assertQueueResults(queueResult.items, "1", "50", 50)
		assertEquals QUEUE_SIZE, queueResult.queueCount 
		queueResult = service.getInstantQueue(userId, accessToken, tokenSecret, 50, 50)
		assertQueueResults(queueResult.items, "51", "100", 50)
		assertEquals QUEUE_SIZE, queueResult.queueCount 
    }

    void testGetCompleteInstantQueue() {
		QueueResult queueResult = service.getCompleteInstantQueue(userId, accessToken, tokenSecret)
		assertQueueResults(queueResult.items, "1", QUEUE_SIZE.toString(), QUEUE_SIZE)
		assertEquals QUEUE_SIZE, queueResult.queueCount
    }
    
	private assertQueueResults(List<QueueItem> results, String expectedStartPosition, String expectedEndPosition, int expectedResultsSize) {
		assertNotNull results
		assertTrue results instanceof List
		assertEquals expectedResultsSize, results.size()
		assertEquals expectedStartPosition, results[0].position
		assertEquals expectedEndPosition, results[expectedResultsSize-1].position
//		println ToStringBuilder.reflectionToString(results[20], ToStringStyle.MULTI_LINE_STYLE)
		assertTrue ( StringUtils.isNotBlank(results[0].title))
		assertTrue ( StringUtils.isNotBlank(results[0].id))
		assertTrue ( StringUtils.isNotBlank(results[0].position))
		assertTrue ( StringUtils.isNotBlank(results[0].releaseYear))
		assertTrue ( StringUtils.isNotBlank(results[0].averageRating))
	}
}
