import org.apache.bsf.util.IOUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;


class Processor {

	String baseUrl = "http://api-public.netflix.com/"
	String consumerKey = "ktf3pkgm5t72g44xfejtkdey"
	String sharedSecret = "4QBdkRVGEy"
	String accessToken = "BQAJAAEDEAEhjfInAY7FBD08Db9r9FAwdu0EESW0vybJttdVZH8hKY-d7e21CM7k8LUKrcwqo9FfiAkw4n9cKYrIxokbdCC1"
	String tokenSecret = "aJknMGuzrgAK"
	String userId = "T1__JyEskfMDDgkOldxCf708Mob4K8qdn5ZvZPGdybsXA-"
	URLEncoder enc = new URLEncoder()

	String rottenTomatoesKey = "qcp9b49x2n47tu75yqxr67q4"
		
	void process() {
		
		InputStream is
		StringWriter stringWriter
		XmlParser parser
		def root
		
		String term = "Fargo"
		term = enc.encode(term, "UTF-8")
				
		OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, sharedSecret)
		URL url = new URL(baseUrl + "catalog/titles?term=$term&max_results=10")
		HttpURLConnection request = url.openConnection()
		
		
		request = url.openConnection()
		consumer.sign(request)
//		request.connect()
		is = request.getInputStream()
		stringWriter = new StringWriter()
		parser = new XmlParser()
		root = parser.parse(is)
		new XmlNodePrinter(new PrintWriter(stringWriter)).print(root)
		println stringWriter.toString()
		
		println root.number_of_results
		println root.catalog_title[0].title."@short"
		
//		consumer.sign(request)
//		request.connect()
//		is = request.getInputStream()
//		
//		stringWriter = new StringWriter()
//		XmlParser parser = new XmlParser()
//		root = parser.parse(is)
//		new XmlNodePrinter(new PrintWriter(stringWriter)).print(root)
//		println stringWriter.toString()
//
//		println root.number_of_results
//		println root.catalog_title[0].title."@short"
		
		consumer.setTokenWithSecret(accessToken, tokenSecret)
		url = new URL(baseUrl + "users/$userId/queues/instant")
//		url = new URL(baseUrl + "catalog/titles/series/70140403/similars")
//		url = new URL(baseUrl + "users/current")
		request = url.openConnection()
		consumer.sign(request)
//		request.connect()
		is = request.getInputStream()
		stringWriter = new StringWriter()
		parser = new XmlParser()
		root = parser.parse(is)
		new XmlNodePrinter(new PrintWriter(stringWriter)).print(root)
		println stringWriter.toString()

	}
}

new Processor().process()