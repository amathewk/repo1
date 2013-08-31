package queuebrowserplus

class RTItem {

	static expirationInSecs = 8*24*60*60;
	
    static constraints = {
		
    }
	
	String rtId
	String netflixId
	String imdbId
	
	String rtTitle
	String netflixTitle
    Date lastCheck

	Date lastUpdated
	Date dateCreated
	
	RTRatings rtRatings
	
	static mapping = {
		id column: 'netflixId'
		netflixTitle index: 'netflix_title_idx'
		rtTitle index: 'rt_title_idx'
	}
	
	static embedded = ['rtRatings']
	
	boolean isExpired() {
		System.currentTimeMillis() - lastUpdated.time > expirationInSecs * 1000
	}
}

	

class RTRatings {
	
	String criticsRating
	Integer criticsScore
	String audienceRating
	Integer audienceScore
}

