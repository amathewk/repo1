
import app.businessConnect.Entry
import app.businessConnect.User
import app.businessConnect.Role
import app.businessConnect.UserRole
import app.businessConnect.MembershipLevel
import app.businessConnect.GlobalPreferences
import grails.util.Environment;

class BootStrap {

	def init = { servletContext ->
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def memberRole = Role.findByAuthority('ROLE_MEMBER') ?: new Role(authority: 'ROLE_MEMBER').save(failOnError: true)

		def adminUser = User.findByUsername('admin') ?: new User(
			username: 'admin',
			password: 'admin',
			enabled: true).save(failOnError: true)

		if (!adminUser.authorities.contains(adminRole)) {
			UserRole.create adminUser, adminRole
		}

		def hiltonUser = User.findByUsername('hilton') ?: new User(
			username: 'hilton',
			password: 'hilton',
			enabled: true).save(failOnError: true)
				
		if (!hiltonUser.authorities.contains(memberRole)) {
			UserRole.create hiltonUser, memberRole
		}
		
		def masonUser = User.findByUsername('mason') ?: new User(
				username: 'mason',
				password: 'mason',
				enabled: true).save(failOnError: true)
				
		if (!masonUser.authorities.contains(memberRole)) {
			UserRole.create masonUser, memberRole
		}
		
		setupDefaultGlobalConfiguration()
		setupDefaultMembershipLevels()
		assignDefaultMembershipLevelsToTestUsers()
		
		if ( Entry.list().size() == 0 ) { // in grails interactive mode, restarts keep accumulating bootstrap data
			( new Entry ( name : "Hilton Morrisville", streetAddress : "1006 Forest Willow Lane", city : "Morrisville", state : "NC", zip : "27560", phoneNumber : "(614) 999-9999", webLink: "http://www.hilton.com", member: hiltonUser) ).save()
			( new Entry ( name : "Hilton Raleigh", streetAddress : "8900 Langwood Drive Apt 103", city : "Raleigh", state : "NC", zip : "27613", phoneNumber : "(614) 999-9999", webLink: "http://www.hilton.com", member: hiltonUser) ).save()
			( new Entry ( name : "Hilton Tennessee", streetAddress : "501 West Church Avenue", city : "Knoxville", state : "TN", zip : "37902", phoneNumber : "(614) 999-9999", webLink: "http://www.hilton.com", member: hiltonUser) ).save()
			( new Entry ( name : "Hilton Columbus1", streetAddress : "8700 Lyra Drive", city : "Columbus", state : "OH", zip : "43240", phoneNumber : "(614) 999-9999", webLink: "http://www.hilton.com", ad: getImageFileBytes("/sampleAds/Koala.jpg"), adLink: "http://www.shambala.org", adLine: "Shambala wild life Preserve", member: hiltonUser) ).save()
			( new Entry ( name : "Hilton Columbus2", streetAddress : "3900 Chagrin Drive", city : "Columbus", state : "OH", zip : "43219", phoneNumber : "(614) 888-8888", webLink: "http://www.hilton.com", ad: getImageFileBytes("/sampleAds/Tulips.jpg"), adLink: "http://www.tulips.com", adLine: "Tulips.com - Shop for your loved one..", member: hiltonUser ) ).save()
			( new Entry ( name : "Hilton Columbus3", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
			( new Entry ( name : "Travel Lodge", streetAddress : "5300 Cornell Road", city : "Blue Ash", state : "OH", zip : "45242", member: masonUser ) ).save()
			( new Entry ( name : "Lodge", streetAddress : "2880 Airport Drive", city : "Columbus", state : "OH", zip : "43219", phoneNumber : "(614) 333-3333", webLink: "http://www.hilton.com", ad: getImageFileBytes("/sampleAds/Lighthouse.jpg"), adLink: "http://www.lighthouse.org", adLine: "Lighthouse International - fighting vision loss", member: masonUser ) ).save()
			if (Environment.current == Environment.TEST) {
				( new Entry ( name : "Premium Lodge", streetAddress : "501 N High Street", city : "Columbus", state : "OH", zip : "43215", member: masonUser ) ).save()
				( new Entry ( name : "Hilton2", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton3", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton4", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton5", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton6", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton7", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "Hilton8", streetAddress : "35 West Fifth Street", city : "Cincinnati", state : "OH", zip : "45202", member: hiltonUser ) ).save()
				( new Entry ( name : "State Lodge", streetAddress : "1100 Carnegie Avenue", city : "Cleveland", state : "OH", zip : "44115", member: masonUser ) ).save()
			}
		}
	}
	
	void setupDefaultMembershipLevels() {
		def bronzeMembershipLevel = MembershipLevel.findByName('Bronze') ?: new MembershipLevel(
			name: 'Bronze',
			entriesLimit: 5,
			displayAdsOnWeb: false,
			displayAdsOnMobile: false
			).save(failOnError: true)
		def silverMembershipLevel = MembershipLevel.findByName('Silver') ?: new MembershipLevel(
			name: 'Silver',
			entriesLimit: 10,
			displayAdsOnWeb: true,
			displayAdsOnMobile: true
			).save(failOnError: true)
		def goldMembershipLevel = MembershipLevel.findByName('Gold') ?: new MembershipLevel(
			name: 'Gold',
			entriesLimit: 20,
			displayAdsOnWeb: true,
			displayAdsOnMobile: true
			).save(failOnError: true)
	}
	
	void setupDefaultGlobalConfiguration() {
		def globalPreferences = GlobalPreferences.get(1)
		if (!globalPreferences) {
			log.info "Setting default global preferences"
			def gp = new GlobalPreferences(adDisplayCount:6, mobileAdDisplayCount:3).save(failOnError:true)
		}
	}
	
	void assignDefaultMembershipLevelsToTestUsers() {
		def bronzeMembershipLevel = MembershipLevel.findByName('Bronze')
		def silverMembershipLevel = MembershipLevel.findByName('Silver')
		def goldMembershipLevel = MembershipLevel.findByName('Gold')
		
		def hiltonUser = User.findByUsername('hilton')
		def masonUser = User.findByUsername('mason')
		hiltonUser.membershipLevel = silverMembershipLevel
		masonUser.membershipLevel = goldMembershipLevel
	}

	def destroy = {
	}
	
	def getImageFileBytes = { path ->
		return getClass().getResourceAsStream(path).getBytes()
	}
}
