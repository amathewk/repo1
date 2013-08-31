package app.businessConnect

class MembershipLevel {

	String name
	int entriesLimit
	boolean displayAdsOnWeb
	boolean displayAdsOnMobile
	
    static constraints = {
		name()
		entriesLimit()
		displayAdsOnWeb()
		displayAdsOnMobile()
    }
}
