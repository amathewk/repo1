package queuebrowserplus

import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import queuebrowserplus.items.QueueItem;
import queuebrowserplus.items.QueueResult;

class NetflixBridgeService {

	private String baseUrl = "http://api-public.netflix.com/"
	private String consumerKey = "ktf3pkgm5t72g44xfejtkdey"
	private String sharedSecret = "4QBdkRVGEy"

	URLEncoder enc = new URLEncoder()

	static int callCount = 0

	String search (searchTerm) {
		InputStream is
		StringWriter stringWriter
		XmlParser parser
		def root

		searchTerm = enc.encode(searchTerm, "UTF-8")

		OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, sharedSecret)
		URL url = new URL(baseUrl + "catalog/titles?term=$searchTerm&max_results=10")
		HttpURLConnection request = url.openConnection()
		consumer.sign(request)

		is = request.getInputStream()
		callCount++
		log.debug "callCount $callCount"
		stringWriter = new StringWriter()
		parser = new XmlParser()
		root = parser.parse(is)
		new XmlNodePrinter(new PrintWriter(stringWriter)).print(root)

		return stringWriter.toString()
	}

	QueueResult getInstantQueue (userId, accessToken, tokenSecret, startIndex, maxResults) {
		InputStream is
		StringWriter stringWriter
		XmlParser parser
		def root

		OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, sharedSecret)
		consumer.setTokenWithSecret(accessToken, tokenSecret)
		URL url = new URL(baseUrl + "users/$userId/queues/instant?max_results=${maxResults}&start_index=${startIndex}")
		HttpURLConnection request = url.openConnection()
		consumer.sign(request)
		//		request.connect()
		is = request.getInputStream()
		callCount++
		log.debug "callCount $callCount"
		parser = new XmlParser()
		root = parser.parse(is)

		def items = []

		root.queue_item.each { queue_item ->
			QueueItem item = new QueueItem()
			item.id = queue_item.id.text()
			item.title = queue_item.title."@regular".text()
			item.position = Integer.valueOf(queue_item.position.text())
			item.releaseYear = queue_item.release_year.text()
			item.averageRating = queue_item.average_rating.text()
			items << item
		}

		return new QueueResult(items: items, queueCount: root.number_of_results.text().toInteger())
	}

	QueueResult getCompleteInstantQueue (userId, accessToken, tokenSecret) {

		QueueResult completeQueueResult = new QueueResult()
		boolean moreToGet = true
		int startIndex = 0
		while (moreToGet) {
			QueueResult queueResult = getInstantQueue (userId, accessToken, tokenSecret, startIndex, 500)
			completeQueueResult = completeQueueResult + queueResult
			startIndex = startIndex + 500
			moreToGet = (queueResult.queueCount > startIndex)
		}
		
		return completeQueueResult
	}

}
