import grails.util.GrailsUtil

import net.soadna.remotecms.server.Content
import net.soadna.remotecms.server.Customer 
import net.soadna.remotecms.server.Site 
import net.soadna.remotecms.server.User
import net.soadna.remotecms.server.Tag

class BootStrap {

     def init = { servletContext ->
		
		switch(GrailsUtil.environment) {
			case "development": 
				setupTestData()
				break
		}
     }
	
     def destroy = {
     }
	
     def setupTestData = {

		createTags()
		 		
		def oldManArtz = new Customer(
				name:"OLD MAN ARTZ DESIGN", 
				address:'''472 Maggie Drive 
    	    					 Antioch, TN 37013''',
				phoneNumber: "(615) 496-9094"	 
				).save()
		
		def soadna = new Customer(
				name:"SOA-DNA", 
				address:'''123 sycamore Drive 
    	    					 Raleigh, NC 12345''',
				phoneNumber: "(123) 123-3212"	 
				).save()

				
		setupSiteContentData(oldManArtz, soadna)
		setupUsers(oldManArtz,soadna)
     }
	
	 def setupSiteContentData(oldManArtz, soadna) 
	 {
	 	def oldManArtzSite = new Site(
				domain: "www.oldmanartz.com",
				customer: oldManArtz
		).save()

		def soadnaSite = new Site(
				domain: "www.soa-dna.com",
				customer: soadna
		).save()

		def soadnaNetSite = new Site(
				domain: "www.soa-dna.net",
				customer: soadna
		).save()

		def localhostSite = new Site(
				domain: "localhost",
				customer: soadna
		).save()
		
		(1..22).each { index ->
				new Site(
					domain: "dummySite" + index,
					customer: soadna
			).save()
	 	}
		
		
		new Content(
			text: "Welcome to the Sample Page",
			site: localhostSite,
			divId: "welcome"
		).save()
	
		new Content(
			text: "About Us",
			site: localhostSite,
			divId: "aboutUs"
		).save()

		def welcomeMessage = new Content(
				text : '''
					Welcome to our home. Eric Jackson has always incorporated his acute sense of style into every aspect of his life. In 2001, Eric decided to fulfill a life-long dream of owning his own business when he officially launched Old Man Artz Design. Originally, the company was created to fill a void in the market for the uplifting of small businesses and ministries to experience well-crafted, sophisticated designs that also reflected an custom sensibility and style.
					<br/>
					"I always had the idea that I would start my own design business, but I wanted to make sure the timing was right and that I had the time to devote to it," said Eric. Since its launch, Old Man Artz Design has enjoyed critical and commercial success. Through the support of family, friends and a loyal clientele, Old Man Artz Design has quickly grown from an small business to a national brand.
					<br/>
					"We are not in the design & marketing business for a quick hit. We are truly committed to the expansion and growth of our clients and will use all of our resources to ensure that the quality of your marketing pieces, from conception to completion, always exceeds your expectations," said Eric Jackson. "We would ultimately like to have Old Man Artz Design viewed as the Future of Design."
				''',
				site: oldManArtzSite,
				divId: "welcome",
				tag: [Tag.findByLabel ( "Home" ), Tag.findByLabel ( "Feedback" )]
		).save()

		(1..25).each { index ->
			
			new Content(
					text : '''
						Welcome to SOA-DNA!!!!
						''',
						site: localhostSite,
						divId: "welcome" + index
			).save()
		}
	}
	
	def setupUsers(customer1, customer2) {
		(new User(username: "amathew", password: "amathew", name:"Anil Mathew", customer: customer1, admin:true)).save()
		(new User(username: "amathew2", password: "amathew2", name:"Anil Mathew", customer: customer2, admin:false)).save()
	}
	
	def createTags() {
		
		new Tag ( label : "Home" ).save()
		new Tag ( label : "Home2" ).save()
		new Tag ( label : "About us" ).save()
		new Tag ( label : "New" ).save()
		new Tag ( label : "News" ).save()
		new Tag ( label : "History" ).save()
		new Tag ( label : "History2" ).save()
		new Tag ( label : "Feedback" ).save()
		new Tag ( label : "Contact" ).save()
		
	}
} 