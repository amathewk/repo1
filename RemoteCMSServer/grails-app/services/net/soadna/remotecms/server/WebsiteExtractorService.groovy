package net.soadna.remotecms.server

class WebsiteExtractorService {

	boolean transactional = true

	def serviceMethod() {

	}

	def getLinksFromWebPage(pageUrl)
	{
		def pageContent = new URL(pageUrl).text
		def matches = pageContent =~ /<[aA]\s*href\s*=\s*["'](.*)["']\s*>/

		def links = []
		matches.each { mainMatch, hrefLink ->
			links << hrefLink.trim()
		}

		return links
	}

	def generateFullLinksToNavigate(String domain, links, String currentPageUrl)
	{
		def currentPagePath = currentPageUrl - domain

		def pathTokens = currentPagePath.split("/")
		def lastToken = pathTokens[pathTokens.size() - 1]
		def currentPageBasePath = currentPagePath -lastToken 
		
		def fullLinks = []
		
		links.each { link ->
			fullLinks << domain + currentPageBasePath + link
		}

		return fullLinks
	}
}
