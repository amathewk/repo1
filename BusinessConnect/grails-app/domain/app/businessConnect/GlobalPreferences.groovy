package app.businessConnect

class GlobalPreferences {

	int adDisplayCount 
	int mobileAdDisplayCount 
	int imageDisplayWidth 
	int imageDisplayHeight
	String administratorEmailAddress
	
    static constraints = {
		adDisplayCount(range:0..40)
		mobileAdDisplayCount(range:0..30)
		administratorEmailAddress(nullable:true)
    }
}
