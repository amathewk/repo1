package net.soadna.remotecms.server

import grails.test.*

class WebsiteExtractorServiceTests extends GrailsUnitTestCase {

	WebsiteExtractorService websiteExtractorService = new WebsiteExtractorService()

	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testGetLinksFromWebPage() {
		def testURL = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/testWebPage.html"
		def links = websiteExtractorService.getLinksFromWebPage(testURL)

		assertEquals 3, links.size()
		assertEquals "a1.html", links[0]
		assertEquals "a2.html", links[1]
		assertEquals "a3.html", links[2]
	}
	
	void testGenerateFullLinksToNavigate() {
		
		def domain = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server"
		def links = ["a1.html", "a2.html", "a3.html"]
		def currentPageUrl = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/testWebPage.html"
		
		def fullLinks = websiteExtractorService.generateFullLinksToNavigate(domain, links, currentPageUrl)
		
		assertEquals 3, fullLinks.size()
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/a1.html", fullLinks[0]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/a2.html", fullLinks[1]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/a3.html", fullLinks[2]
		
	}

	void testGenerateFullLinksToNavigateFromSubFolderWebPage() {
		
		def domain = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server"
		def links = ["a1.html", "a2.html", "a3.html"]
		def currentPageUrl = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/"

		def fullLinks = websiteExtractorService.generateFullLinksToNavigate(domain, links, currentPageUrl)

		assertEquals 3, fullLinks.size()
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a1.html", fullLinks[0]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a2.html", fullLinks[1]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a3.html", fullLinks[2]
			            	                                                                                                                                                                                                                                                                                                                                                                         
	}

	void testGenerateFullLinksToNavigateFromSubFolderWebPageWithoutTrailingSlash() {
		
		def domain = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server"
		def links = ["a1.html", "a2.html", "a3.html"]
		def currentPageUrl = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder"

		def fullLinks = websiteExtractorService.generateFullLinksToNavigate(domain, links, currentPageUrl)

		assertEquals 3, fullLinks.size()
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a1.html", fullLinks[0]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a2.html", fullLinks[1]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolder/a3.html", fullLinks[2]
			            	                                                                                                                                                                                                                                                                                                                                                                                                       
	}
	
	void testGenerateFullLinksToNavigateFromPageWithRelativeLinksThatPointToParentWebFolder() {
		
		def domain = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server"
		def links = ["../a1.html", "../../a2.html", "../../../a3.html"]
		def currentPageUrl = "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolderA/subfolderB/"

		def fullLinks = websiteExtractorService.generateFullLinksToNavigate(domain, links, currentPageUrl)

		assertEquals 2, fullLinks.size()
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/subFolderA/a1.html", fullLinks[0]
		assertEquals "file:///C://workspace/remoteCMS/RemoteCMSServer/test/unit/net/soadna/remotecms/server/a2.html", fullLinks[1]
			            	                                                                                                                                                                                                                                                                                                                                                                                                       
	}
	
}
