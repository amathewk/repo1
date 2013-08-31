import groovy.util.XmlParser;

import java.net.URL;

class Runner {

	static def host = "http://intra.anilmathew.net/alfresco"
	static def alf_ticket
	
	public static void main(def args)
	{
		
		alf_ticket = getLoginToken()
		def serviceUrl
		println alf_ticket
//		serviceUrl = "service/api/sites"
//		println makeCall(serviceUrl, "")
		
//		serviceUrl = "service/api/node/workspace/SpacesStore/3c0b999e-a5b2-4167-9cab-1d33fdcdf57c/children"
//		println makeCall(serviceUrl, "")
		
//		serviceUrl = "service/api/node/workspace/SpacesStore/9a025657-f572-4c7c-b81e-14e1857de580/content.oldManIndex"
//		println makeCall(serviceUrl, "")
		
		
		return
	}

	
	private static def getLoginToken() {
		
		def service = "service/api/login"
			
		def username = "amathew"
		def password = "Workpass1"
					
		def args = "u=${username}&pw=${password}"
		def response = makeCall(service, args)
		def ticket = new XmlParser().parseText(response)
		
		ticket.text()
	}
	
	private static def makeCall(def serviceUrl, def args) {
		def serviceCall = new URL("${host}/${serviceUrl}?${args}&alf_ticket=${alf_ticket}")
		serviceCall.text
	}
	
}